package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

import static xpertss.measure.ucum.customary.IT.BITS_PER_SECOND;

/**
 * Speed of data-transmission. The system unit for this quantity is "bit/s" (bit per second).
 *
 * @see Information
 * @see <a href="http://en.wikipedia.org/wiki/Information_rate">Wikipedia: Information Rate</a>
 */
public interface InformationRate extends Quantity<InformationRate> {

   public static final Unit<InformationRate> UNIT = BITS_PER_SECOND;

}
