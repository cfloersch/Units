package org.xpertss.unit.math;

/**
 * Provides arithmetic {@link Number} operations on an implementation specific set of 
 * {@link Number} types.
 * <p>
 * Let <em>S</em> be the set of possible {@link Number} values within the (given) set of 
 * {@link Number} types.<br>
 * Then <em>S</em> is <a href="https://en.wikipedia.org/wiki/Closure_(mathematics)">closed</a> 
 * under the collection of {@link NumberSystem}'s methods.   
 * 
 * <dl>
 * <dt><span class="strong">Implementation Note:</span></dt><dd>Given <em>S</em> the set of
 * possible {@link Number} values within implementation specific set of (supported)
 * {@link Number} types:<br>
 * - implemented methods must support any {@link Number} arguments from <em>S</em><br>
 * - implemented methods must also have their {@link Number} results to be in <em>S</em></dd>
 * </dl>
 *  
 * @see <a href="https://en.wikipedia.org/wiki/Closure_(mathematics)">Closure (wikipedia)</a>
 */
public interface NumberSystem {
    
    /**
     * Immutable value type, holder of 2 numbers.
     */
    public final static class DivisionResult {
        /**
         * originating from x / y
         */
        public final Number quotient;
        /**
         * originating from x % y 
         */
        public final Number remainder;
        
        public static DivisionResult of(Number quotient, Number remainder)
        {
            return new DivisionResult(quotient, remainder);
        }
        
        private DivisionResult(Number quotient, Number remainder)
        {
            this.quotient = quotient;
            this.remainder = remainder;
        }
    }

    /**
     * Returns the sum of given {@code x} and {@code y} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param x
     * @param y
     * @return {@code x + y}  
     */
    Number add(Number x, Number y);
    
    /**
     * Returns the difference of given {@code x} and {@code y} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param x
     * @param y
     * @return {@code x - y}  
     */
    Number subtract(Number x, Number y);

    /**
     * Returns the product of given {@code x} and {@code y} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param x
     * @param y
     * @return {@code x * y}  
     */
    Number multiply(Number x, Number y);
    
    /**
     * Returns the division of given {@code x} and {@code y} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param x
     * @param y
     * @return {@code x / y}  
     */
    Number divide(Number x, Number y);
    
    /**
     * Returns a two-element Number array containing {x / y, x % y} 
     * @param x
     * @param y
     * @param roundRemainderTowardsZero - whether the division remainder should be rounded towards zero 
     * @return
     */
    Number[] divideAndRemainder(Number x, Number y, boolean roundRemainderTowardsZero);
    
    /**
     * Returns given {@code number} to the power of {@code exponent} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param number
     * @param exponent - an integer
     * @return number^exponent
     * @throws ArithmeticException if {@code number} and {@code exponent} are ZERO 
     */
    Number power(Number number, int exponent);
    
    /**
     * Returns the reciprocal of given {@code number} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param number
     * @return {@code number^-1}  
     */    
    Number reciprocal(Number number);
    
    /**
     * Returns the negation of given {@code number} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param number
     * @return {@code -number}  
     */    
    Number negate(Number number);
    
    /**
     * Returns the signum function of given {@code number}.
     * 
     * @param number
     * @return {@code signum(number)}  
     */
    int signum(Number number);
    
    /**
     * Returns the absolute of given {@code number} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param number
     * @return {@code abs(number)}  
     */
    Number abs(Number number);
    
    /**
     * Returns Euler's Constant to the power of of given {@code number} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     * 
     * @param number
     * @return {@code e}^number, with {@code e} Euler's Constant)
     */
    Number exp(Number number);

    /**
     * Returns the natural logarithm of given {@code number} as a {@link Number} that best
     * represents the arithmetic result within the set of number types this NumberSystem
     * supports.
     *  
     * @param number
     * @return natural logarithm of number
     */
    Number log(Number number);
    
    
    /**
     * 'Narrows' given {@code number} as a {@link Number} that best
     * represents the numeric value within the set of number types this NumberSystem
     * supports.
     * <p>
     * eg. A BigInteger that is within range of Java's {@code Long} type can be narrowed to
     * Long w/o loss of precision.
     * 
     * @param number
     * @return 'best' representation of {@code number} w/o loss of precision
     */
    Number narrow(Number number);

    /**
     * Compares two {@code Number} values numerically.
     *
     * @param  x
     * @param  y
     * @return the value {@code 0} if {@code x == y};
     *         a value less than {@code 0} if {@code x < y}; and
     *         a value greater than {@code 0} if {@code x > y}
     */
    int compare(Number x, Number y);
    
    boolean isZero(Number number);
    boolean isOne(Number number);
    boolean isLessThanOne(Number number);
    
    /**
     * Checks whether given {@code number} has fractional parts or not.
     * @param number
     * @return whether {@code number} represents a whole number
     */
    boolean isInteger(Number number);
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    default boolean equals(Number x, Number y)
    {
        if(x == y) {
            return true;
        }
        if(!x.getClass().equals(y.getClass())) {
            return false;
        }
        return x.equals(y);
    }


    static NumberSystem current()
    {
        return DefaultNumberSystem.INSTANCE;
    }

    

}
