package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import org.xpertss.unit.math.Pi;
import xpertss.measure.UnitConverter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * This class represents a converter multiplying numeric values by a factor of
 * Pi to the power of an integer exponent (π^exponent).

 */
final class PowerOfPiConverter extends AbstractConverter implements MultiplyConverter {
	private final Object $lock1 = new Object[0]; // serializable lock for 'scaleFactor'
	
	private final int exponent;
	private final int hashCode;
	private transient Number scaleFactor;

	/**
     * A converter by Pi to the power of 1.
     */
    public static final PowerOfPiConverter ONE = of(1);
	
	/**
	 * Creates a converter with the specified exponent.
	 * 
	 * @param exponent
	 *            the exponent for the factor π^exponent.
	 */
	static PowerOfPiConverter of(int exponent)
	{
		return new PowerOfPiConverter(exponent);
	}

	protected PowerOfPiConverter(int exponent)
	{
		this.exponent = exponent;
		this.hashCode = Objects.hash(exponent);
	}

	public int getExponent()
	{
		return exponent;
	}

	@Override
	public boolean isIdentity()
	{
		return exponent == 0; // x^0 = 1, for any x!=0
	}

	@Override
	public boolean isLinear()
	{
		return true;
	}

	@Override
	public AbstractConverter inverseWhenNotIdentity()
	{
		return new PowerOfPiConverter(-exponent);
	}
	
    @Override
    protected Number convertWhenNotIdentity(Number value)
	{
        return Calculator.of(getFactor())
              .multiply(value)
              .peek();
    }

	@Override
	protected boolean canReduceWith(AbstractConverter that)
	{
		return that instanceof PowerOfPiConverter;
	}

	@Override
	protected AbstractConverter reduce(AbstractConverter that)
	{
		return new PowerOfPiConverter(this.exponent + ((PowerOfPiConverter)that).exponent);
	}
	
    public Number getValue()
	{
	    
	    synchronized ($lock1) {
	       if(scaleFactor==null) {
	           int nbrDigits = MathContext.DECIMAL128.getPrecision();
	           if (nbrDigits == 0)
	               throw new ArithmeticException("Pi multiplication with unlimited precision");
	           BigDecimal pi = Pi.ofNumDigits(nbrDigits);
	           
	           scaleFactor = Calculator.of(pi)
	                   				.power(exponent)
	                   				.peek();
	       }
        }

        return scaleFactor;
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
		if (obj instanceof PowerOfPiConverter) {
			PowerOfPiConverter other = (PowerOfPiConverter) obj;
			return this.exponent == other.exponent;
		}
		return false;
	}

	@Override
	public final String transformationLiteral() {
		return String.format("x -> x * π^%s", exponent);
	}

	@Override
	public int compareTo(UnitConverter o)
	{
		if (this == o) return 0;
		if(this.isIdentity() && o.isIdentity()) return 0;
		if (o instanceof PowerOfPiConverter) {
			PowerOfPiConverter other = (PowerOfPiConverter) o;
			return Integer.compare(exponent, other.exponent);
		}
		return this.getClass().getName().compareTo(o.getClass().getName());
	}

	@Override
	public int hashCode()
	{
		return hashCode;
	}

}
