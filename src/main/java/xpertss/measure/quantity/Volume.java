package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Amount of space occupied by a three-dimensional object or region of space.
 * The metric system unit for this quantity is "m³" (cubic metre).
 *
 * @see Length
 * @see Area
 * @see VolumetricDensity
 * @see VolumetricFlowRate
 */
public interface Volume extends Quantity<Volume> {

   public static final Unit<Volume> UNIT = SI.CUBIC_METRE;

}
