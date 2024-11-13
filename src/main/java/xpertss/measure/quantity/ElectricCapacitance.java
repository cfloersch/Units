package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Electric capacitance. The metric system unit for this quantity is "F" (Farad).
 */
public interface ElectricCapacitance extends Quantity<ElectricCapacitance> {

   public static final Unit<ElectricCapacitance> UNIT = SI.FARAD;

}
