package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * This class represents a exponential converter of limited precision. Such converter
 * is used to create inverse of logarithmic unit.
 * <p/>
 * This class is package private, instances are created using the {@link LogConverter#inverse()}
 * method.
 *
 */
public final class ExpConverter extends AbstractConverter {

	/**
	 * Holds the logarithmic base.
	 */
	private final double base;

	/**
	 * Holds the natural logarithm of the base.
	 */
	private final double logOfBase;

	/**
	 * Creates a logarithmic converter having the specified base.
	 *
	 * @param base
	 *          the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
	 */
	public ExpConverter(double base)
	{
		this.base = base;
		this.logOfBase = Math.log(base);
	}

	/**
	 * Creates a logarithmic converter having the specified base.
	 *
	 * @param base
	 *          the logarithmic base (e.g. <code>Math.E</code> for the Natural Logarithm).
	 */
	public static ExpConverter of(double base)
	{
		return new ExpConverter(base);
	}

	/**
	 * Returns the exponential base of this converter.
	 *
	 * @return the exponential base (e.g. <code>Math.E</code> for the Natural Exponential).
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
		if(that instanceof LogConverter) {
			return ((LogConverter)that).getBase() == base; // can compose with log to identity, provided it has same base
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
		return new LogConverter(base);
	}

	@Override
	public final String transformationLiteral()
	{
		if (base == Math.E) return "x -> e^x";

		if (base<0) return String.format("x -> (%s)^x", base);

		return String.format("x -> %s^x", base);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj instanceof ExpConverter) {
			ExpConverter that = (ExpConverter) obj;
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
        return Calculator.of(logOfBase)
              .multiply(value)
              .exp()
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

	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo(UnitConverter o)
	{
		if (this == o) return 0;
		if (o instanceof ExpConverter) {
			// TODO Should this have worked with LogConverter
			return getValue().compareTo(String.valueOf(((ExpConverter) o).getValue()));
		}
		return -1;
	}


}
