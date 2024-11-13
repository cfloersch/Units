package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Magnetic flux density. The metric system unit for this quantity is "T" (Tesla).
 */
public interface MagneticFluxDensity extends Quantity<MagneticFluxDensity> {

   public static final Unit<MagneticFluxDensity> UNIT = SI.TESLA;

}
