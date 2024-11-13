package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Magnetic flux. The metric system unit for this quantity is "Wb" (Weber).
 */
public interface MagneticFlux extends Quantity<MagneticFlux> {

   public static final Unit<MagneticFlux> UNIT = SI.WEBER;

}
