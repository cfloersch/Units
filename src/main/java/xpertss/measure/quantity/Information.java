package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

import static xpertss.measure.ucum.customary.IT.BIT;

/**
 * Measure of information. The metric system unit for this quantity is "bit".
 *
 * @see InformationRate
 * @see <a href="http://en.wikipedia.org/wiki/Units_of_information">Wikipedia:
 *    Units of Information</a>
 */
public interface Information extends Quantity<Information> {

   public static final Unit<Information> UNIT = BIT;

}
