package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * This interface represents a wave property inversely related to wavelength.
 * The system unit for this quantity is "1/m" (reciprocal meters).
 *
 * @see Length
 * @see <a href="http://en.wikipedia.org/wiki/Wavenumber">Wikipedia: Wavenumber</a>
 *
 * TODO Beyond spec
 */
public interface WaveNumber extends Quantity<WaveNumber> {

   public static final Unit<WaveNumber> UNIT = SI.RECIPROCAL_METRE;

}
