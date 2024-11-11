package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Measure of the quantity of matter that a body or an object contains. The mass of the
 * body is not dependent on gravity and therefore is different from but proportional to
 * its weight. The metric system unit for this quantity is "kg" (kilogram).
 */
public interface Mass extends Quantity<Mass> {

   public static final Unit<Mass> UNIT = SI.KILOGRAM;

}
