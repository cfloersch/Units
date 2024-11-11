package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Number of times a specified phenomenon occurs within a specified interval.
 * The metric system unit for this quantity is "Hz" (Hertz).
 *
 * @see Time
 */
public interface Frequency extends Quantity<Frequency> {

   public static final Unit<Frequency> UNIT = SI.HERTZ;

}
