/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import org.xpertss.unit.math.RationalNumber;
import xpertss.measure.Prefix;
import xpertss.measure.UnitConverter;
import java.math.BigInteger;
import java.util.Objects;

/**
 * UnitConverter for numbers in base^exponent representation.
 */
public final class PowerOfIntConverter extends AbstractConverter implements MultiplyConverter {

	private final int base;
	private final int exponent;
	private final int hashCode;
	private final RationalNumber rationalFactor;

	/**
	 * Creates a converter with the specified Prefix.
	 * 
	 * @param prefix
	 *            the prefix for the factor.
	 */
	static PowerOfIntConverter of(Prefix prefix)
	{
		return of(prefix.getValue(), prefix.getExponent());
	}

	/**
	 * Creates a converter with a factor represented by specified base^exponent.
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 */
	static PowerOfIntConverter of(int base, int exponent)
	{
		return new PowerOfIntConverter(base, exponent);
	}
	
	/**
	 * Creates a converter with a factor represented by specified base^exponent.
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 */
	static PowerOfIntConverter of(Number base, int exponent)
	{
		return new PowerOfIntConverter(base.intValue(), exponent);
	}

	protected PowerOfIntConverter(int base, int exponent)
	{
		if(base == 0) {
			throw new IllegalArgumentException("base cannot be zero (because 0^0 is undefined)");
		}
		this.base = base;
		this.exponent = exponent;
		this.hashCode = Objects.hash(base, exponent);
		this.rationalFactor = calculateRationalNumberFactor();
	}

	public int getBase()
	{
		return base;
	}

	public int getExponent()
	{
		return exponent;
	}

	@Override
	public boolean isIdentity()
	{
		if( base == 1 ) {
			return true; // 1^x = 1
		}
		return exponent == 0; // x^0 = 1, for any x!=0
		// [ahuber] 0^0 is undefined, but we guard against base==0 in the constructor,
		// and there is no composition, that changes the base
	}

	@Override
	protected boolean canReduceWith(AbstractConverter that)
	{
		if (that instanceof PowerOfIntConverter) {
			return ((PowerOfIntConverter) that).base == this.base;
		}
		return that instanceof RationalConverter;
	}

	@Override
	protected AbstractConverter reduce(AbstractConverter that)
	{
		if (that instanceof PowerOfIntConverter) {
			PowerOfIntConverter other = (PowerOfIntConverter) that;
			if(this.base == other.base) { // always true due to guard above
				return composeSameBaseNonIdentity(other);
			} 
		}
		if (that instanceof RationalConverter) {
			return (AbstractConverter) toRationalConverter().concatenate(that);
		}
		throw new IllegalStateException(String.format(
				"%s.simpleCompose() not handled for converter %s", 
				this, that));
	}

	@Override
	public AbstractConverter inverseWhenNotIdentity()
	{
		return new PowerOfIntConverter(base, -exponent);
	}

    @Override
    protected Number convertWhenNotIdentity(Number value) {
        return Calculator.of(rationalFactor)
                .multiply(value)
                .peek();
    }
    
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj instanceof UnitConverter) {
			UnitConverter other = (UnitConverter) obj;
			if(this.isIdentity() && other.isIdentity()) {
				return true;
			}
		}
		if (obj instanceof PowerOfIntConverter) {
			PowerOfIntConverter other = (PowerOfIntConverter) obj;
			return this.base == other.base && this.exponent == other.exponent;
		}
		return false;
	}

	@Override
	public final String transformationLiteral()
	{
		if(base<0) {
			return String.format("x -> x * (%s)^%s", base, exponent);
		}
		return String.format("x -> x * %s^%s", base, exponent);
	}
	

    public Number getValue()
	{
        return rationalFactor;
    }

	@Override
	public int compareTo(UnitConverter o)
	{
		if (this == o) {
			return 0;
		}
		if(this.isIdentity() && o.isIdentity()) {
			return 0;
		}
		if (o instanceof PowerOfIntConverter) {
			PowerOfIntConverter other = (PowerOfIntConverter) o;
			int c = Integer.compare(base, other.base);
			if(c!=0) {
				return c;
			}
			return Integer.compare(exponent, other.exponent);
		}
		return this.getClass().getName().compareTo(o.getClass().getName());
	}

	@Override
	public int hashCode()
	{
		return hashCode;
	}

	// -- HELPER
	
	private RationalNumber calculateRationalNumberFactor()
	{
        if(exponent==0) {
            return RationalNumber.ONE;
        }
        BigInteger bintFactor = BigInteger.valueOf(base).pow(Math.abs(exponent));
        if(exponent>0) {
            return RationalNumber.ofInteger(bintFactor);
        }
        return RationalNumber.of(BigInteger.ONE, bintFactor);
    }

	private PowerOfIntConverter composeSameBaseNonIdentity(PowerOfIntConverter other)
	{
		// no check for identity required
		return new PowerOfIntConverter(this.base, this.exponent + other.exponent);
	}

	public RationalConverter toRationalConverter()
	{
		return new RationalConverter(rationalFactor);
	}
}
