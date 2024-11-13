package xpertss.measure.ucum;

import org.xpertss.unit.converters.MultiplyConverter;
import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.BaseUnit;
import org.xpertss.unit.types.ProductUnit;
import org.xpertss.unit.types.TransformedUnit;
import xpertss.measure.Dimension;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Angle;
import xpertss.measure.quantity.Area;
import xpertss.measure.quantity.Dimensionless;
import xpertss.measure.quantity.ElectricCharge;
import xpertss.measure.quantity.Energy;
import xpertss.measure.quantity.Length;
import xpertss.measure.quantity.LuminousIntensity;
import xpertss.measure.quantity.Mass;
import xpertss.measure.quantity.Pressure;
import xpertss.measure.quantity.Temperature;
import xpertss.measure.quantity.Time;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.DECI;
import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.ucum.SI.CUBIC_METRE;
import static xpertss.measure.ucum.SI.JOULE;
import static xpertss.measure.ucum.SI.PASCAL;
import static xpertss.measure.ucum.SI.SQUARE_METRE;

/**
 * Unified Code for Units of Measure (UCUM)
 * <p/>
 * The UCUM Base Units, Derived Units, Other Units
 *
 * BASE UNITS: UCUM 4.2 §28
 * DIMENSIONLESS DERIVED UNITS: UCUM 4.3 §29
 * OTHER UNITS FROM ISO 1000, ISO 2955, AND ANSI X3.50: UCUM 4.3 §31
 */
public class Base {

   //////////////////////////////
   // BASE UNITS: UCUM 4.2 §28 //
   //////////////////////////////

   /**
    * The metre, symbol m, is the SI unit of length. It is defined by taking the
    * fixed numerical value of the speed of light in vacuum c to be 299 792 458
    * when expressed in the unit m s⁻¹, where the second is defined in terms of the
    * caesium frequency ∆νCs.
    * <p/>
    * This definition implies the exact relation c = 299 792 458 m s⁻¹. Inverting
    * this relation gives an exact expression for the metre in terms of the
    * defining constants c and ∆νCs:
    * <p/>
    * 1 m = (c / 299 792 458)s = 9 192 631 770 c / 299 792 458 ∆νCs ≈ 30.663 319 c
    * / ∆νCs
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    */
   public static final Unit<Length> METRE = new BaseUnit<>("m", "Metre", Dimension.LENGTH);


   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The second, symbol s, is the SI unit of time. It is defined by taking the
    * fixed numerical value of the caesium frequency ∆νCs, the unperturbed
    * ground-state hyperfine transition frequency of the caesium 133 atom, to be 9
    * 192 631 770 when expressed in the unit Hz, which is equal to s⁻¹.
    * <p/>
    * This definition implies the exact relation ∆νCs = 9 192 631 770 Hz. Inverting
    * this relation gives an expression for the unit second in terms of the
    * defining constant ∆νCs:
    * <p/>
    * 1 Hz = ∆νCs / 9 192 631 770 or 1 s = 9 192 631 770 / ∆νCs
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    */
   public static final Unit<Time> SECOND = new BaseUnit<>("s", "Second", Dimension.TIME);


   /**
    * The gram, symbol g, is the SI unit of mass.
    * <p/>
    * The Unified Code for Units of Measure is different from the system used by the Système
    * International d'Unités (SI). The SI base unit kilogram has been replaced by gram for the
    * dimension of Mass.
    * 
    * @see <a href="https://en.wikipedia.org/wiki/Gram">Wikipedia: Gram</a>
    */
   public static final Unit<Mass> GRAM = new BaseUnit<>("g", "Gram", Dimension.MASS);


   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for plane angle quantities (standard name <code>rad</code>). One
    * radian is the angle between two radii of a circle such that the length of the
    * arc between them is equal to the radius.
    */
   public static final Unit<Angle> RADIAN = new BaseUnit<>( "rad", "Radian", Dimension.NONE);



   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The kelvin, symbol K, is the SI unit of thermodynamic temperature. It is
    * defined by taking the fixed numerical value of the Boltzmann constant k to be
    * 1.380 649 × 10−²³ when expressed in the unit J K⁻¹, which is equal to kg m²
    * s⁻² K⁻¹, where the kilogram, metre and second are defined in terms of h, c
    * and ∆νCs.
    * <p/>
    * This definition implies the exact relation k = 1.380 649 × 10⁻²³ kg m² s⁻²
    * K⁻¹. Inverting this relation gives an exact expression for the kelvin in
    * terms of the defining constants k, h and ∆νCs:
    * <p/>
    * 1 K = (1.380 649 / k) × 10⁻²³ kg m² s⁻²
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    * 
    * @see SI#JOULE
    */
   public static final Unit<Temperature> KELVIN = new BaseUnit<>("K", "Kelvin", Dimension.TEMPERATURE);



   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for electric charge, quantity of electricity (standard name
    * <code>C</code>). One Coulomb is equal to the quantity of charge transferred
    * in one second by a steady current of one ampere. It is named after the French
    * physicist Charles Augustin de Coulomb (1736-1806).
    */
   public static final Unit<ElectricCharge> COULOMB = new BaseUnit<>("C", "Coulomb", Dimension.ELECTRIC_CHARGE);


   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The candela, symbol cd, is the SI unit of luminous intensity in a given
    * direction. It is defined by taking the fixed numerical value of the luminous
    * efficacy of monochromatic radiation of frequency 540 × 10¹² Hz, Kcd, to be
    * 683 when expressed in the unit lm W−1, which is equal to cd sr W⁻¹, or cd sr
    * kg⁻¹ m⁻² s³, where the kilogram, metre and second are defined in terms of h,
    * c and ∆νCs.
    * <p/>
    * This definition implies the exact relation Kcd = 683 cd sr kg⁻¹ m⁻² s³ for
    * monochromatic radiation of frequency ν = 540 × 10¹² Hz. Inverting this
    * relation gives an exact expression for the candela in terms of the defining
    * constants Kcd, h and ∆νCs:
    * <p/>
    * 1 cd = (Kcd / 683) kg m² s⁻³ sr⁻¹
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    * @see <a href="http://en.wikipedia.org/wiki/Candela"> Wikipedia: Candela</a>
    */
   public static final Unit<LuminousIntensity> CANDELA = new BaseUnit<>("cd", "Candela", Dimension.LUMINOUS_INTENSITY);


   ///////////////////////////////////////////////
   // DIMENSIONLESS DERIVED UNITS: UCUM 4.3 §29 //
   ///////////////////////////////////////////////
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> TRILLIONS = Unit.ONE.multiply(1000000000000L);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> BILLIONS = Unit.ONE.multiply(1000000000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> MILLIONS = Unit.ONE.multiply(1000000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> THOUSANDS = Unit.ONE.multiply(1000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> HUNDREDS = Unit.ONE.multiply(100);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PI = Unit.ONE.transform(MultiplyConverter.ofPiExponent(1));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PERCENT = Unit.ONE.divide(100);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PER_THOUSAND = Unit.ONE.divide(1000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PER_MILLION = Unit.ONE.divide(1000000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PER_BILLION = Unit.ONE.divide(1000000000);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Dimensionless> PER_TRILLION = Unit.ONE.divide(1000000000000L);









   ///////////////////////////////////////////////////////////////////////
   // OTHER UNITS FROM ISO 1000, ISO 2955, AND ANSI X3.50: UCUM 4.3 §31 //
   ///////////////////////////////////////////////////////////////////////
   // The order of GON and DEGREE has been inverted because GON is defined in
   // terms of DEGREE
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Angle> DEGREE = new ProductUnit<>(PI.multiply(RADIAN.divide(180)));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Angle> GRADE = DEGREE.multiply(0.9);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Angle> GON = GRADE;
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Angle> MINUTE_ANGLE = DEGREE.divide(60);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Angle> SECOND_ANGLE = MINUTE_ANGLE.divide(60);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> LITER = new TransformedUnit<Volume>("l", "Litre", CUBIC_METRE, MultiplyConverter.ofRational(1, 1000));
   /**
    * As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. Liter has
    * <b>two</b> definitions.
    *
    * @see <a href="http://unitsofmeasure.org/ucum.html#iso1000">UCUM Table 5</a>
    */
   public static final Unit<Volume> LITER_DM3 = new AlternateUnit<>(DECI(METRE).pow(3).asType(Volume.class), "liter", "l");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Area> ARE = new AlternateUnit<>(SQUARE_METRE.multiply(100), "are", "a");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> MINUTE = new TransformedUnit<>("min", "Minute", SECOND, SECOND, MultiplyConverter.ofRational(60, 1));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> HOUR = new TransformedUnit<>("h", "Hour", SECOND, SECOND, MultiplyConverter.ofRational(60 * 60, 1));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> DAY = new TransformedUnit<>("d", "Day", SECOND, SECOND, MultiplyConverter.ofRational(24 * 60 * 60, 1));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> YEAR_TROPICAL = DAY.multiply(365.24219);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> YEAR_JULIAN = DAY.multiply(365.25);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> YEAR_GREGORIAN = DAY.multiply(365.2425);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. TODO Conflicting symbol */
   public static final Unit<Time> YEAR = new AlternateUnit<>(DAY.multiply(365.25), "a", "Year");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> WEEK = new AlternateUnit<>(DAY.multiply(7), "wk", "Week");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> MONTH_SYNODAL = DAY.multiply(29.53059);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> MONTH_JULIAN = YEAR_JULIAN.divide(12);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> MONTH_GREGORIAN = YEAR_GREGORIAN.divide(12);
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Time> MONTH = new AlternateUnit<>(YEAR_JULIAN.divide(12), "mo", "Month");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> TONNE = new AlternateUnit<>(KILO(GRAM).multiply(1000), "t", "Tonne");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Pressure> BAR = new AlternateUnit<>(PASCAL.multiply(100000), "bar", "Bar");
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> ATOMIC_MASS_UNIT = new TransformedUnit<>("u", "Unified atomic mass", KILO(GRAM), MultiplyConverter.of(1.660538782E-27));

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Energy> ELECTRON_VOLT = new TransformedUnit<>("eV", "Electron Volt", JOULE, MultiplyConverter.of(1.602176487E-19));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> ASTRONOMIC_UNIT = new TransformedUnit<>("AU", "Astronomical Unit", METRE, MultiplyConverter.of(149597870700d));
   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> PARSEC = new AlternateUnit<>(METRE.multiply(3.085678E16), "pc", "Parsec");



}
