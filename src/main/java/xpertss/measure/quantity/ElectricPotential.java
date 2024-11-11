package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Electric potential or electromotive force. The metric system unit for this quantity
 * is "V" (Volt).
 */
public interface ElectricPotential extends Quantity<ElectricPotential> {

   public static final Unit<ElectricPotential> UNIT = SI.VOLT;

}
