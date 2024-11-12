package org.xpertss.unit.math;


import java.util.Objects;

/**
 * Provides arithmetic on Java {@link Number}s utilizing a provided {@link NumberSystem}.    
 */
public final class Calculator {

    /**
     * Returns a new instance of a {@code Calculator} initialized with the default {@link NumberSystem}, 
     * as set at {@link NumberSystem#current()}
     * <p>
     * This implementation is *not* thread-safe, hence threads should not share instances of this. 
     * @return a {@code Calculator} initialized with the default {@link NumberSystem} 
     */
    private static Calculator getInstance()
    {
        return new Calculator(NumberSystem.current());
    }

    /**
     * Shortcut for {@code getDefault().load(number)}. See {@link #getInstance()} and {@link #load(Number)}
     * @param number
     * @return default {@code Calculator} with {@code number} loaded into its accumulator
     */
    public static Calculator of(Number number)
    {
        return getInstance().load(number);
    }





    private final NumberSystem ns;
    private Number acc = 0;
    
    /**
     * Returns a new instance of a {@code Calculator} initialized with the given {@link NumberSystem}.
     * @return a {@code Calculator} initialized with the given {@link NumberSystem}.
     * @param ns the {@link NumberSystem} 
     */
    private Calculator(NumberSystem ns)
    {
        this.ns = ns;
    }
    
    /**
     * Loads {@code number} into this {@code Calculator}´s accumulator. 
     * @param number
     * @return self
     */
    private Calculator load(Number number)
    {
        Objects.requireNonNull(number);
        this.acc = ns.narrow(number);
        return this;
    }
    
    /**
     * Adds {@code number} to this {@code Calculator}´s accumulator, 
     * then stores the result in the accumulator.
     * @param number
     * @return self
     */
    public Calculator add(Number number)
    {
        Objects.requireNonNull(number);
        acc = ns.add(acc, ns.narrow(number));    
        return this;
    }

    /**
     * Subtracts {@code number} from this {@code Calculator}´s accumulator, 
     * then stores the result in the accumulator.
     * @param number
     * @return self
     */
    public Calculator subtract(Number number)
    {
        Objects.requireNonNull(number);
        acc = ns.subtract(acc, ns.narrow(number));
        return this;
    }
    
    /**
     * Multiplies {@code number} with this {@code Calculator}´s accumulator, 
     * then stores the result in the accumulator.
     * @param number
     * @return self
     */
    public Calculator multiply(Number number)
    {
        acc = ns.multiply(acc, ns.narrow(number));    
        return this;
    }

    /**
     * Divides this {@code Calculator}´s accumulator by {@code number}, 
     * then stores the result in the accumulator.
     * @param number
     * @return self
     */
    public Calculator divide(Number number)
    {
        acc = ns.divide(acc, ns.narrow(number));    
        return this;
    }
    
    /**
     * Takes this {@code Calculator}´s accumulator to the integer power of {@code exponent},
     * then stores the result in the accumulator.
     * @param exponent
     * @return self
     */
    public Calculator power(int exponent)
    {
        acc = ns.power(acc, exponent);    
        return this;
    }
    
    /**
     * Calculates the absolute value of this {@code Calculator}´s accumulator,
     * then stores the result in the accumulator.
     * @return self
     */
    public Calculator abs()
    {
        acc = ns.abs(acc);
        return this;
    }

    /**
     * Calculates the additive inverse value of this {@code Calculator}´s accumulator,
     * then stores the result in the accumulator.
     * @return self
     */
    public Calculator negate()
    {
        acc = ns.negate(acc);
        return this;
    }

    /**
     * Calculates the multiplicative inverse value of this {@code Calculator}´s accumulator,
     * then stores the result in the accumulator.
     * @return self
     */
    public Calculator reciprocal()
    {
        acc = ns.reciprocal(acc);
        return this;
    }
    
    /**
     * Calculates Euler's constant taken to the power of this {@code Calculator}´s accumulator,
     * then stores the result in the accumulator.
     * @return self
     */
    public Calculator exp()
    {
        acc = ns.exp(acc);
        return this;
    }
    
    /**
     * Calculates the natural logarithm of this {@code Calculator}´s accumulator,
     * then stores the result in the accumulator.
     * @return self
     */
    public Calculator log()
    {
        acc = ns.log(acc);
        return this;
    }
    
    // -- TERMINALS
    
    /**
     * Allows to 'peek' at this {@code Calculator}´s accumulator. The {@link Number} returned is narrowed
     * to best represent the numerical value w/o loss of precision within the {@link NumberSystem} as 
     * configured for this {@code Calculator} instance.
     * @return a narrowed version of this {@code Calculator}´s accumulator
     */
    public Number peek()
    {
        return ns.narrow(acc);
    }
    
    /**
     * @return whether this {@code Calculator}´s accumulator is less than ONE
     */
    public boolean isLessThanOne()
    {
        return ns.isLessThanOne(acc);
    }


}