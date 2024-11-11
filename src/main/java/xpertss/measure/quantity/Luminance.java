package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Luminous intensity per unit area of light traveling in a given direction.
 * The system unit for this quantity is "cd/m²" (candela per square meter).
 *
 * @see LuminousIntensity
 * @see Area
 *
 * TODO Beyond spec
 */
public interface Luminance extends Quantity<Luminance> {

   public static final Unit<Luminance> UNIT = SI.CANDELAS_PER_SQUARE_METRE;

}
