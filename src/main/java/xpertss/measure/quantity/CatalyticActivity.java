package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.ucum.SI;


/**
 * Catalytic activity. The metric system unit for this quantity is "kat" (katal).
 */
public interface CatalyticActivity extends Quantity<CatalyticActivity> {

   public static final Unit<CatalyticActivity> UNIT = SI.KATAL;

}
