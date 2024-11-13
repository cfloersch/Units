package xpertss.measure.ucum;

import org.xpertss.unit.types.ProductUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.*;

import static xpertss.measure.MetricPrefix.CENTI;
import static xpertss.measure.ucum.Base.*;
import static xpertss.measure.ucum.SI.*;

/**
 * Centimeter-Gram-Second
 * <p/>
 * CGS UNITS: UCUM 4.3 §33
 */
public final class CGS {


    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<WaveNumber> KAYSER = RECIPROCAL_METRE.divide(100);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Acceleration> GAL = new ProductUnit<>(CENTI(METRE).divide(SECOND.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> DYNE = new ProductUnit<>(GRAM.multiply(CENTI(METRE).divide(SECOND.pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> ERG = new ProductUnit<>(DYNE.multiply(CENTI(METRE)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<DynamicViscosity> POISE = new ProductUnit<>(DYNE.multiply(SECOND).divide(CENTI(METRE).pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCurrent> BIOT = AMPERE.multiply(10);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<KinematicViscosity> STOKES = new ProductUnit<>(CENTI(METRE).pow(2).divide(SECOND));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFlux> MAXWELL = WEBER.divide(1E8);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFluxDensity> GAUSS = TESLA.divide(1E4);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFieldStrength> OERSTED = new ProductUnit<>(SI.AMPERE_PER_METRE.multiply(250).divide(PI));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagnetomotiveForce> GILBERT = new ProductUnit<>(OERSTED.multiply(CENTI(METRE)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Luminance> STILB = new ProductUnit<>(CANDELA.divide(CENTI(METRE).pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Luminance> LAMBERT = new ProductUnit<>(STILB.divide(PI));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Illuminance> PHOT = LUX.divide(1E4);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Radioactivity> CURIE = BECQUEREL.multiply(3.7E10);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<IonizingRadiation> ROENTGEN = COULOMB_PER_KILOGRAM.multiply(2.58E-4);
    // add later when JMQ issue fixed
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseAbsorbed> RAD = new ProductUnit<>(ERG.divide(GRAM.multiply(100)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseEffective> REM = new ProductUnit<>(ERG.divide(GRAM.multiply(100)));

}
