package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Illuminance. The metric system unit for this quantity is "lx" (lux).
 */
public interface Illuminance extends Quantity<Illuminance> {

   public static final Unit<Illuminance> UNIT = SI.LUX;

}
