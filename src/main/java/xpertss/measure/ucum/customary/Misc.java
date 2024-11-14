package xpertss.measure.ucum.customary;

import org.xpertss.unit.converters.MultiplyConverter;
import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.ProductUnit;
import org.xpertss.unit.types.TransformedUnit;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.quantity.*;

import static xpertss.measure.MetricPrefix.*;
import static xpertss.measure.Unit.ONE;
import static xpertss.measure.ucum.Base.*;
import static xpertss.measure.ucum.Natural.GRAM_FORCE;
import static xpertss.measure.ucum.Natural.POUND_FORCE;
import static xpertss.measure.ucum.SI.*;
import static xpertss.measure.ucum.customary.International.INCH_INTERNATIONAL;

/**
 * MISCELLANEOUS UNITS: UCUM 4.5 §47
 */
public final class Misc {


    /** temporary helper for MHO */
    private static final Unit<? extends Quantity<?>> TMP_MHO = SIEMENS.alternate("mho");

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> STERE = new TransformedUnit<>("st", CUBIC_METRE, CUBIC_METRE, MultiplyConverter.identity());
    public static final Unit<Length> ANGSTROM = NANO(METRE).divide(10);
    public static final Unit<Area> BARN = new ProductUnit<Area>(FEMTO(METRE).pow(2)).multiply(100);
    public static final Unit<Pressure> ATMOSPHERE_TECHNICAL = new ProductUnit<>(KILO(GRAM_FORCE).divide(CENTI(METRE).pow(2)));
    public static final Unit<ElectricConductance> MHO = new AlternateUnit<>(TMP_MHO, TMP_MHO.getSymbol());
    public static final Unit<Pressure> POUND_PER_SQUARE_INCH = new ProductUnit<>(POUND_FORCE.divide(INCH_INTERNATIONAL.pow(2)));
    public static final Unit<Angle> CIRCLE = new ProductUnit<>(PI.multiply(RADIAN.multiply(2)));
    public static final Unit<SolidAngle> SPHERE = new ProductUnit<>(PI.multiply(STERADIAN.multiply(4)));
    public static final Unit<Mass> CARAT_METRIC = GRAM.divide(5);
    public static final Unit<Dimensionless> CARAT_GOLD = ONE.divide(24);
    public static final Unit<Length> SMOOT = INCH_INTERNATIONAL.multiply(67);



   // TODO Do we want LEVEL Units like DECI(BEL)
   
}
