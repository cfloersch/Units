package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

/**
 * Intensity, defined as the power transferred per unit area.
 * The SI unit for this quantity is Watt per square metre (W/m2).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Intensity_(physics)">Wikipedia: Intensity</a>
 */
public interface Intensity extends Quantity<Intensity> {

    public static final Unit<Intensity> UNIT = SI.WATT_PER_SQUARE_METRE;

}
