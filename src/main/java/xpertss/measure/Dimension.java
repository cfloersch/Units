package xpertss.measure;


import org.xpertss.unit.types.BaseUnit;
import org.xpertss.unit.types.ProductUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Represents the dimension of a unit.
 * <p/>
 * Concrete dimensions are obtained through the {@link Unit#getDimension()} method.
 * <p/>
 * Two units {@code u1} and {@code u2} are {@link Unit#isCompatible(Unit) compatible}
 * if and only if {@code u1.getDimension().equals(u2.getDimension())}.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Dimensional_analysis">Wikipedia:
 * Dimensional Analysis</a>
 */
public class Dimension {


   /**
    * Holds dimensionless.
    */
   public static final Dimension NONE = new Dimension(Unit.ONE);

   /**
    * Holds length dimension (L).
    */
   public static final Dimension LENGTH = new Dimension('L');

   /**
    * Holds mass dimension (M).
    */
   public static final Dimension MASS = new Dimension('M');

   /**
    * Holds time dimension (T).
    */
   public static final Dimension TIME = new Dimension('T');

   /**
    * Holds electric current dimension (I).
    */
   public static final Dimension ELECTRIC_CURRENT = new Dimension('I');

   /**
    * Holds temperature dimension (Θ).
    */
   public static final Dimension TEMPERATURE = new Dimension('\u0398');

   /**
    * Holds amount of substance dimension (N).
    */
   public static final Dimension AMOUNT_OF_SUBSTANCE = new Dimension('N');

   /**
    * Holds luminous intensity dimension (J).
    */
   public static final Dimension LUMINOUS_INTENSITY = new Dimension('J');


   /**
    * Holds the pseudo unit associated to this dimension.
    */
   private final Unit<?> pseudoUnit;





   /**
    * Returns the unit dimension having the specified symbol.
    *
    * @param symbol the associated symbol.
    */
   @SuppressWarnings("rawtypes")
   private Dimension(char symbol)
   {
      pseudoUnit = new BaseUnit("[" + symbol + ']', NONE);
   }

   /**
    * Constructor from pseudo-unit (not visible).
    *
    * @param pseudoUnit the pseudo-unit.
    */
   private Dimension(Unit<?> pseudoUnit)
   {
      this.pseudoUnit = pseudoUnit;
   }

   /**
    * Default Constructor (not visible).
    *
    */
   protected Dimension()
   {
      this(Unit.ONE);
   }


   /**
    * Returns the product of this dimension with the one specified.
    * If the specified dimension is not a <code>UnitDimension</code>, then
    * <code>that.multiply(this)</code> is returned.
    *
    * @param that the dimension multiplicand.
    * @return <code>this * that</code>
    */
   public Dimension multiply(Dimension that)
   {
      return new Dimension(this.pseudoUnit.multiply(that.pseudoUnit));
   }


   /**
    * Returns the quotient of this dimension with the one specified.
    * If the specified dimension is not a <code>UnitDimension</code>, then
    * <code>that.divide(this).pow(-1)</code> is returned.
    *
    * @param that the dimension divisor.
    * @return <code>this / that</code>
    */
   public Dimension divide(Dimension that)
   {
      return new Dimension(ProductUnit.ofQuotient(pseudoUnit, that.pseudoUnit));
   }


   /**
    * Returns this dimension raised to an exponent.
    *
    * @param n the exponent.
    * @return the result of raising this dimension to the exponent.
    */
   public Dimension pow(int n)
   {
      return new Dimension(this.pseudoUnit.pow(n));
   }

   /**
    * Returns the given root of this dimension.
    *
    * @param n the root's order.
    * @return the result of taking the given root of this dimension.
    * @throws ArithmeticException if <code>n == 0</code>.
    */
   public Dimension root(int n)
   {
      return new Dimension(this.pseudoUnit.root(n));
   }





   /**
    * Returns the fundamental (base) dimensions and their exponent whose product
    * is this dimension or <code>null</code> if this dimension is a fundamental
    * dimension.
    *
    * @return the mapping between the base dimensions and their exponent.
    */
   @SuppressWarnings("rawtypes")
   public Map<? extends Dimension, Integer> getBaseDimensions() {
      Map<? extends Unit, Integer> pseudoUnits = pseudoUnit.getBaseUnits();
      if (pseudoUnits == null) return null;
      final Map<Dimension, Integer> baseDimensions = new HashMap<>();
      for (Map.Entry<? extends Unit, Integer> entry : pseudoUnits.entrySet()) {
         baseDimensions.put(new Dimension(entry.getKey()), entry.getValue());
      }
      return baseDimensions;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj instanceof Dimension) {
         Dimension other = (Dimension) obj;
         return Objects.equals(pseudoUnit, other.pseudoUnit);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return Objects.hashCode(pseudoUnit);
   }

   @Override
   public String toString()
   {
      return pseudoUnit.toString();
   }












   /**
    * TODO Should I keep this or move it
    * Returns the dimension for the specified quantity type by aggregating the
    * results from the default {@link javax.measure.spi.SystemOfUnits SystemOfUnits}
    * or <code>null</code> if the specified quantity is unknown.
    *
    * @param quantityType the quantity type.
    * @return the dimension for the quantity type or <code>null</code>.
   public static <Q extends Quantity<Q>> Dimension of(Class<Q> quantityType)
   {
   // TODO: Track services and aggregate results (register custom types)
   Unit<Q> siUnit = Units.getInstance().getUnit(quantityType);
   return (siUnit != null) ? siUnit.getDimension() : null;
   }
    */

   /**
    * TODO Should this be moved??
    * Returns the dimension for the specified symbol.
    *
    * @param symbol the quantity symbol.
    * @return the dimension for the given symbol.
   public static Dimension parse(char symbol)
   {
      return new Dimension(symbol);
   }
    */


}
