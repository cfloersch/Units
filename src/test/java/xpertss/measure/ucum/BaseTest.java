package xpertss.measure.ucum;

import org.junit.jupiter.api.Test;
import xpertss.measure.Quantity;
import xpertss.measure.quantity.Angle;

import static org.junit.jupiter.api.Assertions.*;
import static xpertss.measure.MetricPrefix.KILO;
import static xpertss.measure.ucum.Base.*;

class BaseTest {

    @Test
    public void testRadianDegree()
    {
        Quantity<Angle> radian = Quantity.of(1, RADIAN);
        Quantity<Angle> degree = radian.to(DEGREE);
        assertTrue(radian.isEquivalentTo(degree));
    }

    @Test
    public void testKiloGramPrefixOutput()
    {
        System.out.println(KILO(GRAM).getSymbol());
    }

}