package xpertss.measure.ucum.customary;

import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Area;
import xpertss.measure.quantity.Length;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.MILLI;
import static xpertss.measure.ucum.Base.LITER;
import static xpertss.measure.ucum.Base.METRE;
import static xpertss.measure.ucum.customary.International.CUBIC_FOOT_INTERNATIONAL;
import static xpertss.measure.ucum.customary.International.CUBIC_INCH_INTERNATIONAL;

/**
 * US Customary
 * <p/>
 * US SURVEY LENGTH UNITS: UCUM 4.4 §35
 * US VOLUME UNITS: UCUM 4.4 §37
 *
 * TODO Come back and give names/symbols to ProductUnits
 */
public final class US {

    //////////////////////////////////////////
    // US SURVEY LENGTH UNITS: UCUM 4.4 §35 //
    //////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FOOT_US_SURVEY = AlternateUnit.of(METRE.multiply(1200).divide(3937), "ft_us", "foot");
    public static final Unit<Length> YARD_US_SURVEY = AlternateUnit.of(FOOT_US_SURVEY.multiply(3), "yd_us", "yard");
    public static final Unit<Length> INCH_US_SURVEY = AlternateUnit.of(FOOT_US_SURVEY.divide(12), "in_us", "inch");
    public static final Unit<Length> ROD_US_SURVEY = AlternateUnit.of(FOOT_US_SURVEY.multiply(33).divide(2), "rd_us", "rod");
    public static final Unit<Length> CHAIN_US_SURVEY = AlternateUnit.of(ROD_US_SURVEY.multiply(4), "ch_us", "chain");
    public static final Unit<Length> LINK_US_SURVEY = AlternateUnit.of(CHAIN_US_SURVEY.divide(100), "lk_us", "link chain");
    public static final Unit<Length> RAMDEN_CHAIN_US_SURVEY = AlternateUnit.of(FOOT_US_SURVEY.multiply(100), "rch_us", "ramdens chain");
    public static final Unit<Length> RAMDEN_LINK_US_SURVEY = AlternateUnit.of(CHAIN_US_SURVEY.divide(100), "rlk_us", "radmens link chain");
    public static final Unit<Length> FATHOM_US_SURVEY = AlternateUnit.of(FOOT_US_SURVEY.multiply(6), "fth_us", "fathom");
    public static final Unit<Length> FURLONG_US_SURVEY = AlternateUnit.of(ROD_US_SURVEY.multiply(40), "fur_us", "furlong");
    public static final Unit<Length> MILE_US_SURVEY = AlternateUnit.of(FURLONG_US_SURVEY.multiply(8), "mi_us", "mile");

    public static final Unit<Area> ACRE_US_SURVEY = new ProductUnit<>(ROD_US_SURVEY.pow(2)).multiply(160).asType(Area.class);

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard.
     * @deprecated Per [http://unitsofmeasure.org/ucum.html#para-34](§34 international customary units)
     */
    public static final Unit<Area> SQUARE_ROD_US_SURVEY = new ProductUnit<>(ROD_US_SURVEY.pow(2));


    public static final Unit<Area> SQUARE_MILE_US_SURVEY = new ProductUnit<>(MILE_US_SURVEY.pow(2));
    public static final Unit<Area> SECTION_US_SURVEY = new ProductUnit<>(MILE_US_SURVEY.pow(2));
    public static final Unit<Area> TOWNSHP_US_SURVEY = AlternateUnit.of(SECTION_US_SURVEY.multiply(36), "twp", "township");
    public static final Unit<Length> MIL_US_SURVEY = AlternateUnit.of(INCH_US_SURVEY.divide(1000), "mil_us", "mil");




    ///////////////////////////////////
    // US VOLUME UNITS: UCUM 4.4 §37 //
    ///////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GALLON_US = AlternateUnit.of(CUBIC_INCH_INTERNATIONAL.multiply(231), "gal_us", "gallon");
    public static final Unit<Volume> BARREL_US = AlternateUnit.of(GALLON_US.multiply(42), "bbl_us", "barrel");
    public static final Unit<Volume> QUART_US = AlternateUnit.of(GALLON_US.divide(4), "qt_us", "quart");
    public static final Unit<Volume> PINT_US = AlternateUnit.of(QUART_US.divide(2), "pt_us", "pint");
    public static final Unit<Volume> GILL_US = AlternateUnit.of(PINT_US.divide(4), "gil_us", "gill");
    public static final Unit<Volume> FLUID_OUNCE_US = AlternateUnit.of(GILL_US.divide(4), "foz_us", "fluid ounce");
    public static final Unit<Volume> FLUID_DRAM_US = AlternateUnit.of(FLUID_OUNCE_US.divide(8), "fdr_us", "fluid dram");
    public static final Unit<Volume> MINIM_US = AlternateUnit.of(FLUID_DRAM_US.divide(60), "min_us", "minim");
    public static final Unit<Volume> CORD_US = AlternateUnit.of(CUBIC_FOOT_INTERNATIONAL.multiply(128), "crd_us", "cord");
    public static final Unit<Volume> BUSHEL_US = AlternateUnit.of(CUBIC_INCH_INTERNATIONAL.multiply(215042).divide(100), "bu_us", "bushel");
    public static final Unit<Volume> GALLON_WINCHESTER = AlternateUnit.of(BUSHEL_US.divide(8), "gal_wi", "winchester gallon");
    public static final Unit<Volume> PECK_US = AlternateUnit.of(BUSHEL_US.divide(4), "pk_us", "peck");
    public static final Unit<Volume> DRY_QUART_US = AlternateUnit.of(PECK_US.divide(8), "dqt_us", "dry quart");
    public static final Unit<Volume> DRY_PINT_US = AlternateUnit.of(DRY_QUART_US.divide(2), "dpt_us", "dry pint");
    public static final Unit<Volume> TABLESPOON_US = AlternateUnit.of(FLUID_OUNCE_US.divide(2), "tbs_us", "tablespoon");
    public static final Unit<Volume> TEASPOON_US = AlternateUnit.of(TABLESPOON_US.divide(3), "tsp_us", "teaspoon");
    public static final Unit<Volume> CUP_US = AlternateUnit.of(TABLESPOON_US.multiply(16), "cup_us", "cup");
    public static final Unit<Volume> METRIC_FLUID_OUNCE_US = AlternateUnit.of(MILLI(LITER).multiply(30), "foz_m", "metric fluid ounce");
    public static final Unit<Volume> METRIC_CUP_US = AlternateUnit.of(MILLI(LITER).multiply(240), "cup_m", "metric cup");
    public static final Unit<Volume> METRIC_TEASPOON_CUP_US = AlternateUnit.of(MILLI(LITER).multiply(5), "tsp_m", "metric teaspoon");
    public static final Unit<Volume> METRIC_TABLESPOON_CUP_US = AlternateUnit.of(MILLI(LITER).multiply(15), "tbs_m", "metric tablespoon");



}
