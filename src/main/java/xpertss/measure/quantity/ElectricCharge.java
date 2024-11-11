package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Electric charge.  The metric system unit for this quantity is "C" (Coulomb).
 *
 * @see ElectricCurrent
 */
public interface ElectricCharge extends Quantity<ElectricCharge> {

   public static final Unit<ElectricCharge> UNIT = SI.COULOMB;

}
