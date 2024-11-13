package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.Base;


/**
 * Measure of the quantity of matter that a body or an object contains. The mass of the
 * body is not dependent on gravity and therefore is different from but proportional to
 * its weight. The Unified Code for Units of Measure unit for this quantity is "g" (gram).
 */
public interface Mass extends Quantity<Mass> {

   public static final Unit<Mass> UNIT = Base.GRAM;

}
