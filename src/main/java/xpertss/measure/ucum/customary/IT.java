package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Information;
import xpertss.measure.quantity.InformationRate;

import static xpertss.measure.Unit.ONE;
import static xpertss.measure.ucum.Base.SECOND;

/**
 * INFORMATION TECHNOLOGY UNITS: UCUM 4.6 §48
 */
public final class IT {

   /**
    * The unit for binary information (standard name <code>bit</code>).
    * As per <a href="http://unitsofmeasure.org/">UCUM</a> standard.
    */
   public static final Unit<Information> BIT = new AlternateUnit<>(ONE, "bit");

   /**
    * The bit is defined twice. One definition with a subscript letter ‘s‘ is defined as the logarithmus dualis of
    * the number of distinct signals. However this unit can not practically be used to express more than 1000 bits.
    * Especially when the bit is used to express transmission rate or memory capacities, floating point registers
    * would quickly overflow. Therefore we define a second symbol for bit, without the suffix, to be the
    * dimensionless unit 1.
    */
   public static final Unit<Information> BIT_S = new AlternateUnit<>(BIT, "bit\\u2082");

   public static final Unit<Information> BYTE = BIT.multiply(8);

   /**
    * The SI unit for binary information rate (standard name <code>bit/s</code>).
    */
   protected static final ProductUnit<InformationRate> BITS_PER_SECOND = new ProductUnit<>(BIT.divide(SECOND));
   public static final Unit<InformationRate> BAUD = BITS_PER_SECOND;

}
