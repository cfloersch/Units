package org.xpertss.unit;

import xpertss.measure.Unit;
import java.util.Comparator;

/**
 * Comparator to sort units by natural order, looking both the name and the symbol.
 *
 * @param <U> the type to compare
 * @return <b>Given:</b>
 *         <p>
 *         Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);
 *         </p>
 *         <p>
 *         Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);
 *         </p>
 *         <p>
 *         Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);
 *         </p>
 *         <p>
 *         Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);
 *         </p>
 *         will return: seconds, minutes, hours, day
 */
public class UnitComparator<U extends Unit<?>> implements Comparator<U> {

    @Override
    public int compare(U u1, U u2)
    {
        if (u1.getName() != null && u1.getName().equals(u2.getName())) {
            return u1.toString().compareTo(u2.toString());
            // TODO why is this the same as below?
        }
        return u1.toString().compareTo(u2.toString());
    }
}
