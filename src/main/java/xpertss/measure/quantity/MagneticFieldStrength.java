package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Magnetic field strength. The system unit for this quantity is "A/m"
 * (ampere per meter).
 */
public interface MagneticFieldStrength extends Quantity<MagneticFieldStrength> {

   public static final Unit<MagneticFieldStrength> UNIT = SI.AMPERES_PER_METRE;

}
