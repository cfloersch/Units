package org.xpertss.unit.converters;

import xpertss.measure.UnitConverter;
import java.util.*;

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
    public static ConverterCompositionHandler UNIT_COMPOSITION_HANDLER = new ConverterCompositionHandler();

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
        if(converter.isIdentity()) return this;
        if(this.isIdentity()) return converter;
        //[ahuber] we don't know how to reduce to a 'normal-form' with 'foreign' converters,
        // so we just return the straightforward composition, which no longer allows for proper
        // composition equivalence test
        return new ConverterPair(this, converter);
    }

    @Override
    public final List<? extends UnitConverter> getConversionSteps()
    {
        if(conversionSteps != null) return conversionSteps;
        if(this instanceof ConverterPair) {
            return conversionSteps = ((ConverterPair)this).createConversionSteps();
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
        if(isIdentity()) return value;
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
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
        if(this instanceof AddConverter) return Identity.ONE;
        if(this instanceof MultiplyConverter)
            return Optional.of(((MultiplyConverter)this).getFactor());
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
            return  (o instanceof Identity) ? 0 : -1;
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
    
}
