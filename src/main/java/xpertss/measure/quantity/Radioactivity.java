package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;


/**
 * Radioactive activity. The metric system unit for this quantity is "Bq"
 * (Becquerel).
 */
public interface Radioactivity extends Quantity<Radioactivity> {

   public static final Unit<Radioactivity> UNIT = SI.BECQUEREL;

}
