package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Force that produces magnetic flux. The metric system unit for this quantity is "At" (ampere-turn).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Magnetomotive_force">Wikipedia: Magnetomotive Force</a>
 *
 * TODO Beyond spec
 */
public interface MagnetomotiveForce extends Quantity<MagnetomotiveForce> {

   public static final Unit<MagnetomotiveForce> UNIT = SI.AMPERE_TURN;

}
