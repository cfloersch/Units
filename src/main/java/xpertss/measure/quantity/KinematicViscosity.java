package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Diffusion of momentum. The system unit for this quantity is "m²/s".
 *
 * @see DynamicViscosity
 * @see <a href="http://en.wikipedia.org/wiki/Viscosity">Wikipedia: Viscosity</a>
 *
 * TODO Beyond spec
 */
public interface KinematicViscosity extends Quantity<KinematicViscosity> {

   public static final Unit<KinematicViscosity> UNIT = SI.SQUARE_METRES_PER_SECOND;

}
