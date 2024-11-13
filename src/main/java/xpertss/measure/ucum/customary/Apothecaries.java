package xpertss.measure.ucum.customary;

import xpertss.measure.Unit;
import xpertss.measure.quantity.Mass;

import static xpertss.measure.ucum.Base.GRAM;
import static xpertss.measure.ucum.customary.Avoirdupois.GRAIN;

/**
 * Apothecaries weights
 * <p/>
 * APOTECARIES' WEIGHT UNITS: UCUM 4.4 §41
 */
public final class Apothecaries {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> SCRUPLE_APOTHECARY = GRAIN.multiply(20);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> DRAM_APOTHECARY = SCRUPLE_APOTHECARY.multiply(3);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> OUNCE_APOTHECARY = DRAM_APOTHECARY.multiply(8);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> POUND_APOTHECARY = OUNCE_APOTHECARY.multiply(12);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> METRIC_OUNCE = GRAM.multiply(28);

}
