package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;


/**
 * Degree of magnetization of a material that responds linearly to an applied magnetic field.
 * The system unit for this quantity is "H/m" (henry per meter).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Permeability_(electromagnetism)">Wikipedia: Permeability</a>
 *
 * TODO Beyond spec
 */
public interface MagneticPermeability extends Quantity<MagneticPermeability> {

   public static final Unit<MagneticPermeability> UNIT = null; // TODO What is it really?
}
