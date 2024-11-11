package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Capacity of a physical system to do work. The metric system unit for this quantity
 * "J" (Joule).
 *
 * @see Force
 * @see Power
 * @see Time
 */
public interface Energy extends Quantity<Energy> {

   public static final Unit<Energy> UNIT = SI.JOULE;

}
