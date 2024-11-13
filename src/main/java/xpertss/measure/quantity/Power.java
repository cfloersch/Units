package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Rate at which work is done. The metric system unit for this quantity is "W" (Watt).
 *
 * @see Energy
 * @see Time
 */
public interface Power extends Quantity<Power> {

   public static final Unit<Power> UNIT = SI.WATT;

}
