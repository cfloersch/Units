package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Energy;
import xpertss.measure.quantity.Power;
import xpertss.measure.quantity.Temperature;

import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.ucum.Base.KELVIN;
import static xpertss.measure.ucum.Base.SECOND;
import static xpertss.measure.ucum.SI.JOULE;
import static xpertss.measure.ucum.customary.Avoirdupois.POUND_FORCE;
import static xpertss.measure.ucum.customary.International.FOOT_INTERNATIONAL;

/**
 * Other Legacy Units
 * <p/>
 * OTHER LEGACY UNITS: UCUM 4.5 §43
 */
public final class Legacy {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Temperature> RANKINE = KELVIN.divide(9).multiply(5);
   public static final Unit<Temperature> FAHRENHEIT = RANKINE.shift(459.67);
   public static final Unit<Temperature> REAUMUR = KELVIN.multiply(4).divide(5).shift(218.52);
   public static final Unit<Energy> CALORIE_AT_15C = JOULE.multiply(41858).divide(10000);
   public static final Unit<Energy> CALORIE_AT_20C = JOULE.multiply(41819).divide(10000);
   public static final Unit<Energy> CALORIE_MEAN = JOULE.multiply(419002).divide(100000);
   public static final Unit<Energy> CALORIE_INTERNATIONAL_TABLE = JOULE.multiply(41868).divide(10000);
   public static final Unit<Energy> CALORIE_THERMOCHEMICAL = JOULE.multiply(4184).divide(1000);
   public static final Unit<Energy> CALORIE = CALORIE_THERMOCHEMICAL;
   public static final Unit<Energy> CALORIE_FOOD = KILO(CALORIE_THERMOCHEMICAL);

   public static final Unit<Energy> BTU_AT_39F = KILO(JOULE).multiply(105967).divide(100000);
   public static final Unit<Energy> BTU_AT_59F = KILO(JOULE).multiply(105480).divide(100000);
   public static final Unit<Energy> BTU_AT_60F = KILO(JOULE).multiply(105468).divide(100000);
   public static final Unit<Energy> BTU_MEAN = KILO(JOULE).multiply(105587).divide(100000);
   public static final Unit<Energy> BTU_INTERNATIONAL_TABLE = KILO(JOULE).multiply(105505585262L).divide(100000000000L);
   public static final Unit<Energy> BTU_THERMOCHEMICAL = KILO(JOULE).multiply(105435).divide(100000);
   public static final Unit<Energy> BTU = BTU_THERMOCHEMICAL;
   public static final Unit<Power> HORSEPOWER = new ProductUnit<Power>(FOOT_INTERNATIONAL.multiply(POUND_FORCE).divide(SECOND));


}
