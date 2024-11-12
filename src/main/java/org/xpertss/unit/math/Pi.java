package org.xpertss.unit.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Pi calculation with Machin's formula.
 *
 * @see <a href= "http://mathworld.wolfram.com/PiFormulas.html" >Pi Formulas</a>
 */
public final class Pi {


   private static final BigDecimal TWO = new BigDecimal("2");
   private static final BigDecimal THREE = new BigDecimal("3");
   private static final BigDecimal FOUR = new BigDecimal("4");
   private static final BigDecimal FIVE = new BigDecimal("5");
   private static final BigDecimal TWO_HUNDRED_THIRTY_NINE = new BigDecimal("239");

   /**
    * Memoization of Pi by number-of-digits, to match Pi's precision with that of
    * the current {@link MathContext}.
    */
   private static final Map<Integer, BigDecimal> piCache = new ConcurrentHashMap<>();

   // this is a utility class, don't instantiate
   private Pi() {}

   public static BigDecimal ofNumDigits(int numDigits)
   {
      if(numDigits<=0)
         throw new IllegalArgumentException("numDigits is required to be greater than zero");
      return piCache.computeIfAbsent(numDigits, key->calculatePi(numDigits));
   }

   /**
    * Calculates Pi up to numDigits.
    */
   private static BigDecimal calculatePi(int numDigits)
   {
      // adds an arbitrary safety margin of 10 digits to the requested number of digits
      // (this is a guess, without any particular research to back that up)
      final int calcDigits = numDigits + 10;
      return FOUR
         .multiply((FOUR.multiply(arccot(FIVE, calcDigits)))
            .subtract(arccot(TWO_HUNDRED_THIRTY_NINE, calcDigits)))
               .setScale(numDigits, RoundingMode.DOWN);
   }

   /** Compute arccot via the Taylor series expansion. */
   private static BigDecimal arccot(BigDecimal x, int numDigits)
   {
      BigDecimal unity = BigDecimal.ONE.setScale(numDigits, RoundingMode.DOWN);
      BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
      BigDecimal xpower = new BigDecimal(sum.toString());
      BigDecimal term = null;

      BigDecimal nearZero = BigDecimal.ONE.scaleByPowerOfTen(-numDigits);
      boolean add = false;
      // Add one term of Taylor series each time thru loop. Stop looping
      // when _term_
      // gets very close to zero.
      for (BigDecimal n = THREE; term == null || !term.equals(BigDecimal.ZERO); n = n.add(TWO)) {
         if (term != null && term.compareTo(nearZero) < 0)
            break;
         xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
         term = xpower.divide(n, RoundingMode.DOWN);
         sum = add ? sum.add(term) : sum.subtract(term);
         add = !add;
      }
      return sum;
   }


}
