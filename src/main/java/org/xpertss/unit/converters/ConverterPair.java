/*
 * Copyright 2024 XpertSoftware
 *
 * Created By: cfloersch
 * Date: 11/12/2024
 */
package org.xpertss.unit.converters;


import org.xpertss.unit.UnitComparator;
import org.xpertss.unit.math.Calculator;
import xpertss.measure.UnitConverter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents converters made up of two or more separate converters
 * (in matrix notation <code>[pair] = [left] x [right]</code>).
 */
public final class ConverterPair extends AbstractConverter {


   // -- BINARY TREE (PAIR)


   @SuppressWarnings("rawtypes")
   private final static Comparator unitComparator = new UnitComparator<>();

   /**
    * Holds the first converter.
    */
   private final UnitConverter left;

   /**
    * Holds the second converter.
    */
   private final UnitConverter right;

   /**
    * Creates a pair converter resulting from the combined transformation of the
    * specified converters.
    *
    * @param left
    *            the left converter, not <code>null</code>.
    * @param right
    *            the right converter.
    * @throws IllegalArgumentException
    *             if either the left or right converter are </code> null</code>
    */
   public ConverterPair(UnitConverter left, UnitConverter right)
   {
      if (left != null && right != null) {
         this.left = left;
         this.right = right;
      } else {
         throw new IllegalArgumentException("Converters cannot be null");
      }
   }

   @Override
   public boolean isLinear()
   {
      return left.isLinear() && right.isLinear();
   }

   @Override
   public Optional<Number> linearFactor()
   {
      // factors are composed by multiplying them, unless there is one absent linear-factor,
      // then all breaks down and we return an empty optional

      if(!(left instanceof AbstractConverter)) {
         throw requiresAbstractConverter();
      }

      if(!(right instanceof AbstractConverter)) {
         throw requiresAbstractConverter();
      }

      final Optional<Number> leftLinearFactor = ((AbstractConverter)left).linearFactor();
      final Optional<Number> rightLinearFactor = ((AbstractConverter)right).linearFactor();
      if(!leftLinearFactor.isPresent() || !leftLinearFactor.isPresent()) {
         return Optional.empty();
      }

      return Optional.of(
                  Calculator.of(leftLinearFactor.get())
                     .multiply(rightLinearFactor.get())
                     .peek());
   }

   @Override
   public boolean isIdentity()
   {
      return false;
   }

   /*
    * Non-API
    */
   protected List<? extends UnitConverter> createConversionSteps()
   {
      final List<? extends UnitConverter> leftSteps = left.getConversionSteps();
      final List<? extends UnitConverter> rightSteps = right.getConversionSteps();
      // TODO we could use Lambdas here
      final List<UnitConverter> steps = new ArrayList<>(leftSteps.size() + rightSteps.size());
      steps.addAll(leftSteps);
      steps.addAll(rightSteps);
      return steps;
   }

   @Override
   public ConverterPair inverseWhenNotIdentity()
   {
      return new ConverterPair(right.inverse(), left.inverse());
   }

   @Override
   protected Number convertWhenNotIdentity(Number value)
   {

      if(!(left instanceof AbstractConverter)) {
         throw requiresAbstractConverter();
      }

      if(!(right instanceof AbstractConverter)) {
         throw requiresAbstractConverter();
      }
      final AbstractConverter absLeft = (AbstractConverter) left;
      final AbstractConverter absRight = (AbstractConverter) right;
      return absLeft.convertWhenNotIdentity(absRight.convertWhenNotIdentity(value));
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj instanceof ConverterPair) {
         ConverterPair that = (ConverterPair) obj;
         return Objects.equals(left, that.left) && Objects.equals(right, that.right);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(left, right);
   }

   public UnitConverter getLeft()
   {
      return left;
   }

   public UnitConverter getRight()
   {
      return right;
   }

   @SuppressWarnings("unchecked")
   @Override
   public int compareTo(UnitConverter obj)
   {
      if (this == obj) return 0;
      if (obj instanceof ConverterPair) {
         ConverterPair that = (ConverterPair) obj;
         return Objects.compare(left, that.left, unitComparator)
                  + Objects.compare(right, that.right, unitComparator);
      }
      return -1;
   }

   @Override
   protected String transformationLiteral()
   {
      return String.format("%s",
         getConversionSteps().stream()
            .map(UnitConverter::toString)
            .collect(Collectors.joining(" ○ ")) );
   }

   @Override
   protected boolean canReduceWith(AbstractConverter that)
   {
      return false;
   }

   private IllegalArgumentException requiresAbstractConverter()
   {
      return new IllegalArgumentException("can only handle instances of AbstractConverter");
   }


}
