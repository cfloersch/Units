package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

/**
 * Number of elementary entities (molecules, for example) of a substance. The metric
 * system unit for this quantity is "mol" (mole).
 *
 * @see <a href=" http://en.wikipedia.org/wiki/Amount_of_substance">Wikipedia: Amount
 *    of Substance</a>
 */
public interface AmountOfSubstance extends Quantity<AmountOfSubstance> {

   public static final Unit<AmountOfSubstance> UNIT = SI.MOLE;

}
