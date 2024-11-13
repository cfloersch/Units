/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.Base;


/**
 * Luminous flux density per solid angle as measured in a given direction
 * relative to the emitting source. The metric system unit for this quantity
 * is "cd" (candela).
 *
 * @see Luminance
 */
public interface LuminousIntensity extends Quantity<LuminousIntensity> {

   public static final Unit<LuminousIntensity> UNIT = Base.CANDELA;

}
