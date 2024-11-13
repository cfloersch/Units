package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * How an electric field affects, and is affected, by a dielectric medium. The system unit
 * for this quantity is "F/m" (farads per meter).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Electric_permittivity">Wikipedia: Electric
 *   Permittivity</a>
 */
public interface ElectricPermittivity extends Quantity<ElectricPermittivity> {

   public static final Unit<ElectricPermittivity> UNIT = SI.FARADS_PER_METRE;

}
