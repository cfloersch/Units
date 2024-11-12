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
		if (this == obj) return true;
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
		if (this == o) return 0;
		if(this.isIdentity() && o.isIdentity()) {
			return 0;
		}
		if (o instanceof PowerOfIntConverter) {
			PowerOfIntConverter other = (PowerOfIntConverter) o;
			int c = Integer.compare(base, other.base);
			if(c != 0) return c;
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
