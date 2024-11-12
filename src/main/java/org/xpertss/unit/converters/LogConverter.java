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
import xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * This class represents a logarithmic converter of limited precision. Such converter
 * is typically used to create logarithmic unit. For example:
 * <code>
 *   Unit&lt;Dimensionless&gt; BEL = Unit.ONE.transform(new LogConverter(10).inverse());
 * </code>
 */
public final class LogConverter extends AbstractConverter { // implements
	/**
	 * Holds the logarithmic base.
	 */
	private final double base;
	/**
	 * Holds the natural logarithm of the base.
	 */
	private final double logOfBase;

	/**
	 * Returns a logarithmic converter having the specified base.
	 *
	 * @param base
	 *          the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
	 */
	public LogConverter(double base)
	{
		this.base = base;
		this.logOfBase = Math.log(base);
	}

	/**
	 * Returns the logarithmic base of this converter.
	 *
	 * @return the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
	 */
	public double getBase()
	{
		return base;
	}

	@Override
	public boolean isIdentity()
	{
		return false;
	}

	@Override
	protected boolean canReduceWith(AbstractConverter that)
	{
		if(that instanceof ExpConverter) {
			return ((ExpConverter)that).getBase() == base; // can compose with exp to identity, provided it has same base
		}
		return false;
	}

	@Override
	protected AbstractConverter reduce(AbstractConverter that)
	{
		return AbstractConverter.IDENTITY;
	}

	@Override
	public AbstractConverter inverseWhenNotIdentity()
	{
		return new ExpConverter(base);
	}

	@Override
	public final String transformationLiteral()
	{
		if (base == Math.E) return "x -> ln(x)";
		return String.format("x -> log(base=%s, x)", base);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj instanceof LogConverter) {
			LogConverter that = (LogConverter) obj;
			return Objects.equals(base, that.base);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(base);
	}

    @Override
    protected Number convertWhenNotIdentity(Number value)
	{
        return Calculator.of(value)
								  .log()
								  .divide(logOfBase)
								  .peek();
    }

	@Override
	public boolean isLinear()
	{
		return false;
	}

	public String getValue()
	{
		return toString();
	}

	@Override
	public int compareTo(UnitConverter o)
	{
		if (this == o) return 0;
		if (o instanceof LogConverter) {
			// TODO Should this have worked with ExpConverter
			return getValue().compareTo(String.valueOf(((LogConverter) o).getValue()));
		}
		return -1;
	}
}
