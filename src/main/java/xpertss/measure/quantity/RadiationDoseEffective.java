package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Effective (or "equivalent") dose of radiation received by a human or some other
 * living organism. The metric system unit for this quantity is "Sv" (Sievert).
 */
public interface RadiationDoseEffective extends Quantity<RadiationDoseEffective> {

   public static final Unit<RadiationDoseEffective> UNIT = SI.SIEVERT;

}
