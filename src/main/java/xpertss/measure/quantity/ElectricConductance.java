package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Electric conductance. The metric system unit for this quantity "S" (Siemens).
 */
public interface ElectricConductance extends Quantity<ElectricConductance> {

   public static final Unit<ElectricConductance> UNIT = SI.SIEMENS;

}
