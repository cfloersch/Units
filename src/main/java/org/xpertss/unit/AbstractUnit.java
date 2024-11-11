package org.xpertss.unit;

import xpertss.measure.Quantity;
import xpertss.measure.Unit;

public abstract class AbstractUnit<Q extends Quantity<Q>> extends Unit<Q> {

    protected AbstractUnit()
    {
        super();
    }

    protected AbstractUnit(String symbol)
    {
        super(symbol);
    }

    protected AbstractUnit(String symbol, String name)
    {
        super(symbol, name);
    }


}
