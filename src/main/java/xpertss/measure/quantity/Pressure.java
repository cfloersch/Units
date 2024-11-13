package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Force applied uniformly over a surface. The metric system unit for this quantity is "Pa"
 * (Pascal).
 *
 * @see Force
 * @see Area
 */
public interface Pressure extends Quantity<Pressure> {

   public static final Unit<Pressure> UNIT = SI.PASCAL;

}
