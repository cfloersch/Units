package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Period of existence or persistence. The metric system unit for this quantity is "s"
 * (second).
 *
 * @see Frequency
 * @see Speed
 * @see AngularSpeed
 * @see Acceleration
 * @see AngularAcceleration
 * @see ElectricCurrent
 * @see MassFlowRate
 * @see VolumetricFlowRate
 * @see InformationRate
 * @see Power
 */
public interface Time extends Quantity<Time> {

   public static final Unit<Time> UNIT = SI.SECOND;

}
