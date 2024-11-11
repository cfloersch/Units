package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;

/**
 * Rate of change of angular displacement with respect to time. The system unit for this quantity
 * is "rad/s" (radian per second).
 * <p/>
 * <cite>Angular speed</cite> is a scalar value, while <cite>angular velocity</cite> is a pseudo-
 * vector. The angular speed is the magnitude of the angular velocity pseudo-vector, or the
 * components of the angular velocity pseudo-vector.
 *
 * @see Angle
 * @see Time
 * @see AngularAcceleration
 * @see Speed
 * @see <a href="http://en.wikipedia.org/wiki/Angular_speed">Wikipedia: Angular Speed</a>
 *
 * TODO Beyond spec
 */
public interface AngularSpeed extends Quantity<AngularSpeed> {

   public static final Unit<AngularSpeed> UNIT = null;   // TODO

}
