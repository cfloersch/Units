package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Dimensionless quantity.
 */
public interface Dimensionless extends Quantity<Dimensionless> {

    public static final Unit<Dimensionless> UNIT = Unit.ONE;

}
