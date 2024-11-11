package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Electric resistance. The metric system unit for this quantity is "Ohm" (Ω).
 */
public interface ElectricResistance extends Quantity<ElectricResistance> {

   public static final Unit<ElectricResistance> UNIT = SI.OHM;

}
