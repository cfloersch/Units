package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Figure formed by two lines diverging from a common point. The metric system unit
 * for this quantity is "rad" (radian).
 *
 * @see SolidAngle
 * @see Length
 * @see AngularSpeed
 */
public interface Angle extends Quantity<Angle> {

   public static final Unit<Angle> UNIT = SI.RADIAN;

}
