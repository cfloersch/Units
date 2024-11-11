package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Electric inductance. The metric system unit for this quantity is "H" (Henry).
 */
public interface ElectricInductance extends Quantity<ElectricInductance> {

   public static final Unit<ElectricInductance> UNIT = SI.HENRY;

}
