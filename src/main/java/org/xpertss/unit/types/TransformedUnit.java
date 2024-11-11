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
import xpertss.measure.Dimension;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * This class represents the units derived from other units using
 * {@linkplain UnitConverter converters}.
 * <p/>
 * Examples of transformed units:
 * <code>
 *    CELSIUS = KELVIN.shift(273.15);
 *    FOOT = METRE.multiply(3048).divide(10000);
 *    MILLISECOND = MILLI(SECOND);
 * </code>
 * <p/>
 * Transformed units have no symbol. But like any other units, they may have labels
 * attached to them (see {@link xpertss.measure.format.UnitFormat#label(Unit, String)
 * UnitFormat.label}
 * <p/>
 * Instances of this class are created through the {@link Unit#transform} method.
 *
 * @param <Q>  The type of the quantity measured by this unit.
 */
public final class TransformedUnit<Q extends Quantity<Q>> extends AbstractUnit<Q> {

  /**
   * Holds the parent unit.
   */
  private final Unit<Q> parentUnit;

  /**
   * Holds the system unit.
   */
  private final Unit<Q> systemUnit;

  /**
   * Holds the converter to the parent unit.
   */
  private final UnitConverter converter;

  /**
   * Creates a transformed unit from the specified system unit. using the parent as symbol
   * 
   * @param parentUnit
   *          the system unit from which this unit is derived.
   * @param converter
   *          the converter to the parent units.
   */
  public TransformedUnit(Unit<Q> parentUnit, UnitConverter converter)
  {
    this(null, parentUnit, converter);
  }

  /**
   * Creates a transformed unit from the specified parent unit.
   *
   * @param symbol
   *          the symbol to use with this transformed unit.
   * @param name
   *          the name to use with this transformed unit.
   * @param parentUnit
   *          the parent unit from which this unit is derived.
   * @param unitConverter
   *          the converter to the parent units.
   */
  public TransformedUnit(String symbol, String name, Unit<Q> parentUnit, UnitConverter unitConverter)
  {
    this(symbol, name, parentUnit, parentUnit.getSystemUnit(), unitConverter);
  }
  
  /**
   * Creates a transformed unit from the specified parent unit.
   *
   * @param symbol
   *          the symbol to use with this transformed unit.
   * @param parentUnit
   *          the parent unit from which this unit is derived.
   * @param unitConverter
   *          the converter to the parent units.
   */
  public TransformedUnit(String symbol, Unit<Q> parentUnit, UnitConverter unitConverter)
  {
    this(symbol, parentUnit, parentUnit.getSystemUnit(), unitConverter);
  }

  /**
   * Creates a transformed unit from the specified parent and system unit. using a specific symbol and name.
   * 
   * @param symbol
   *          the symbol for this unit.
   * @param name
   *          the name for this unit.
   * @param parentUnit
   *          the parent unit from which this unit is derived.
   * @param sysUnit
   *          the system unit which this unit is based on.
   * @param converter
   *          the converter to the parent units.
   */
  public TransformedUnit(String symbol, String name, Unit<Q> parentUnit, Unit<Q> sysUnit, UnitConverter converter)
  {
    super(symbol, name);
    this.systemUnit = sysUnit;
    // if (!abParent.isSystemUnit()) {
    // throw new IllegalArgumentException("The parent unit: " + abParent
    // + " is not a system unit");
    // }
    this.parentUnit = parentUnit;
    this.converter = converter;
  }
  
  /**
   * Creates a transformed unit from the specified parent and system unit. using a specific symbol.
   * 
   * @param symbol
   *          the symbol for this unit.
   * @param parentUnit
   *          the parent unit from which this unit is derived.
   * @param sysUnit
   *          the system unit which this unit is based on.
   * @param converter
   *          the converter to the parent units.
   */
  public TransformedUnit(String symbol, Unit<Q> parentUnit, Unit<Q> sysUnit, UnitConverter converter)
  {
	  this(symbol, null, parentUnit, sysUnit, converter);
  }

  @Override
  public Dimension getDimension()
  {
    return parentUnit.getDimension();
  }

  public UnitConverter toSystemUnit()
  {
    return parentUnit.toSystemUnit().concatenate(converter);
  }

  /**
   * Returns the converter to the parent unit.
   *
   * @return the converter to the parent unit.
   */
  public UnitConverter getConverter()
  {
    return converter;
  }

  @Override
  public Unit<Q> getSystemUnit()
  {
    return (systemUnit != null ? systemUnit : parentUnit.getSystemUnit());
  }


  @Override
  public int hashCode()
  {
    return Objects.hash(parentUnit, converter);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj instanceof TransformedUnit) {
      TransformedUnit<?> other = (TransformedUnit<?>) obj;
      return Objects.equals(parentUnit, other.parentUnit) 
    		  && Objects.equals(converter, other.converter);
    } 
    return false;
  }

  /**
   * Returns the parent unit for this unit. The parent unit is the untransformed unit from which this unit is derived.
   *
   * @return the untransformed unit from which this unit is derived.
   */
  public Unit<Q> getParentUnit()
  {
    return parentUnit;
  }

}
