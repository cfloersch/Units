package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Amount of electric charge flowing past a specified circuit point per unit time.
 * The metric system unit for this quantity is "A" (Ampere).
 *
 * @see ElectricCharge
 * @see Time
 */
public interface ElectricCurrent extends Quantity<ElectricCurrent> {

   public static final Unit<ElectricCurrent> UNIT = SI.AMPERE;

}
