package xpertss.measure.ucum.customary;

import xpertss.measure.Unit;
import xpertss.measure.quantity.Force;
import xpertss.measure.quantity.Mass;

import static xpertss.measure.MetricPrefix.MILLI;
import static xpertss.measure.ucum.Base.GRAM;
import static xpertss.measure.ucum.Natural.ACCELERATION_OF_FREEFALL;

/**
 *  Avoirdupois weights
 *  <p/>
 *  AVOIRDUPOIS WEIGHT UNITS: UCUM 4.4 §39
 */
public final class Avoirdupois {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> GRAIN = MILLI(GRAM).multiply(6479891).divide(100000);
   public static final Unit<Mass> POUND = GRAIN.multiply(7000);
   public static final Unit<Mass> OUNCE = POUND.divide(16);
   public static final Unit<Mass> DRAM = OUNCE.divide(16);
   public static final Unit<Mass> SHORT_HUNDREDWEIGHT = POUND.multiply(100);
   public static final Unit<Mass> LONG_HUNDREDWEIGHT = POUND.multiply(112);
   public static final Unit<Mass> SHORT_TON = SHORT_HUNDREDWEIGHT.multiply(20);
   public static final Unit<Mass> LONG_TON = LONG_HUNDREDWEIGHT.multiply(20);
   public static final Unit<Mass> STONE = POUND.multiply(14);




}
