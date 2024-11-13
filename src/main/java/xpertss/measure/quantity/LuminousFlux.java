package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Luminous flux. The metric system unit for this quantity is "lm" (lumen).
 */
public interface LuminousFlux extends Quantity<LuminousFlux> {

   public static final Unit<LuminousFlux> UNIT = SI.LUMEN;

}
