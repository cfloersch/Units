package org.xpertss.unit.converters;

import org.xpertss.unit.simplify.CompositionTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

/**
 * Functional interface for handling the composition (concatenation) of two unit converters.
 */
public class ConverterCompositionHandler {


    private final static Map<Class<? extends AbstractConverter>, Integer> normalFormOrder = new HashMap<>(9);
    static {
        normalFormOrder.put(AbstractConverter.IDENTITY.getClass(), 0);
        normalFormOrder.put(PowerOfIntConverter.class, 1);
        normalFormOrder.put(RationalConverter.class, 2);
        normalFormOrder.put(PowerOfPiConverter.class, 3);
        normalFormOrder.put(DoubleMultiplyConverter.class, 4);
        normalFormOrder.put(AddConverter.class, 5);
        normalFormOrder.put(LogConverter.class, 6);
        normalFormOrder.put(ExpConverter.class, 7);
        normalFormOrder.put(ConverterPair.class, 99);
    }


    /**
     * Takes two converters {@code left}, {@code right} and returns a (not necessarily new) 
     * converter that is equivalent to the mathematical composition of these:
     * <p/>
     * compose(left, right) === left o right 
     * <p/>
     * Implementation Note: Instead of using AbstractConverter as parameter 
     * and result types, this could be generalized to UnitConverter, but that 
     * would require some careful changes within AbstractConverter itself.
     *  
     * @param left
     * @param right
     * @param canReduce
     * @param doReduce
     * @return
     */
    public AbstractConverter compose(
            AbstractConverter left, 
            AbstractConverter right,
            BiPredicate<AbstractConverter, AbstractConverter> canReduce,
            BinaryOperator<AbstractConverter> doReduce)
    {
        if(left.isIdentity()) {
            if(right.isIdentity()) {
                return isNormalFormOrderWhenIdentity(left, right) ? left : right;
            }
            return right;
        }
        if(right.isIdentity()) return left;

        if(canReduce.test(left, right)) {
            return doReduce.apply(left, right);
        }

        final boolean commutative = left.isLinear() && right.isLinear();
        final boolean swap = commutative && !isNormalFormOrderWhenCommutative(left, right);

        final ConverterPair nonSimplifiedForm = (swap)
                    ? new ConverterPair(right, left)
                    : new ConverterPair(left, right);

        return new CompositionTask(
                this::isNormalFormOrderWhenIdentity,
                this::isNormalFormOrderWhenCommutative,
                canReduce,
                doReduce)
           .reduceToNormalForm(nonSimplifiedForm.getConversionSteps());

    }



    private boolean isNormalFormOrderWhenIdentity(AbstractConverter a, AbstractConverter b)
    {
        if(a.getClass().equals(b.getClass())) return true;
        return normalFormOrder.get(a.getClass()) <= normalFormOrder.get(b.getClass());
    }

    private boolean isNormalFormOrderWhenCommutative(AbstractConverter a, AbstractConverter b)
    {
        if(a.getClass().equals(b.getClass())) {
            if(a instanceof PowerOfIntConverter) {
                return  ((PowerOfIntConverter)a).getBase() <= ((PowerOfIntConverter)b).getBase();
            }
            return true;
        }

        Integer orderA = Objects.requireNonNull(normalFormOrder.get(a.getClass()),
           ()->String.format("no normal-form order defined for class '%s'", a.getClass().getName()));
        Integer orderB = Objects.requireNonNull(normalFormOrder.get(b.getClass()),
           ()->String.format("no normal-form order defined for class '%s'", b.getClass().getName()));

        return orderA <= orderB;
    }



}
