package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Energy multiplied by a duration (quantity associated to the
 * <a href="http://en.wikipedia.org/wiki/Planck%27s_constant">Planck Constant</a>).
 * The system unit for this quantity is "J.s" (joules second).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Action_(physics)">Wikipedia: Action</a>
 *
 * TODO Beyond spec
 */
public interface Action extends Quantity<Action> {

   public static final Unit<Action> UNIT = SI.JOULE_SECOND;

}
