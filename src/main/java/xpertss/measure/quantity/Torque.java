package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;


/**
 * Moment of a force. The system unit for this quantity is "N.m" (Newton-Metre).
 * <p/>
 * <b>Note:</b> The Newton-metre ("N.m") is also a way of expressing a Joule
 * (unit of energy). However, torque is not energy. So, to avoid confusion, we
 * will use the units "N.m" for torque and not "J". This distinction occurs due
 * to the scalar nature of energy and the vector nature of torque.
 *
 * @see Force
 * @see Length
 *
 * TODO Beyond spec
 */
public interface Torque extends Quantity<Torque> {

   public static final Unit<Torque> UNIT = null;   // TODO ?? Remove this???

}
