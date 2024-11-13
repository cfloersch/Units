package org.xpertss.unit.types;

import org.xpertss.unit.converters.AbstractConverter;
import xpertss.measure.Dimension;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.UnitConverter;

import java.util.Map;
import java.util.Objects;

/**
 * This class represents the building blocks on top of which all others physical units
 * are created. Base units are always unscaled SI units.
 * <p/>
 * When using the {@link org.xpertss.unit.StandardModel standard model}, all seven
 * <b>SI</b> base units are dimensionally independent.
 *
 * @see <a href="http://en.wikipedia.org/wiki/SI_base_unit"> Wikipedia: SI base unit</a>
 */
public final class BaseUnit<Q extends Quantity<Q>> extends Unit<Q> {

	/**
	 * Holds the base unit dimension.
	 */
	private final Dimension dimension;

	/**
	 * Creates a base unit having the specified symbol and dimension.
	 *
	 * @param symbol the symbol of this base unit.
	 */
	public BaseUnit(String symbol, Dimension dimension)
	{
		super(symbol);
		this.dimension = dimension;
	}

	/**
	 * Creates a base unit having the specified symbol.
	 *
	 * @param symbol the symbol of this base unit.
	 */
	public BaseUnit(String symbol)
	{
		super(symbol);
		this.dimension = Dimension.NONE;
	}

	/**
	 * Creates a base unit having the specified symbol and name.
	 *
	 * @param symbol the symbol of this base unit.
	 * @param name   the name of this base unit.
	 * @throws IllegalArgumentException if the specified symbol is associated to a
	 *                                  different unit.
	 */
	public BaseUnit(String symbol, String name)
	{
		super(symbol, name);
		this.dimension = Dimension.NONE;
	}

	/**
	 * Creates a base unit having the specified symbol, name and dimension.
	 *
	 * @param symbol the symbol of this base unit.
	 * @param name   the name of this base unit.
	 * @throws IllegalArgumentException if the specified symbol is associated to a
	 *                                  different unit.
	 * @since 2.0
	 */
	public BaseUnit(String symbol, String name, Dimension dimension)
	{
		super(symbol, name);
		this.dimension = dimension;
	}


	@Override
	public Dimension getDimension()
	{
		return dimension;
	}

	@Override
	public Unit<Q> getSystemUnit()
	{
		return this;
	}

	@Override
	public UnitConverter toSystemUnit()
		throws UnsupportedOperationException
	{
		return AbstractConverter.IDENTITY;
	}



	public Map<? extends Unit<Q>, Integer> getBaseUnits()
	{
		// TODO Shall we return null, empty list or what (e.g. Optional from Java 8)?
		return null;
	}



	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
		// result = result + prime * result + ((name == null) ? 0 : name.hashCode());
		result = result + prime * result + ((dimension == null) ? 0 : dimension.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		BaseUnit other = (BaseUnit) obj;
		return Objects.equals(dimension, other.dimension) && Objects.equals(getSymbol(), other.getSymbol());
	}


}
