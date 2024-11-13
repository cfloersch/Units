package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Area;
import xpertss.measure.quantity.Length;
import xpertss.measure.quantity.Speed;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.CENTI;
import static xpertss.measure.ucum.Base.HOUR;
import static xpertss.measure.ucum.Base.LITER;
import static xpertss.measure.ucum.Base.METRE;

/**
 * British Imperial
 * <p/>
 * BRITISH IMPERIAL LENGTH UNITS: UCUM 4.4 §36
 * BRITISH IMPERIAL VOLUME UNITS: UCUM 4.4 §38
 *
 * TODO Come back and give names/symbols to Units as appropriate
 */
public final class Imperial {

    /////////////////////////////////////////////////
    // BRITISH IMPERIAL LENGTH UNITS: UCUM 4.4 §36 //
    /////////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> INCH_BRITISH = CENTI(METRE).multiply(2539998).divide(1000000);
    public static final Unit<Length> FOOT_BRITISH = INCH_BRITISH.multiply(12);
    public static final Unit<Length> ROD_BRITISH = FOOT_BRITISH.multiply(33).divide(2);
    public static final Unit<Length> CHAIN_BRITISH = ROD_BRITISH.multiply(4);
    public static final Unit<Length> LINK_BRITISH = CHAIN_BRITISH.divide(100);
    public static final Unit<Length> FATHOM_BRITISH = FOOT_BRITISH.multiply(6);
    public static final Unit<Length> PACE_BRITISH = FOOT_BRITISH.multiply(5).divide(2);
    public static final Unit<Length> YARD_BRITISH = FOOT_BRITISH.multiply(3);
    public static final Unit<Length> MILE_BRITISH = FOOT_BRITISH.multiply(5280);
    public static final Unit<Length> NAUTICAL_MILE_BRITISH = FOOT_BRITISH.multiply(6080);
    public static final Unit<Speed> KNOT_BRITISH = new ProductUnit<>(NAUTICAL_MILE_BRITISH.divide(HOUR));
    public static final Unit<Area> ACRE_BRITISH = new ProductUnit<>(YARD_BRITISH.pow(2)).multiply(4840).asType(Area.class);




    /////////////////////////////////////////////////
    // BRITISH IMPERIAL VOLUME UNITS: UCUM 4.4 §38 //
    /////////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GALLON_BRITISH = LITER.multiply(454609).divide(100000);
    public static final Unit<Volume> PECK_BRITISH = GALLON_BRITISH.multiply(2);
    public static final Unit<Volume> BUSHEL_BRITISH = PECK_BRITISH.multiply(4);
    public static final Unit<Volume> QUART_BRITISH = GALLON_BRITISH.divide(4);
    public static final Unit<Volume> PINT_BRITISH = QUART_BRITISH.divide(2);
    public static final Unit<Volume> GILL_BRITISH = PINT_BRITISH.divide(4);
    public static final Unit<Volume> FLUID_OUNCE_BRITISH = GILL_BRITISH.divide(5);
    public static final Unit<Volume> FLUID_DRAM_BRITISH = FLUID_OUNCE_BRITISH.divide(8);
    public static final Unit<Volume> MINIM_BRITISH = FLUID_DRAM_BRITISH.divide(60);


}
