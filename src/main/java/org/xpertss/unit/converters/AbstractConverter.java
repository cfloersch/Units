/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import xpertss.measure.UnitConverter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The base class for our {@link UnitConverter} implementations.
 */
public abstract class AbstractConverter implements UnitConverter, Comparable<UnitConverter> {

    /**
     * Default identity converter implementing AbstractConverter.
     * <p>
     * Note: Checking whether a UnitConverter is an identity operator should be done with 
     * {@code UnitConverter.isIdentity()} rather than checking for object identity 
     * {@code unitConverter == AbstractConverter.IDENTITY}.
     */
    public static final AbstractConverter IDENTITY = new Identity();
    
    /**
     * Allows for plug in of a custom UnitCompositionHandler.
     */
    public static ConverterCompositionHandler UNIT_COMPOSITION_HANDLER = ConverterCompositionHandler.yieldingNormalForm();

    /**
     * memorization for getConversionSteps
     */
    protected List<? extends UnitConverter> conversionSteps; 

    /**
     * DefaultQuantityFactory constructor.
     */
    protected AbstractConverter()
    {
    }

    @Override
    public abstract boolean equals(Object cvtr);

    @Override
    public abstract int hashCode();
    
    // -- TO-STRING - CONTRACT AND INTERFACE IMPLEMENTATION (FINAL)
    
    /**
     * Non-API
     * <p>
     * Returns a String describing the transformation that is represented by this converter. 
     * Contributes to converter's {@code toString} method. If null or empty
     * {@code toString} output becomes simplified.
     * </p>
     * @return 
     */
    protected abstract String transformationLiteral();
    
    @Override
    public final String toString()
    {
        String converterName = getClass().getSimpleName();
        // omit trailing 'Converter'
        if(converterName.endsWith("Converter")) {
            converterName = converterName.substring(0, converterName.length()-"Converter".length());
        }
        if(isIdentity()) {
            return String.format("%s(IDENTITY)", converterName);
        }
        final String transformationLiteral = transformationLiteral();
        if(transformationLiteral==null || transformationLiteral.length()==0) {
            return String.format("%s", converterName);
        }
        return String.format("%s(%s)", converterName, transformationLiteral);
    }

    // -- INVERSION - CONTRACT AND INTERFACE IMPLEMENTATION (FINAL)
    
    /**
     * Non-API
     * <p>
     * Returns an AbstractConverter that represents the inverse transformation of this converter,
     * for cases where the transformation is not the identity transformation.
     * </p>  
     * @return 
     */
    protected abstract AbstractConverter inverseWhenNotIdentity();
    
    @Override
    public final UnitConverter inverse()
    {
        if(isIdentity()) return this;
        return inverseWhenNotIdentity();
    }
    
    // -- COMPOSITION CONTRACTS (TO BE IMPLEMENTED BY SUB-CLASSES)

    /**
     * Non-API
     * Guard for {@link #reduce(AbstractConverter)}
     * @param that
     * @return whether or not a composition with given {@code that} is possible, such 
     * that no additional conversion steps are required, with respect to the steps already 
     * in place by this converter 
     */
    protected abstract boolean canReduceWith(AbstractConverter that);
    
    /**
     * Non-API
     * Guarded by {@link #canReduceWith(AbstractConverter)}
     * @param that
     * @return a new AbstractConverter that adds no additional conversion steps, with respect 
     * to the steps already in place by this converter 
     */
    protected AbstractConverter reduce(AbstractConverter that)
    {
        throw new IllegalStateException(
                String.format("Concrete UnitConverter '%s' does not implement reduce(...).", this)); 
    }
    
    // -- COMPOSITION INTERFACE IMPLEMENTATION (FINAL)
    
    @Override
    public final UnitConverter concatenate(UnitConverter converter)
    {
        Objects.requireNonNull(converter, "Cannot compose with converter that is null.");
        
        if(converter instanceof AbstractConverter) {
            final AbstractConverter other = (AbstractConverter) converter;
            return UNIT_COMPOSITION_HANDLER.compose(this, other, 
                    AbstractConverter::canReduceWith,
                    AbstractConverter::reduce);
        }
        // converter is not a sub-class of AbstractConverter, we do the best we can ...
        if(converter.isIdentity()) {
            return this;
        }
        if(this.isIdentity()) {
            return converter;
        }
        //[ahuber] we don't know how to reduce to a 'normal-form' with 'foreign' converters,
        // so we just return the straightforward composition, which no longer allows for proper
        // composition equivalence test
        return new Pair(this, converter);
    }

    @Override
    public final List<? extends UnitConverter> getConversionSteps()
    {
        if(conversionSteps != null) {
            return conversionSteps;  
        }
        if(this instanceof Pair) {
            return conversionSteps = ((Pair)this).createConversionSteps();
        }
        return conversionSteps = Collections.singletonList(this);
    }
    
    // -- CONVERSION CONTRACTS (TO BE IMPLEMENTED BY SUB-CLASSES)
    
    /**
     * Non-API
     * @param value
     * @return transformed value 
     */
    protected abstract Number convertWhenNotIdentity(Number value);
    
    // -- CONVERSION INTERFACE IMPLEMENTATION (FINAL)
    
    /**
     * @throws IllegalArgumentException
     *             if the value is <code>null</code>.
     */
    @Override
    public final Number convert(Number value)
    {
        if(isIdentity()) {
            return value;
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return convertWhenNotIdentity(value);
    }
    
    /**
     * Even though transformations may be composed of addition and multiplication, the first
     * derivative might just be a linear function. This is strictly required for Quantities that 
     * are expressed with RELATIVE scope. Eg. Δ2°C or Δ2°F. Otherwise such deltas cannot 
     * be converted to ABSOLUTE scope without additional information.
     *  
     * @return optionally the linear factor of this transformation's first derivative, 
     * based on whether this transformation allows for RELATIVE scaled Quantities
     */
    public Optional<Number> linearFactor()
    {
        if(this instanceof AddConverter) {
            return Identity.ONE;
        }
        if(this instanceof MultiplyConverter) {
            return Optional.of(((MultiplyConverter)this).getFactor());
        }
        return Optional.empty();
    }
    
    // -- DEFAULT IMPLEMENTATION OF IDENTITY

    /**
     * This class represents the identity converter (singleton).
     */
    private static final class Identity extends AbstractConverter {

        private static final Optional<Number> ONE = Optional.of(1);

        @Override
        public boolean isIdentity() {
            return true;
        }

        @Override
        protected Number convertWhenNotIdentity(Number value) {
            throw unreachable();
        }
        
        @Override
        public boolean equals(Object cvtr) {
            return (cvtr instanceof Identity); 
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean isLinear() {
            return true;
        }

        @Override
        public Optional<Number> linearFactor() {
            return ONE;
        }
        
        @Override
        public int compareTo(UnitConverter o)
        {
            if (o instanceof Identity) {
                return 0;
            }
            return -1;
        }

        @Override
        protected boolean canReduceWith(AbstractConverter that) {
            throw unreachable();
        }
        
        @Override
        protected AbstractConverter reduce(AbstractConverter that) {
            throw unreachable();
        }

        @Override
        protected AbstractConverter inverseWhenNotIdentity() {
            throw unreachable();
        }
        
        @Override
        protected String transformationLiteral() {
            return null;
        }
        
        private IllegalStateException unreachable()
        {
            return new IllegalStateException("code was reached, that is expected unreachable");
        }


        
    }
    
    // -- BINARY TREE (PAIR)

    /**
     * This class represents converters made up of two or more separate converters
     * (in matrix notation <code>[pair] = [left] x [right]</code>).
     */
    public static final class Pair extends AbstractConverter {

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
        public Pair(UnitConverter left, UnitConverter right)
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
        public Pair inverseWhenNotIdentity()
        {
            return new Pair(right.inverse(), left.inverse());
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
        public boolean equals(Object obj)
        {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Pair) {
                Pair that = (Pair) obj;
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
            if (this == obj) {
                return 0;
            }
            if (obj instanceof Pair) {
                Pair that = (Pair) obj;
                
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
}
