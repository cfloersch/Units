package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Quantity that tends to produce an acceleration of a body in the direction of its application.
 * The metric system unit for this quantity is "N" (Newton).
 *
 * @see Energy
 * @see Pressure
 * @see Torque
 */
public interface Force extends Quantity<Force> {

   public static final Unit<Force> UNIT = SI.NEWTON;

}
