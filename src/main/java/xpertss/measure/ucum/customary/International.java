package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Area;
import xpertss.measure.quantity.Length;
import xpertss.measure.quantity.Speed;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.CENTI;
import static xpertss.measure.ucum.Base.*;

/**
 * International Customary
 * <p/>
 * INTERNATIONAL CUSTOMARY UNITS: UCUM 4.4 §34
 *
 * TODO Come back and give names/symbols to Units as appropriate
 */
public final class International {

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> INCH_INTERNATIONAL = CENTI(METRE).multiply(254).divide(100);
    public static final Unit<Length> FOOT_INTERNATIONAL = INCH_INTERNATIONAL.multiply(12);
    public static final Unit<Length> YARD_INTERNATIONAL = FOOT_INTERNATIONAL.multiply(3);
    public static final Unit<Length> MILE_INTERNATIONAL = FOOT_INTERNATIONAL.multiply(5280);
    public static final Unit<Length> FATHOM_INTERNATIONAL = FOOT_INTERNATIONAL.multiply(6);
    public static final Unit<Length> NAUTICAL_MILE_INTERNATIONAL = METRE.multiply(1852);
    public static final Unit<Speed> KNOT_INTERNATIONAL = new ProductUnit<>(NAUTICAL_MILE_INTERNATIONAL.divide(HOUR));


    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard.
     * @deprecated Per [http://unitsofmeasure.org/ucum.html#para-34](§34 international customary units)
     */
    public static final Unit<Area> SQUARE_INCH_INTERNATIONAL =
            new ProductUnit<Area>(INCH_INTERNATIONAL.pow(2));
    public static final Unit<Area> SQUARE_FOOT_INTERNATIONAL =
            new ProductUnit<Area>(FOOT_INTERNATIONAL.pow(2));
    public static final Unit<Area> SQUARE_YARD_INTERNATIONAL =
            new ProductUnit<Area>(YARD_INTERNATIONAL.pow(2));
    public static final Unit<Volume> CUBIC_INCH_INTERNATIONAL =
            new ProductUnit<Volume>(INCH_INTERNATIONAL.pow(3));
    public static final Unit<Volume> CUBIC_FOOT_INTERNATIONAL =
            new ProductUnit<Volume>(FOOT_INTERNATIONAL.pow(3));
    public static final Unit<Volume> CUBIC_YARD_INTERNATIONAL =
            new ProductUnit<Volume>(YARD_INTERNATIONAL.pow(3));



    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> BOARD_FOOT_INTERNATIONAL = CUBIC_INCH_INTERNATIONAL.multiply(144);
    public static final Unit<Volume> CORD_INTERNATIONAL = CUBIC_FOOT_INTERNATIONAL.multiply(128);
    public static final Unit<Length> MIL_INTERNATIONAL = INCH_INTERNATIONAL.divide(1000);
    public static final Unit<Area> CIRCULAR_MIL_INTERNATIONAL = new ProductUnit<>(MIL_INTERNATIONAL.pow(2).multiply(PI.divide(4)));
    public static final Unit<Length> HAND_INTERNATIONAL = INCH_INTERNATIONAL.multiply(4);




}
