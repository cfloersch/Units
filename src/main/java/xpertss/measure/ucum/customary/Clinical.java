package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Drag;
import xpertss.measure.quantity.Pressure;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.MetricPrefix.MILLI;
import static xpertss.measure.ucum.Base.LITER;
import static xpertss.measure.ucum.Base.METRE;
import static xpertss.measure.ucum.Base.MINUTE;
import static xpertss.measure.ucum.Base.SECOND;
import static xpertss.measure.ucum.SI.PASCAL;
import static xpertss.measure.ucum.customary.International.INCH_INTERNATIONAL;

/**
 * CLINICAL MEDICINE UNITS: UCUM 4.5 §44
 */
public final class Clinical {

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Pressure> METER_OF_WATER_COLUMN = KILO(PASCAL).multiply(980665).divide(100000);
   public static final Unit<Pressure> METER_OF_MERCURY_COLUMN = KILO(PASCAL).multiply(1333220).divide(10000);
   public static final Unit<Pressure> INCH_OF_WATER_COLUMN = new ProductUnit<Pressure>(METER_OF_WATER_COLUMN.multiply(INCH_INTERNATIONAL).divide(METRE));
   public static final Unit<Pressure> INCH_OF_MERCURY_COLUMN = new ProductUnit<Pressure>(METER_OF_MERCURY_COLUMN.multiply(INCH_INTERNATIONAL).divide(METRE));

   public static final Unit<Drag> PERIPHERAL_VASCULAR_RESISTANCE = MILLI(METER_OF_MERCURY_COLUMN).multiply(SECOND).divide(MILLI(LITER)).asType(Drag.class);
   public static final Unit<Drag> WOOD = MILLI(METER_OF_MERCURY_COLUMN).multiply(MINUTE).divide(LITER).asType(Drag.class);

   public static final Unit<Volume> DROP = MILLI(LITER).divide(20);


}
