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
import xpertss.measure.*;

import java.util.Objects;

/**
 * This class represents units used in expressions to distinguish between quantities of
 * a different nature but of the same dimensions.
 * <p/>
 * Examples of alternate units:
 * <code>
 *   Unit<Angle> RADIAN = AlternateUnit.of(ONE, "rad").asType(Angle.class);
 *   Unit<Force> NEWTON = AlternateUnit.of(METRE.multiply(KILOGRAM).divide(SECOND.pow(2)), "N").asType(Force.class);
 *   Unit<Pressure> PASCAL = AlternateUnit.of(NEWTON.divide(METRE.pow(2), "Pa").asType(Pressure.class);
 * </code>
 */
public final class AlternateUnit<Q extends Quantity<Q>> extends AbstractUnit<Q> {

	private String name;

	/**
	 * Holds the parent unit (a system unit).
	 */
	private final Unit<?> parentUnit;

	/**
	 * Creates an alternate unit for the specified system unit identified by the
	 * specified name and symbol.
	 *
	 * @param parentUnit the system unit from which this alternate unit is derived.
	 * @param symbol     the symbol for this alternate unit.
	 * @throws IllegalArgumentException if the specified parent unit is not an
	 *                                  {@link Unit#isSystemUnit() system
	 *                                  unit}
	 */
	@SuppressWarnings("rawtypes")
	public AlternateUnit(Unit<?> parentUnit, String symbol)
	{
		super(symbol);
		if (!parentUnit.isSystemUnit())
			throw new IllegalArgumentException("The parent unit: " + parentUnit + " is not an unscaled SI unit");

		this.parentUnit = parentUnit instanceof AlternateUnit ? ((AlternateUnit) parentUnit).getParentUnit()
				: parentUnit;
	}

	/**
	 * Creates an alternate unit for the specified system unit identified by the
	 * specified name and symbol.
	 *
	 * @param parentUnit the system unit from which this alternate unit is derived.
	 * @param symbol     the symbol for this alternate unit.
	 * @param name       the name for this alternate unit.
	 * @throws IllegalArgumentException if the specified parent unit is not an
	 *                                  {@link Unit#isSystemUnit() system
	 *                                  unit}
	 */
	AlternateUnit(Unit<?> parentUnit, String symbol, String name)
	{
		this(parentUnit, symbol);
		this.name = name;
	}


	public String getName()
	{
		return name;
	}


	/**
	 * Returns the parent unit of this alternate unit, always a system unit and
	 * never an alternate unit.
	 *
	 * @return the parent unit.
	 */
	public Unit<?> getParentUnit()
	{
		return parentUnit;
	}

	@Override
	public Dimension getDimension()
	{
		return parentUnit.getDimension();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public UnitConverter toSystemUnit()
	{
		return parentUnit.toSystemUnit();
	}

	@Override
	public Unit<Q> getSystemUnit()
	{
		return this; // Alternate units are SI units.
	}




	@Override
	public int hashCode()
	{
		return Objects.hash(parentUnit, getSymbol());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj instanceof AlternateUnit) {
			AlternateUnit that = (AlternateUnit) obj;
			return Objects.equals(parentUnit, that.parentUnit) && Objects.equals(getSymbol(), that.getSymbol());
		}
		return false;
	}

	/**
     * Creates an alternate unit for the specified system unit identified by the specified name and symbol.
     *
     * @param parent
     *            the system unit from which this alternate unit is derived.
     * @param symbol
     *            the symbol for this alternate unit.
     * @throws IllegalArgumentException
     *             if the specified parent unit is not an unscaled standard {@link Unit#isSystemUnit() system unit}.
     * @throws MeasurementException
     *           if the specified symbol is not valid or is already associated to a different unit.
     */
    public static <Q extends Quantity<Q>> AlternateUnit<Q> of(Unit<?> parent, String symbol)
	{
        return new AlternateUnit<>(parent, symbol);
    }

	/**
     * Creates an alternate unit for the specified system unit identified by the specified name and symbol.
     *
     * @param parent
     *            the system unit from which this alternate unit is derived.
     * @param symbol
     *            the symbol for this alternate unit.
     * @param name       the name for this alternate unit.
     * @throws IllegalArgumentException
     *             if the specified parent unit is not an unscaled standard {@link Unit#isSystemUnit() system unit}.
     * @throws MeasurementException
     *           if the specified symbol is not valid or is already associated to a different unit.
     * @since 2.0
     */
    public static <Q extends Quantity<Q>> AlternateUnit<Q> of(Unit<?> parent, String symbol, String name)
	{
        return new AlternateUnit<>(parent, symbol, name);
    }
}
