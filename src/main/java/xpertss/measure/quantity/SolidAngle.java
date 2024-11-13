package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Angle formed by three or more planes intersecting at a common point.
 * The metric system unit for this quantity is "sr" (steradian).
 *
 * @see Angle
 */
public interface SolidAngle extends Quantity<SolidAngle> {

   public static final Unit<SolidAngle> UNIT = SI.STERADIAN;

}
