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
package org.xpertss.unit.types;

import org.xpertss.unit.AbstractUnit;
import org.xpertss.unit.converters.AbstractConverter;
import xpertss.measure.Dimension;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.UnitConverter;

import java.util.Objects;

/**
 * This class represents the building blocks on top of which all others physical units
 * are created. Base units are always unscaled SI units.
 * <p/>
 * When using the {@link tech.units.indriya.spi.StandardModel standard model}, all
 * seven <b>SI</b> base units are dimensionally independent.
 *
 * @see <a href="http://en.wikipedia.org/wiki/SI_base_unit"> Wikipedia: SI base unit</a>
 */
public final class BaseUnit<Q extends Quantity<Q>> extends AbstractUnit<Q> {

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
