package xpertss.measure;

import org.xpertss.unit.NumericQuantity;
import xpertss.measure.quantity.Dimensionless;

import java.util.Objects;

/**
 * Represents a quantitative property of a phenomenon, body, or substance, that
 * can be quantified by measurement. {@link xpertss.measure.quantity.Mass Mass},
 * time, distance, heat, and angular separation are among the familiar examples
 * of quantitative properties.
 * <p>
 * <code>
 *   Unit<Mass> pound = ...
 *   Quantity<Length> size = ...
 *   Sensor<Temperature> thermometer = ...
 *   Vector3D<Speed> aircraftSpeed = ...
 * </code>
 *
 * <h3>Arithmetic operations</h3>
 * This interface defines some arithmetic operations between {@code Quantity}
 * instances. All implementations shall produce <em>equivalent</em> results for
 * the same operation applied on equivalent quantities. Two quantities are
 * equivalent if, after conversion to the same unit of measurement, they have
 * the same numerical value (ignoring rounding errors). For example 2000 metres
 * is equivalent to 2 km, but 2°C is not equivalent to 2 K; it is equivalent to
 * 275.15 K instead. Above requirement applied to addition means that 2°C + 2 K
 * shall be equivalent to 275.15 K + 2 K.
 *
 * <p>All operations shall preserve the
 * <a href="https://en.wikiversity.org/wiki/Basic_Laws_of_Algebra">basic laws
 * of algebra</a>, in particular <b>commutativity</b> of addition and
 * multiplication (<var>A</var> + <var>B</var> = <var>B</var> + <var>A</var>)
 * and <b>associativity</b> of addition and multiplication (<var>A</var> +
 * <var>B</var>) + <var>C</var> = <var>A</var> + (<var>B</var> + <var>C</var>).
 * In order to preserve those algebra laws, this specification requires all
 * arithmetic operations to execute <em>as is</em> all operands were converted
 * to {@linkplain Unit#getSystemUnit() system unit} before the operation is
 * carried out, and the result converted back to any compatible unit at
 * implementation choice. For example 4 cm + 1 inch shall produce any result
 * <em>equivalent</em> to 0.04 m + 0.0254 m.</p>
 *
 * <p>Implementations are allowed to avoid conversion to system unit if the
 * result is guaranteed to be equivalent. This is often the case when the
 * conversion between quantity unit and system unit is only a
 * {@linkplain UnitConverter#isLinear() scale factor}. However this is not
 * the case for conversions applying an offset or more complex formula.
 * For example 2°C + 1°C = 274.15°C, not 3°C. This counter-intuitive result
 * is essential for preserving algebra laws like associativity, and is also
 * the expected result from a thermodynamic point of view.</p>
 *
 * apiNote This interface places no restrictions on the mutability of
 *          implementations, however immutability is strongly recommended. All
 *          implementations must be {@link Comparable}.
 *
 * @param <Q>  The type of the quantity.
 *
 * @see Unit
 * @see <a href="http://en.wikipedia.org/wiki/Quantity">Wikipedia: Quantity</a>
 * @see <a href="http://martinfowler.com/eaaDev/quantity.html">Martin Fowler -
 *      Quantity</a>
 */
public interface Quantity<Q extends Quantity<Q>> extends Comparable<Quantity<Q>> {

    /**
     * Holds a dimensionless quantity of none (exact).
     */
    public static final Quantity<Dimensionless> NONE = Quantity.of(0, Unit.ONE);

    /**
     * Holds a dimensionless quantity of one (exact).
     */
    public static final Quantity<Dimensionless> ONE = Quantity.of(1, Unit.ONE);


   /**
    * The scale of a {@link Quantity}, either {@code ABSOLUTE} or {@code RELATIVE}.
    *
    * @see <a href="https://en.wikipedia.org/wiki/Absolute_scale">Wikipedia: Absolute scale</a>
    */
    public static enum Scale {
        ABSOLUTE, RELATIVE
    }






   /**
    * Returns the sum of this {@code Quantity} with the one specified. The result shall be as
    * if this quantity and the given addend were converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be added, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @param addend
    *            the {@code Quantity} to be added.
    * @return {@code this + addend}.
    */
   Quantity<Q> add(Quantity<Q> addend);

   /**
    * Returns the difference between this {@code Quantity} and the one specified. The result
    * shall be as if this quantity and the given subtrahend were  converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be subtracted, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @param subtrahend
    *            the {@code Quantity} to be subtracted.
    * @return <code>this - subtrahend</code>.
    */
   Quantity<Q> subtract(Quantity<Q> subtrahend);

   /**
    * Returns the quotient of this {@code Quantity} divided by the {@code Quantity} specified.
    * The result shall be as if this quantity and the given divisor were converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be divided, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @throws ClassCastException
    *             if the type of an element in the specified operation is
    *             incompatible with this quantity
    *
    * @param divisor
    *            the {@code Quantity} divisor.
    * @return <code>this / divisor</code>.
    */
   Quantity<?> divide(Quantity<?> divisor);

   /**
    * Returns the quotient of this {@code Quantity} divided by the {@code Number} specified.
    * The result shall be as if this quantity was converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be divided, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @param divisor
    *            the {@code Number} divisor.
    * @return <code>this / divisor</code>.
    */
   Quantity<Q> divide(Number divisor);

   /**
    * Returns the product of this {@code Quantity} with the one specified. The result shall be
    * as if this quantity and the given multiplicand were converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be multiplied, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @throws ClassCastException
    *             if the type of an element in the specified operation is
    *             incompatible with this quantity
    *
    * @param multiplicand
    *            the {@code Quantity} multiplicand.
    * @return <code>this * multiplicand</code>.
    */
   Quantity<?> multiply(Quantity<?> multiplicand);

   /**
    * Returns the product of this {@code Quantity} with the {@code Number} value specified.
    * The result shall be as if this quantity was converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be multiplied, and the result
    * converted back to the unit of this quantity or any other compatible unit at
    * implementation choice.
    *
    * @param multiplicand
    *            the {@code Number} multiplicand.
    * @return <code>this * multiplicand</code>.
    */
   Quantity<Q> multiply(Number multiplicand);

   /**
    * Returns this {@code Quantity} converted into another (compatible) {@code Unit}.
    *
    * @param unit
    *            the {@code Unit unit} in which the returned quantity is stated.
    * @return this quantity or a new quantity equivalent to this quantity stated in the specified unit.
    * @throws ArithmeticException
    *             if the result is inexact and the quotient has a non-terminating decimal expansion.
    */
   Quantity<Q> to(Unit<Q> unit);

   /**
    * Returns a {@code Quantity} that is the multiplicative inverse of this
    * {@code Quantity}, having reciprocal value and reciprocal unit as given by
    * {@code this.getUnit().inverse()}.
    *
    * @return reciprocal {@code Quantity}
    * @see <a href=
    *      "https://en.wikipedia.org/wiki/Multiplicative_inverse">Wikipedia:
    *      Multiplicative inverse</a>
    */
   Quantity<?> inverse();

   /**
    * Returns a {@code Quantity} whose value is {@code (-this.getValue())}.
    *
    * @return {@code -this}.
    */
   Quantity<Q> negate();

   /**
    * Casts this quantity to a parameterized unit of specified nature or throw a
    * <code>ClassCastException</code> if the dimension of the specified quantity
    * and this measure unit's dimension do not match. For example:
    * <p/>
    * <code>
    *     Quantity<Length> length = format.parse("2 km").asType(Length.class);
    * </code> or
    * <code>
    *     Quantity<Speed> C = length.multiply(299792458).divide(second).asType(Speed.class);
    * </code>
    *
    * @param <T>
    *            The type of the quantity.
    * @param type
    *            the quantity class identifying the nature of the quantity.
    * @return this quantity parameterized with the specified type.
    * @throws ClassCastException
    *             if the dimension of this unit is different from the specified
    *             quantity dimension.
    * @throws UnsupportedOperationException
    *             if the specified quantity class does not have a SI unit for the
    *             quantity.
    * @see Unit#asType(Class)
    */
   <T extends Quantity<T>> Quantity<T> asType(Class<T> type) throws ClassCastException;

   /**
    * Returns the value of this {@code Quantity}.
    *
    * @return a value.
    */
   Number getValue();

   /**
    * Returns the unit of this {@code Quantity}.
    *
    * @return the unit (shall not be {@code null}).
    */
   Unit<Q> getUnit();

   /**
    * Convenient method equivalent to {@link #to(xpertss.measure.Unit)
    * to(getUnit().toSystemUnit())}.
    *
    * @return this quantity or a new quantity equivalent to this quantity stated in
    *         SI units.
    * @throws ArithmeticException
    *             if the result is inexact and the quotient has a non-terminating
    *             decimal expansion.
    */
   default Quantity<Q> toSystemUnit() {
      return to(getUnit().getSystemUnit());
   }

   /**
    * Returns the {@code Scale} of this {@code Quantity}, if it's absolute or relative.
    *
    * @return the scale, if it's an absolute or relative quantity.
    * @see <a href="https://en.wikipedia.org/wiki/Absolute_scale">Wikipedia: Absolute scale</a>
    */
   Scale getScale();










   // Comparable methods

   /**
    * Compares two instances of {@link Quantity <Q>}. Conversion of unit can happen if necessary
    *
    * @param that
    *          the {@code quantity<Q>} to be compared with this instance.
    * @return {@code true} if {@code that > this}.
    * @throws NullPointerException
    *           if the that is null
    */
   public boolean isGreaterThan(Quantity<Q> that);

   /**
    * Compares two instances of {@link Quantity <Q>}, doing the conversion of unit
    * if necessary.
    *
    * @param that
    *          the {@code quantity<Q>} to be compared with this instance.
    * @return {@code true} if {@code that >= this}.
    * @throws NullPointerException
    *           if the that is null
    */
   public boolean isGreaterThanOrEqualTo(Quantity<Q> that);


   /**
    * Compares two instances of {@link Quantity <Q>}, doing the conversion of unit
    * if necessary.
    *
    * @param that
    *          the {@code quantity<Q>} to be compared with this instance.
    * @return {@code true} if {@code that < this}.
    * @throws NullPointerException
    *           if the quantity is null
    */
   public boolean isLessThan(Quantity<Q> that);


   /**
    * Compares two instances of {@link Quantity <Q>}, doing the conversion of unit
    * if necessary.
    *
    * @param that
    *          the {@code quantity<Q>} to be compared with this instance.
    * @return {@code true} if {@code that < this}.
    * @throws NullPointerException
    *           if the quantity is null
    */
   public boolean isLessThanOrEqualTo(Quantity<Q> that);



   /**
    * Compares two instances of {@link Quantity <Q>}, doing the conversion of unit
    * if necessary.
    *
    * @param that
    *          the {@code quantity<Q>} to be compared with this instance.
    * @return {@code true} if {@code that = this}.
    * @throws NullPointerException
    *           if the quantity is null
    */
   public boolean isEquivalentTo(Quantity<Q> that);





   

   /**
    * Factory method that returns the scalar quantity.
    *
    * @param value the quantity value.
    * @param unit  the quantity unit.
    * @param scale the quantity scale.
    * @return the corresponding <code>numeric</code> quantity.
    * @throws NullPointerException if value, unit or scale were null
    */
    public static <Q extends Quantity<Q>> Quantity<Q> of(Number value, Unit<Q> unit, Scale scale)
    {
        Objects.requireNonNull(value);
        Objects.requireNonNull(unit);
        Objects.requireNonNull(scale);
        return new NumericQuantity<>(value, unit, scale);
    }

   /**
    * Factory method that returns the scalar quantity using an absolute scale.
    *
    * @param value the quantity value.
    * @param unit  the quantity unit.
    * @return the corresponding <code>numeric</code> quantity.
    * @throws NullPointerException if value, unit or scale were null
    */
    public static <Q extends Quantity<Q>> Quantity<Q> of(Number value, Unit<Q> unit)
    {
        return of(value, unit, Scale.ABSOLUTE);
    }



}
