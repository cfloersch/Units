package org.xpertss.unit.utils;

import xpertss.measure.Quantity;

public enum OperandMode {

    ALL_ABSOLUTE,
    ALL_RELATIVE,
    MIXED;

    public static OperandMode get(final Quantity<?> q1, final Quantity<?> q2)
    {
        if(q1.getScale() != q2.getScale()) {
            return OperandMode.MIXED;
        }
        return q1.getScale() == Quantity.Scale.RELATIVE
                                    ? OperandMode.ALL_ABSOLUTE
                                    : OperandMode.ALL_RELATIVE;
    }

    public boolean isAllRelative() {
        return this==ALL_RELATIVE;
    }
}
