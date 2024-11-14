package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;

/**
 * Radiance of a surface per unit frequency or wavelength. The latter is
 * commonly measured in W⋅sr−1⋅m−2⋅nm−1. This is a directional quantity. This is
 * sometimes also confusingly called "spectral intensity".
 *
 * @see <a href="https://en.wikipedia.org/wiki/Radiance#Radiance">Wikipedia:
 *      Radiance</a>
 * @see RadiantIntensity
 */
public interface Radiance extends Quantity<Radiance> {
    public static final Unit<Radiance> UNIT = SI.WATT_PER_STERADIAN_PER_SQUARE_METRE;
}