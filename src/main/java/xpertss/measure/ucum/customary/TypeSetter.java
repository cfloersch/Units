package xpertss.measure.ucum.customary;

import xpertss.measure.Unit;
import xpertss.measure.quantity.Length;

import static xpertss.measure.MetricPrefix.CENTI;
import static xpertss.measure.ucum.Base.METRE;
import static xpertss.measure.ucum.customary.International.INCH_INTERNATIONAL;

/**
 * TypeSetter's lengths
 * <p/>
 * TYPESETTER'S LENGTH UNITS: UCUM 4.4 §42
 */
public final class TypeSetter {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> LINE = INCH_INTERNATIONAL.divide(12);
   public static final Unit<Length> POINT = LINE.divide(6);
   public static final Unit<Length> PICA = POINT.multiply(12);
   public static final Unit<Length> POINT_PRINTER = INCH_INTERNATIONAL.multiply(13837).divide(1000000);
   public static final Unit<Length> PICA_PRINTER = POINT_PRINTER.multiply(12);
   public static final Unit<Length> PIED = CENTI(METRE).multiply(3248).divide(100);
   public static final Unit<Length> POUCE = PIED.divide(12);
   public static final Unit<Length> LIGNE = POUCE.divide(12);
   public static final Unit<Length> DIDOT = LIGNE.divide(6);
   public static final Unit<Length> CICERO = DIDOT.multiply(12);


}
