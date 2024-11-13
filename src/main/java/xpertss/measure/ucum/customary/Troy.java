package xpertss.measure.ucum.customary;

import xpertss.measure.Unit;
import xpertss.measure.quantity.Mass;

import static xpertss.measure.ucum.customary.Avoirdupois.GRAIN;

/**
 * Troy Weights
 * <p/>
 * TROY WEIGHT UNITS: UCUM 4.4 §40
 */
public final class Troy {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> PENNYWEIGHT_TROY = GRAIN.multiply(24);
   public static final Unit<Mass> OUNCE_TROY = PENNYWEIGHT_TROY.multiply(20);
   public static final Unit<Mass> POUND_TROY = OUNCE_TROY.multiply(12);

}
