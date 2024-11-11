package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;


/**
 * Volume of fluid passing a point in a system per unit of time. The system unit for
 * this quantity is "m³/s" (cubic metre per second).
 *
 * @see Volume
 * @see Time
 * @see <a href="http://en.wikipedia.org/wiki/Rate_of_fluid_flow">Wikipedia: Volumetric
 *    Flow Rate</a>
 *
 * TODO Beyond spec
 */
public interface VolumetricFlowRate extends Quantity<VolumetricFlowRate> {

   public static final Unit<VolumetricFlowRate> UNIT = null;   // TODO

}
