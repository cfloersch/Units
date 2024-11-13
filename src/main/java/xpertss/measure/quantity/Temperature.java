package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.Base;


/**
 * Degree of hotness or coldness of a body or an environment. The metric system unit
 * for this quantity is "K" (Kelvin).
 */
public interface Temperature extends Quantity<Temperature> {

   public static final Unit<Temperature> UNIT = Base.KELVIN;

}
