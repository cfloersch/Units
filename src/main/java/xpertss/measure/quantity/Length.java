package xpertss.measure.quantity;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.units.SI;

/**
 * Extent of something along its greatest dimension or the extent of space between
 * two objects or places. The metric system unit for this quantity is "m" (metre).
 *
 * @see Area
 * @see Volume
 * @see Angle
 * @see SolidAngle
 * @see Speed
 */
public interface Length extends Quantity<Length> {


   public static final Unit<Length> UNIT = SI.METRE;


   // TODO How can this be called? There is no impl of this to the best of my knowledge?

   /**
    * Returns the product of this {@code Length} with the one specified resulting in
    * {@link Area}
    *
    * @param that the {@code Length} multiplier.
    * @return <code>this * that</code>.
    */
   Area multiply(Length that);

}
