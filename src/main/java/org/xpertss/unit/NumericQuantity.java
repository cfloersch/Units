/*
 * Copyright 2024 XpertSoftware
 *
 * Created By: cfloersch
 * Date: 11/13/2024
 */
package org.xpertss.unit;

import org.xpertss.unit.converters.AbstractConverter;
import org.xpertss.unit.math.Calculator;
import org.xpertss.unit.math.NumberSystem;
import org.xpertss.unit.utils.OperandMode;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.UnitConverter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class NumericQuantity<Q extends Quantity<Q>> implements Quantity<Q> {

   private final Number value;

   private final Unit<Q> unit;

   private final Quantity.Scale scale;


   /**
    * Constructor.
    * @param unit a unit
    * @param sca the scale, absolute or relative
    */
   public NumericQuantity(Number number, Unit<Q> unit, Quantity.Scale sca)
   {
      this.value = number;
      this.unit = unit;
      this.scale = sca;
   }

   /**
    * Constructor. Applies {@code ABSOLUTE} {@code Scale} if none was given.
    * @param unit a unit
    */
   public NumericQuantity(Number number, Unit<Q> unit)
   {
      this(number, unit, Quantity.Scale.ABSOLUTE);
   }



   /**
    * Returns the numeric value of the quantity.
    *
    * @return the quantity value.
    */
   public Number getValue()
   {
      return value;
   }

   /**
    * Returns the measurement unit.
    *
    * @return the measurement unit.
    */
   public Unit<Q> getUnit() {
      return unit;
   }

   /**
    * Returns the absolute or relative scale.
    *
    * @return the scale.
    */
   public Scale getScale() {
      return scale;
   }



   /**
    * Returns the sum of this {@code Quantity} with the one specified.
    * The result shall be as if this quantity and the given addend were
    * converted to {@linkplain Unit#getSystemUnit() system unit} before
    * to be added, and the result converted back to the unit of this
    * quantity or any other compatible unit at implementation choice.
    *
    * @param addend
    *            the {@code Quantity} to be added.
    * @return {@code this + addend}.
    */
   public Quantity<Q> add(Quantity<Q> addend)
   {
      return addition(this, addend,
         (thisValue, thatValue) -> Calculator.of(thisValue).add(thatValue).peek());
   }

   /**
    * Returns the difference between this {@code Quantity} and the one specified.
    * The result shall be as if this quantity and the given subtrahend were
    * converted to {@linkplain Unit#getSystemUnit() system unit} before
    * to be subtracted, and the result converted back to the unit of this
    * quantity or any other compatible unit at implementation choice.
    *
    * @param subtrahend
    *            the {@code Quantity} to be subtracted.
    * @return <code>this - subtrahend</code>.
    */
   public Quantity<Q> subtract(Quantity<Q> subtrahend)
   {
      return addition(this, subtrahend,
         (thisValue, thatValue) -> Calculator.of(thisValue).subtract(thatValue).peek());
   }

   /**
    * Returns the quotient of this {@code Quantity} divided by the {@code Quantity}
    * specified.
    * The result shall be as if this quantity and the given divisor were
    * converted to {@linkplain Unit#getSystemUnit() system unit} before
    * to be divided, and the result converted back to the unit of this
    * quantity or any other compatible unit at implementation choice.
    *
    * @throws ClassCastException
    *             if the type of an element in the specified operation is
    *             incompatible with this quantity
    *
    * @param divisor
    *            the {@code Quantity} divisor.
    * @return <code>this / divisor</code>.
    */
   public Quantity<?> divide(Quantity<?> divisor)
   {
      return multiplication(this, divisor,
         (thisValue, thatValue) -> Calculator.of(thisValue).divide(thatValue).peek(),
         (thisUnit, thatUnit) -> thisUnit.divide(thatUnit));
   }

   /**
    * Returns the quotient of this {@code Quantity} divided by the {@code Number}
    * specified.
    * The result shall be as if this quantity was converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be divided,
    * and the result converted back to the unit of this quantity or any
    * other compatible unit at implementation choice.
    *
    * @param divisor
    *            the {@code Number} divisor.
    * @return <code>this / divisor</code>.
    */
   public Quantity<Q> divide(Number divisor)
   {
      return scalarMultiplication(this, thisValue ->
         Calculator.of(thisValue).divide(divisor).peek());
   }

   /**
    * Returns the product of this {@code Quantity} with the one specified.
    * The result shall be as if this quantity and the given multiplicand were
    * converted to {@linkplain Unit#getSystemUnit() system unit} before
    * to be multiplied, and the result converted back to the unit of this
    * quantity or any other compatible unit at implementation choice.
    *
    * @throws ClassCastException
    *             if the type of an element in the specified operation is
    *             incompatible with this quantity
    *
    * @param multiplicand
    *            the {@code Quantity} multiplicand.
    * @return <code>this * multiplicand</code>.
    */
   public Quantity<?> multiply(Quantity<?> multiplicand)
   {
      return multiplication(this, multiplicand,
         (thisValue, thatValue) -> Calculator.of(thisValue).multiply(thatValue).peek(),
         (thisUnit, thatUnit) -> thisUnit.multiply(thatUnit));
   }

   /**
    * Returns the product of this {@code Quantity} with the {@code Number} value
    * specified.
    * The result shall be as if this quantity was converted to
    * {@linkplain Unit#getSystemUnit() system unit} before to be multiplied,
    * and the result converted back to the unit of this quantity or any
    * other compatible unit at implementation choice.
    *
    * @param multiplicand
    *            the {@code Number} multiplicand.
    * @return <code>this * multiplicand</code>.
    */
   public Quantity<Q> multiply(Number multiplicand)
   {
      return scalarMultiplication(this, thisValue ->
         Calculator.of(thisValue).multiply(multiplicand).peek());
   }

   /**
    * Returns this {@code Quantity} converted into another (compatible)
    * {@code Unit}.
    *
    * @param anotherUnit
    *            the {@code Unit unit} in which the returned quantity is stated.
    * @return this quantity or a new quantity equivalent to this quantity stated in the specified unit.
    * @throws ArithmeticException
    *             if the result is inexact and the quotient has a non-terminating decimal expansion.
    */
   public Quantity<Q> to(Unit<Q> anotherUnit)
   {
      if (anotherUnit.equals(this.getUnit())) return this;
      final UnitConverter converter = this.getUnit().getConverterTo(anotherUnit);

      if (isRelative(this)) {
         final Number linearFactor = linearFactorOf(converter).orElse(null);
         if(linearFactor==null)
            throw unsupportedRelativeScaleConversion(this, anotherUnit);
         final Number valueInOtherUnit = Calculator.of(linearFactor).multiply(this.getValue()).peek();
         return Quantity.of(valueInOtherUnit, anotherUnit, Quantity.Scale.RELATIVE);
      }

      final Number convertedValue = converter.convert(this.getValue());
      return Quantity.of(convertedValue, anotherUnit, Quantity.Scale.ABSOLUTE);
   }

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
   public Quantity<?> inverse()
   {
      final Number resultValueInThisUnit = Calculator
         .of(getValue())
         .reciprocal()
         .peek();
      return Quantity.of(resultValueInThisUnit, getUnit().inverse(), getScale());
   }

   /**
    * Returns a {@code Quantity} whose value is {@code (-this.getValue())}.
    *
    * @return {@code -this}.
    */
   public Quantity<Q> negate()
   {
      final Number resultValueInThisUnit = Calculator
         .of(getValue())
         .negate()
         .peek();
      return Quantity.of(resultValueInThisUnit, getUnit(), getScale());
   }

   /**
    * Casts this quantity to a parameterized unit of specified nature or throw a
    * <code>ClassCastException</code> if the dimension of the specified quantity
    * and this measure unit's dimension do not match. For example:
    * <p>
    * <code>
    *     Quantity<Length> length = format.parse("2 km").asType(Length.class);
    * </code>
    *   or
    * <code>
    *     Quantity<Speed> C = length.multiply(299792458).divide(second).asType(Speed.class);
    * </code>
    * </p>
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
   public <T extends Quantity<T>> Quantity<T> asType(Class<T> type)
      throws ClassCastException
   {
      this.getUnit().asType(type); // ClassCastException if dimension mismatches.
      //noinspection unchecked
      return (Quantity<T>) this;
   }


   /**
    * Convenient method equivalent to {@link #to(xpertss.measure.Unit)
    * to(getUnit().toSystemUnit())}.
    *
    * @return this quantity or a new quantity equivalent to this quantity stated
    *         in  units.
    * @throws ArithmeticException
    *             if the result is inexact and the quotient has a non-terminating
    *             decimal expansion.
    */
   public Quantity<Q> toSystemUnit()
   {
      return to(getUnit().getSystemUnit());
   }












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
   public boolean isGreaterThan(Quantity<Q> that)
   {
      return this.compareTo(that) > 0;
   }

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
   public boolean isGreaterThanOrEqualTo(Quantity<Q> that)
   {
      return this.compareTo(that) >= 0;
   }


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
   public boolean isLessThan(Quantity<Q> that)
   {
      return this.compareTo(that) < 0;
   }


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
   public boolean isLessThanOrEqualTo(Quantity<Q> that)
   {
      return this.compareTo(that) <= 0;
   }



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
   public boolean isEquivalentTo(Quantity<Q> that)
   {
      return this.compareTo(that) == 0;
   }




   /**
    * Compares this quantity to the specified quantity. The default implementation
    * compares the value of both this quantity and the specified quantity stated in
    * the same unit (this quantity's {@link #getUnit() unit}). If units are not the
    * same, the unit of the specified quantity is converted.
    *
    * @param  that
    *      {@code Quantity} to which this {@code AbstractQuantity} is to be compared.
    * @return a negative integer, zero, or a positive integer as this quantity is less
    *       than, equal/equivalent to, or greater than the specified Measurement
    *       quantity.
    */
   @Override
   public int compareTo(Quantity<Q> that)
   {
      if (this.getUnit().equals(that.getUnit())) {
         return NumberSystem.current().compare(this.getValue(), that.getValue());
      }
      return NumberSystem.current().compare(this.getValue(), that.to(this.getUnit()).getValue());
   }


   /**
    * Compares this quantity against the specified object for <b>strict</b> equality
    * (same unit and same amount).
    * <p/>
    * Similarly to the {@link BigDecimal#equals} method which consider 2.0 and 2.00
    * as different objects because of different internal scales, quantities such as
    * <code>Quantities.getQuantity(3.0, KILO(GRAM))</code>
    * <code>Quantities.getQuantity(3, KILO(GRAM))</code> and
    * <code>Quantities.getQuantity("3 kg")</code>
    * might not be considered equals because of possible differences in their
    * implementations.
    * <p/>
    * To compare quantities stated using different units or using different amount
    * implementations the {@link #compareTo compareTo} or {@link #isEquivalentTo}
    * methods should be used.
    *
    * @param obj
    *            the object to compare with.
    * @return <code>this.getUnit.equals(obj.getUnit())
    *                 && this.getScale().equals(obj.getScale()
    *                 && this.getValue().equals(obj.getValue())</code>
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj) return true;
      if (obj instanceof Quantity<?>) {
         Quantity<?> that = (Quantity<?>) obj;
         return Objects.equals(getUnit(), that.getUnit()) &&
            Objects.equals(getScale(), that.getScale()) &&
            Objects.equals(getValue(), that.getValue());
      }
      return false;
   }

   /**
    * Returns the hash code for this quantity.
    *
    * @return the hash code value.
    */
   @Override
   public int hashCode()
   {
      return Objects.hash(getUnit(), getScale(), getValue());
   }

   /**
    * Returns the <code>String</code> representation of this quantity. The
    * string produced for a given quantity is always the same; it is not
    * affected by locale. This means that it can be used as a canonical
    * string representation for exchanging quantity, or as a key for a
    * Hashtable, etc. Locale-sensitive quantity formatting and parsing is
    * handled by the {@link QuantityFormat} implementations and its subclasses.
    *
    * @return <code>SimpleQuantityFormat.getInstance().format(this)</code>
    */
   @Override
   public String toString()
   {
      return "null";
      //return SimpleQuantityFormat.getInstance().format(this);
   }














   // Internal impl


   private static Optional<Number> linearFactorOf(UnitConverter converter)
   {
      return (converter instanceof AbstractConverter)
         ? ((AbstractConverter)converter).linearFactor()
         : Optional.empty();
   }


   private static boolean isAbsolute(final Quantity<?> quantity)
   {
      return Quantity.Scale.ABSOLUTE == quantity.getScale();
   }

   private static boolean isRelative(final Quantity<?> quantity)
   {
      return Quantity.Scale.RELATIVE == quantity.getScale();
   }



   private static <Q extends Quantity<Q>> Quantity<Q> addition(
      final Quantity<Q> base,
      final Quantity<Q> addend,
      final BinaryOperator<Number> operator)
   {
      final boolean yieldsRelativeScale = OperandMode.get(base, addend).isAllRelative();

      final Unit<Q> systemUnit = base.getUnit().getSystemUnit();

      // converting almost all, except system units and those that are shifted and relative like eg. Δ2°C == Δ2K
      final ToSystemUnitConverter thisConverter =
         ToSystemUnitConverter.forQuantity(base, systemUnit);
      final ToSystemUnitConverter thatConverter =
         ToSystemUnitConverter.forQuantity(addend, systemUnit);

      final Number thisValueInSystemUnit = thisConverter.convert(base.getValue());
      final Number thatValueInSystemUnit = thatConverter.convert(addend.getValue());

      final Number resultValueInSystemUnit = operator.apply(thisValueInSystemUnit, thatValueInSystemUnit);

      if (yieldsRelativeScale) {
         return Quantity.of(thisConverter.invert(resultValueInSystemUnit), base.getUnit(), Quantity.Scale.RELATIVE);
      }

      final boolean needsInverting = !thisConverter.isNoop() || !thatConverter.isNoop();
      final Number resultValueInThisUnit = needsInverting
         ? base.getUnit().getConverterTo(base.getUnit().getSystemUnit()).inverse().convert(resultValueInSystemUnit)
         : resultValueInSystemUnit;

      return Quantity.of(resultValueInThisUnit, base.getUnit(), Quantity.Scale.ABSOLUTE);
   }


   private static <Q extends Quantity<Q>> Quantity<Q> scalarMultiplication(final Quantity<Q> quantity,
                                                                           final UnaryOperator<Number> operator)
   {

      // if operand has scale RELATIVE, multiplication is trivial
      if (isRelative(quantity)) {
         return Quantity.of(
            operator.apply(quantity.getValue()),
            quantity.getUnit(),
            Quantity.Scale.RELATIVE);
      }

      final Unit<Q> unit = quantity.getUnit();
      final Unit<Q> systemUnit = unit.getSystemUnit();
      final ToSystemUnitConverter toSystemUnits = ToSystemUnitConverter.forQuantity(quantity, systemUnit);

      final Number thisValueWithAbsoluteScale = toSystemUnits.convert(quantity.getValue());
      final Number resultValueInAbsUnits = operator.apply(thisValueWithAbsoluteScale);
      final boolean needsInvering = !toSystemUnits.isNoop();

      final Number resultValueInThisUnit = needsInvering
         ? unit.getConverterTo(systemUnit).inverse().convert(resultValueInAbsUnits)
         : resultValueInAbsUnits;

      return Quantity.of(resultValueInThisUnit, unit, quantity.getScale());
   }

   private static Quantity<?> multiplication(final Quantity<?> q1, final Quantity<?> q2,
                                             final BinaryOperator<Number> amountOperator,
                                             final BinaryOperator<Unit<?>> unitOperator)
   {

      final Quantity<?> absQ1 = toAbsoluteLinear(q1);
      final Quantity<?> absQ2 = toAbsoluteLinear(q2);
      return Quantity.of(
         amountOperator.apply(absQ1.getValue(), absQ2.getValue()),
         unitOperator.apply(absQ1.getUnit(), absQ2.getUnit()));
   }

   private static <Q extends Quantity<Q>> Quantity<Q> toAbsoluteLinear(Quantity<Q> quantity)
   {
      final Unit<Q> unit = quantity.getUnit();
      final Unit<Q> systemUnit = unit.getSystemUnit();
      final UnitConverter toSystemUnit = unit.getConverterTo(systemUnit);
      if(toSystemUnit.isLinear()) {
         if(isAbsolute(quantity)) return quantity;
         return Quantity.of(quantity.getValue(), unit);
      }
      // convert to system units
      if(isAbsolute(quantity)) {
         return Quantity.of(toSystemUnit.convert(quantity.getValue()), systemUnit, Quantity.Scale.ABSOLUTE);
      } else {
         final Number linearFactor = linearFactorOf(toSystemUnit).orElse(null);
         if(linearFactor==null)
            throw unsupportedRelativeScaleConversion(quantity, systemUnit);
         final Number valueInSystemUnits = Calculator.of(linearFactor).multiply(quantity.getValue()).peek();
         return Quantity.of(valueInSystemUnits, systemUnit, Quantity.Scale.ABSOLUTE);
      }
   }




   private static class ToSystemUnitConverter {

      private final UnaryOperator<Number> unaryOperator;
      private final UnaryOperator<Number> inverseOperator;

      public static <Q extends Quantity<Q>> ToSystemUnitConverter forQuantity(Quantity<Q> quantity, Unit<Q> systemUnit)
      {
         if(quantity.getUnit().equals(systemUnit)) {
            return ToSystemUnitConverter.noop(); // no conversion required
         }

         final UnitConverter converter = quantity.getUnit().getConverterTo(systemUnit);

         if(Quantity.Scale.ABSOLUTE  == quantity.getScale()) {
            return ToSystemUnitConverter.of(converter::convert); // convert to system units
         }
         final Number linearFactor = linearFactorOf(converter).orElse(null);
         if(linearFactor != null) {
            // conversion by factor required ... Δ2°C -> Δ2K , Δ2°F -> 5/9 * Δ2K
            return ToSystemUnitConverter.factor(linearFactor);
         }
         // convert any other cases of RELATIVE scale to system unit (ABSOLUTE) ...
         throw unsupportedConverter(converter, quantity.getUnit());
      }


      public static ToSystemUnitConverter of(UnaryOperator<Number> unaryOperator)
      {
         return new ToSystemUnitConverter(unaryOperator, null);
      }

      public static ToSystemUnitConverter noop() {
         return new ToSystemUnitConverter(null, null);
      }

      public static ToSystemUnitConverter factor(Number factor)
      {
         return new ToSystemUnitConverter(
            number-> Calculator.of(number).multiply(factor).peek(),
            number-> Calculator.of(number).divide(factor).peek());
      }

      private ToSystemUnitConverter(UnaryOperator<Number> unaryOperator,
                                    UnaryOperator<Number> inverseOperator)
      {
         this.unaryOperator = unaryOperator;
         this.inverseOperator = inverseOperator;
      }

      public boolean isNoop() {
         return unaryOperator==null;
      }

      public Number convert(Number x)
      {
         return isNoop() ? x : unaryOperator.apply(x);
      }

      public Number invert(Number x)
      {
         return isNoop() ? x : inverseOperator.apply(x);
      }

   }




   private static UnsupportedOperationException unsupportedConverter(UnitConverter converter, Unit<?> unit)
   {
      return new UnsupportedOperationException(
         String.format(
            "Scale conversion from RELATIVE to ABSOLUTE for Unit %s having Converter %s is not implemented.",
            unit, converter));
   }

   private static <Q extends Quantity<Q>> UnsupportedOperationException unsupportedRelativeScaleConversion(
      Quantity<Q> quantity, Unit<Q> anotherUnit)
   {
      return new UnsupportedOperationException(
         String.format(
            "Conversion of Quantity %s to Unit %s is not supported for relative scale.",
            quantity, anotherUnit));
   }


}
