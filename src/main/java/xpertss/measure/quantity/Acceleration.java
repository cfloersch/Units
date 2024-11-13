package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Rate of change of velocity with respect to time. The metric system unit for this quantity
 * is "m/s²" (metre per square second).
 *
 * @see Length
 * @see Speed
 * @see Time
 * @see AngularAcceleration
 */
public interface Acceleration extends Quantity<Acceleration> {

   public static final Unit<Acceleration> UNIT = SI.METRE_PER_SQUARE_SECOND;

}
