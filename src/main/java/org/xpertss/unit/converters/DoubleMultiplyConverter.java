package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * <p>
 * This class represents a converter multiplying numeric values by a constant
 * scaling factor (<code>double</code> based).
 * </p>
 */
final class DoubleMultiplyConverter extends AbstractConverter implements MultiplyConverter {

	/**
	 * Holds the scale factor.
	 */
	private final double doubleFactor;

	/**
	 * Creates a multiply converter with the specified scale factor.
	 * 
	 * @param factor
	 *            the scaling factor.
	 */
	private DoubleMultiplyConverter(double factor)
	{
		this.doubleFactor = factor;
	}

	/**
	 * Creates a multiply converter with the specified scale factor.
	 * 
	 * @param factor
	 *            the scaling factor.
	 */
	static DoubleMultiplyConverter of(double factor)
	{
		return new DoubleMultiplyConverter(factor);
	}

	@Override
	public boolean isIdentity()
	{
		return doubleFactor == 1.0;
	}

	@Override
	protected boolean canReduceWith(AbstractConverter that)
	{
		return that instanceof DoubleMultiplyConverter;
	}

	@Override
	protected AbstractConverter reduce(AbstractConverter that)
	{
		return new DoubleMultiplyConverter(doubleFactor * ((DoubleMultiplyConverter) that).doubleFactor);
	}

	@Override
	public DoubleMultiplyConverter inverseWhenNotIdentity() {
		return new DoubleMultiplyConverter(1.0 / doubleFactor);
	}

    @Override
    protected Number convertWhenNotIdentity(Number value)
	{
        return Calculator.of(doubleFactor).multiply(value).peek();
    }
	
	@Override
	public final String transformationLiteral()
	{
		return String.format("x -> x * %s", doubleFactor);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj instanceof DoubleMultiplyConverter) {
			DoubleMultiplyConverter that = (DoubleMultiplyConverter) obj;
			return Objects.equals(doubleFactor, that.doubleFactor);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(doubleFactor);
	}

	public Double getValue()
	{
		return doubleFactor;
	}

	@Override
	public int compareTo(UnitConverter o)
	{
		if (this == o) return 0;
		if (o instanceof DoubleMultiplyConverter) {
			return getValue().compareTo(((DoubleMultiplyConverter) o).getValue());
		}
		return -1;
	}
}
