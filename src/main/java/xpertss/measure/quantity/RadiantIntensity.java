package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

/**
 * Radiant flux emitted, reflected, transmitted or received, per unit solid
 * angle. This is a directional quantity.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Radiant_intensity">Wikipedia:
 *      Radiant intensity</a>
 * @see Power
 * @see SolidAngle
 */
public interface RadiantIntensity extends Quantity<RadiantIntensity> {

    public static final Unit<RadiantIntensity> UNIT = SI.WATT_PER_STERADIAN;

}
