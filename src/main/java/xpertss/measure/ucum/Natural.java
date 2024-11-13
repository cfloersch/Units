package xpertss.measure.ucum;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.*;

import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.ucum.Base.*;
import static xpertss.measure.ucum.SI.*;

/**
 * UCUM Natural Units
 * <p/>
 * NATURAL UNITS: UCUM 4.3 §32
 *
 * TODO Come back and give names/symbols to Units as appropriate
 */
public final class Natural {

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Speed> VELOCITY_OF_LIGHT = METRE_PER_SECOND.multiply(299792458);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Action> PLANCK = JOULE_SECOND.multiply(6.6260755E-34);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<?> BOLTZMAN = JOULE.divide(KELVIN).multiply(1.380658E-23);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricPermittivity> PERMITTIVITY_OF_VACUUM = FARAD_PER_METRE.multiply(8.854187817E-12);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM =
            new ProductUnit<>(NEWTON_PER_SQUARE_AMPERE.multiply(PI.multiply(4).divide(1E7)));


    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCharge> ELEMENTARY_CHARGE = COULOMB.transform((ELECTRON_VOLT).toSystemUnit());
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> ELECTRON_MASS = GRAM.multiply(9.1093897E-28);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> PROTON_MASS = GRAM.multiply(1.6726231E-24);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<?> NEWTON_CONSTANT_OF_GRAVITY = METRE.pow(3).multiply(KILO(GRAM).pow(-1))
                                                                .multiply(SECOND.pow(-2)).multiply(6.67259E-11);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Acceleration> ACCELERATION_OF_FREEFALL = METRE_PER_SQUARE_SECOND.multiply(9.80665);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> ATMOSPHERE = PASCAL.multiply(101325);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LIGHT_YEAR = new ProductUnit<>(VELOCITY_OF_LIGHT.multiply(YEAR_JULIAN));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> GRAM_FORCE = new ProductUnit<>(GRAM.multiply(ACCELERATION_OF_FREEFALL));

    // POUND_FORCE contains a forward reference to avoirdupois pound weight, so
    // it has been moved after section §39 below


}
