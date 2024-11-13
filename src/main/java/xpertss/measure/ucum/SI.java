package xpertss.measure.ucum;

import org.xpertss.unit.converters.AddConverter;
import org.xpertss.unit.math.RationalNumber;
import org.xpertss.unit.types.AlternateUnit;
import org.xpertss.unit.types.ProductUnit;
import org.xpertss.unit.types.TransformedUnit;
import xpertss.measure.Unit;
import xpertss.measure.quantity.Acceleration;
import xpertss.measure.quantity.AmountOfSubstance;
import xpertss.measure.quantity.Area;
import xpertss.measure.quantity.CatalyticActivity;
import xpertss.measure.quantity.ElectricCapacitance;
import xpertss.measure.quantity.ElectricConductance;
import xpertss.measure.quantity.ElectricCurrent;
import xpertss.measure.quantity.ElectricInductance;
import xpertss.measure.quantity.ElectricPotential;
import xpertss.measure.quantity.ElectricResistance;
import xpertss.measure.quantity.Energy;
import xpertss.measure.quantity.Force;
import xpertss.measure.quantity.Frequency;
import xpertss.measure.quantity.Illuminance;
import xpertss.measure.quantity.LuminousFlux;
import xpertss.measure.quantity.MagneticFlux;
import xpertss.measure.quantity.MagneticFluxDensity;
import xpertss.measure.quantity.Power;
import xpertss.measure.quantity.Pressure;
import xpertss.measure.quantity.RadiationDoseAbsorbed;
import xpertss.measure.quantity.RadiationDoseEffective;
import xpertss.measure.quantity.Radioactivity;
import xpertss.measure.quantity.SolidAngle;
import xpertss.measure.quantity.Speed;
import xpertss.measure.quantity.Temperature;
import xpertss.measure.quantity.Volume;

import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.ucum.Base.CANDELA;
import static xpertss.measure.ucum.Base.COULOMB;
import static xpertss.measure.ucum.Base.GRAM;
import static xpertss.measure.ucum.Base.KELVIN;
import static xpertss.measure.ucum.Base.METRE;
import static xpertss.measure.ucum.Base.RADIAN;
import static xpertss.measure.ucum.Base.SECOND;

/**
 * International System of Units
 *
 * SI UNITS: UCUM 4.3 §30
 */
public class SI {




   /**
    * The mole, symbol mol, is the SI unit of amount of substance. One mole contains exactly
    * 6.022 140 76 × 10²³ elementary entities. This number is the fixed numerical value of
    * the Avogadro constant, NA, when expressed in the unit mol⁻¹ and is called the Avogadro
    * number.
    * <p/>
    * The amount of substance, symbol n, of a system is a measure of the number of specified
    * elementary entities. An elementary entity may be an atom, a molecule, an ion, an
    * electron, any other particle or specified group of particles. This definition implies
    * the exact relation Nₐ = 6.022 140 76 × 10²³ mol⁻¹.
    * <p/>
    * Inverting this relation gives an exact expression for the mole in terms of the defining
    * constant NA:
    * <p/>
    * 1 mol = 6.02214076 × 10²³ / Nₐ
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    * TODO How to give this dimension? Is there another unit that is the base for AMOUNT_OF_SUBSTANCE
    */
   public static final Unit<AmountOfSubstance> MOLE = AlternateUnit.of(Unit.ONE.multiply(6.0221367E23), "mol", "Mole");
   // new BaseUnit<>("mol", "Mole", Dimension.AMOUNT_OF_SUBSTANCE)



   /**
    * The SI unit for solid angle quantities (standard name <code>sr</code>). One
    * steradian is the solid angle subtended at the center of a sphere by an area
    * on the surface of the sphere that is equal to the radius squared. The total
    * solid angle of a sphere is 4*Pi steradians.
    */
   public static final Unit<SolidAngle> STERADIAN = new AlternateUnit<>(RADIAN.pow(2), "sr", "Steradian");



   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for frequency (standard name <code>Hz</code>). A unit of
    * frequency equal to one cycle per second. After Heinrich Rudolf Hertz
    * (1857-1894), German physicist who was the first to produce radio waves
    * artificially.
    */
   public static final Unit<Frequency> HERTZ = new AlternateUnit<>(Unit.ONE.divide(SECOND), "Hz", "Hertz");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for force (standard name <code>N</code>). One newton is the force
    * required to give a mass of 1 kilogram an Force of 1 metre per second per
    * second. It is named after the English mathematician and physicist Sir Isaac
    * Newton (1642-1727).
    */
   public static final Unit<Force> NEWTON = new AlternateUnit<>(METRE.multiply(KILO(GRAM)).divide(SECOND.pow(2)), "N", "Newton");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for pressure, stress (standard name <code>Pa</code>). One pascal
    * is equal to one newton per square meter. It is named after the French
    * philosopher and mathematician Blaise Pascal (1623-1662).
    */
   public static final Unit<Pressure> PASCAL =new AlternateUnit<>(NEWTON.divide(METRE.pow(2)), "Pa", "Pascal");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for energy, work, quantity of heat (<code>J</code>). One joule is
    * the amount of work done when an applied force of 1 newton moves through a
    * distance of 1 metre in the direction of the force. It is named after the
    * English physicist James Prescott Joule (1818-1889).
    */
   public static final Unit<Energy> JOULE = new AlternateUnit<>(NEWTON.multiply(METRE), "J", "Joule");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for power, radiant, flux (standard name <code>W</code>). One watt
    * is equal to one joule per second. It is named after the British scientist
    * James Watt (1736-1819).
    */
   public static final Unit<Power> WATT = new AlternateUnit<>(JOULE.divide(SECOND), "W", "Watt");

   /**
    * The ampere, symbol A, is the SI unit of electric current. It is defined by
    * taking the fixed numerical value of the elementary charge e to be 1.602 176
    * 634 × 10⁻¹⁹ when expressed in the unit C, which is equal to A s, where the
    * second is defined in terms of ∆νCs.
    * <p/>
    * This definition implies the exact relation e = 1.602 176 634 × 10⁻¹⁹ A s.
    * Inverting this relation gives an exact expression for the unit ampere in
    * terms of the defining constants e and ∆νCs:
    * <p/>
    * 1 A = (e / 1.602 176 634 × 10⁻¹⁹) s⁻¹
    *
    * <dl>
    * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
    * </dl>
    *
    * In UCUM, the ampere is defined as  <code>COULOMB.divide(SECOND)</code>.
    */
   public static final Unit<ElectricCurrent> AMPERE =  new AlternateUnit<>(COULOMB.divide(SECOND), "A", "Ampere");


   /**
    * The SI unit for electric potential difference, electromotive force (standard
    * name <code>V</code>). One Volt is equal to the difference of electric
    * potential between two points on a conducting wire carrying a constant current
    * of one ampere when the power dissipated between the points is one watt. It is
    * named after the Italian physicist Count Alessandro Volta (1745-1827).
    * <p/>
    * In UCUM, the volt is defined as <code>JOULE.divide(COULOMB)</code>.
    */
   public static final Unit<ElectricPotential> VOLT = new AlternateUnit<>(JOULE.divide(COULOMB), "V", "Volt");




   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for capacitance (standard name <code>F</code>). One Farad is
    * equal to the capacitance of a capacitor having an equal and opposite charge
    * of 1 coulomb on each plate and a potential difference of 1 volt between the
    * plates. It is named after the British physicist and chemist Michael Faraday
    * (1791-1867).
    */
   public static final Unit<ElectricCapacitance> FARAD = new AlternateUnit<>(COULOMB.divide(VOLT), "F", "Farad");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for electric resistance (standard name <code>Ohm</code>). One Ohm
    * is equal to the resistance of a conductor in which a current of one ampere is
    * produced by a potential of one volt across its terminals. It is named after
    * the German physicist Georg Simon Ohm (1789-1854).
    */
   public static final Unit<ElectricResistance> OHM = new AlternateUnit<>(VOLT.divide(AMPERE), "Ω", "Ohm");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for electric conductance (standard name <code>S</code>). One
    * Siemens is equal to one ampere per volt. It is named after the German
    * engineer Ernst Werner von Siemens (1816-1892).
    */
   public static final Unit<ElectricConductance> SIEMENS = new AlternateUnit<>(AMPERE.divide(VOLT), "S", "Siemens");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for magnetic flux (standard name <code>Wb</code>). One Weber is
    * equal to the magnetic flux that in linking a circuit of one turn produces in
    * it an electromotive force of one volt as it is uniformly reduced to zero
    * within one second. It is named after the German physicist Wilhelm Eduard
    * Weber (1804-1891).
    */
   public static final Unit<MagneticFlux> WEBER = new AlternateUnit<>(VOLT.multiply(SECOND), "Wb", "Weber");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for Celsius temperature (standard name <code>°C</code>). This is
    * a unit of temperature such as the freezing point of water (at one atmosphere
    * of pressure) is 0 °C, while the boiling point is 100 °C.
    */
   public static final Unit<Temperature> CELSIUS = new TransformedUnit<>("\u2103", "Celsius", KELVIN, new AddConverter(273.15));

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The alternate unit for magnetic flux density (standard name <code>T</code>).
    * One Tesla is equal equal to one weber per square metre. It is named after the
    * Serbian-born American electrical engineer and physicist Nikola Tesla
    * (1856-1943).
    */
   public static final Unit<MagneticFluxDensity> TESLA = new AlternateUnit<>(WEBER.divide(METRE.pow(2)), "T", "Tesla");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The alternate unit for inductance (standard name <code>H</code>). One Henry
    * is equal to the inductance for which an induced electromotive force of one
    * volt is produced when the current is varied at the rate of one ampere per
    * second. It is named after the American physicist Joseph Henry (1791-1878).
    */
   public static final Unit<ElectricInductance> HENRY = new AlternateUnit<>(WEBER.divide(AMPERE), "H", "Henry");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for luminous flux (standard name <code>lm</code>). One Lumen is
    * equal to the amount of light given out through a solid angle by a source of
    * one candela intensity radiating equally in all directions.
    */
   public static final Unit<LuminousFlux> LUMEN = new AlternateUnit<>(CANDELA.multiply(STERADIAN), "lm", "Lumen");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for illuminance (standard name <code>lx</code>). One Lux is equal
    * to one lumen per square metre.
    */
   public static final Unit<Illuminance> LUX =new AlternateUnit<>(LUMEN.divide(METRE.pow(2)), "lx", "Lux");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for activity of a radionuclide (standard name <code>Bq</code> ).
    * One becquerel is the radiation caused by one disintegration per second. It is
    * named after the French physicist, Antoine-Henri Becquerel (1852-1908).
    */
   public static final Unit<Radioactivity> BECQUEREL = new AlternateUnit<>(Unit.ONE.divide(SECOND), "Bq", "Becquerel");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for absorbed dose, specific energy (imparted), kerma (standard
    * name <code>Gy</code>). One gray is equal to the dose of one joule of energy
    * absorbed per one kilogram of matter. It is named after the British physician
    * L. H. Gray (1905-1965).
    */
   public static final Unit<RadiationDoseAbsorbed> GRAY = new AlternateUnit<>(JOULE.divide(KILO(GRAM)), "Gy", "Gray");

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   /**
    * The SI unit for dose equivalent (standard name <code>Sv</code>). One Sievert
    * is equal to the actual dose, in grays, multiplied by a "quality factor" which
    * is larger for more dangerous forms of radiation. It is named after the Swedish
    * physicist Rolf Sievert (1898-1966).
    */
   public static final Unit<RadiationDoseEffective> SIEVERT = new AlternateUnit<>(JOULE.divide(KILO(GRAM)), "Sv", "Sievert");





   // Non UCUM SI Units

   /**
    * The SI unit for catalytic activity (standard name <code>kat</code>).
    */
   public static final Unit<CatalyticActivity> KATAL =  new AlternateUnit<>(MOLE.divide(SECOND), "kat", "Katal");



   //////////////////////////////
   // SI DERIVED PRODUCT UNITS //
   //////////////////////////////

   /**
    * The SI unit for speed quantities (standard name <code>m/s</code>).
    */
   public static final Unit<Speed> METRE_PER_SECOND = new ProductUnit<>(METRE.divide(SECOND));

   /**
    * The SI unit for acceleration quantities (standard name <code>m/s2</code>).
    *
    * @see <a href="https://en.wikipedia.org/wiki/Metre_per_second_squared"> Wikipedia: Metre per second squared</a>
    */
   public static final Unit<Acceleration> METRE_PER_SQUARE_SECOND = new ProductUnit<>(METRE_PER_SECOND.divide(SECOND));

   /**
    * The SI unit for area quantities (standard name <code>m2</code>).
    *
    * @see <a href="https://en.wikipedia.org/wiki/Square_metre"> Wikipedia: Square metre</a>
    */
   public static final Unit<Area> SQUARE_METRE = new ProductUnit<>(METRE.multiply(METRE));

   /**
    * The SI unit for volume quantities (standard name <code>m3</code>).
    */
   public static final Unit<Volume> CUBIC_METRE = new ProductUnit<>(SQUARE_METRE.multiply(METRE));

   /**
    * A unit of speed expressing the number of international kilometres per {@link Base#HOUR hour}
    * (abbreviation <code>km/h</code>).
    *
    * @see <a href="https://en.wikipedia.org/wiki/Kilometres_per_hour"> Wikipedia: Kilometres per hour</a>
    */
   public static final Unit<Speed> KILOMETRE_PER_HOUR = METRE_PER_SECOND.multiply(RationalNumber.of(5, 18)).asType(Speed.class);



}
