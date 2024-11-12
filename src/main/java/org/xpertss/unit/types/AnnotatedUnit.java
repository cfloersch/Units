package org.xpertss.unit.types;


import org.xpertss.unit.AbstractUnit;
import xpertss.measure.Dimension;
import xpertss.measure.Quantity;
import xpertss.measure.Unit;
import xpertss.measure.UnitConverter;

import java.util.Map;
import java.util.Objects;

/**
 * This class represents an annotated unit.
 * <p/>
 * Instances of this class are created through the {@link AnnotatedUnit#of(Unit, String)}
 * method.
 *
 * @param <Q> The type of the quantity measured by this unit.
 */
public final class AnnotatedUnit<Q extends Quantity<Q>> extends AbstractUnit<Q> {

  /**
   * Holds the actual unit.
   */
  private final Unit<Q> actualUnit;

  /**
   * Holds the annotation.
   */
  private final String annotation;

  /**
   * Creates an annotated unit equivalent to the specified unit.
   *
   * @param actualUnit
   *          the unit to be annotated.
   * @param annotation
   *          the annotation.
   * @return the annotated unit.
   */
  public AnnotatedUnit(Unit<Q> actualUnit, String annotation)
  {
    this.actualUnit = actualUnit instanceof AnnotatedUnit ? ((AnnotatedUnit<Q>) actualUnit).actualUnit : actualUnit;
    this.annotation = annotation;
  }

  /**
   * Returns the actual unit of this annotated unit (never an annotated unit itself).
   *
   * @return the actual unit.
   */
  public Unit<Q> getActualUnit()
  {
    return actualUnit;
  }

  /**
   * Returns the annotation of this annotated unit.
   *
   * @return the annotation.
   */
  public String getAnnotation()
  {
    return annotation;
  }

  @Override
  public String getSymbol()
  {
    return actualUnit.getSymbol();
  }



  @Override
  public Dimension getDimension()
  {
    return actualUnit.getDimension();
  }

  @Override
  public Unit<Q> getSystemUnit()
  {
    return actualUnit.getSystemUnit();
  }

  @Override
  public UnitConverter toSystemUnit()
  {
    return actualUnit.toSystemUnit();
  }

  @Override
  public Map<? extends Unit<?>, Integer> getBaseUnits() {
    return actualUnit.getBaseUnits();
  }

  
  @Override
  public int hashCode()
  {
    return Objects.hash(actualUnit, annotation);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj instanceof AnnotatedUnit<?>) {
      AnnotatedUnit<?> other = (AnnotatedUnit<?>) obj;
      return Objects.equals(actualUnit, other.actualUnit) && Objects.equals(annotation, other.annotation);
    }
    return false;
  }
  
  /**
   * Creates an annotated unit equivalent to the specified unit.
   *
   * @param actualUnit
   *          the unit to be annotated.
   * @param annotation
   *          the annotation.
   * @return the annotated unit.
   */
  public static <Q extends Quantity<Q>> AnnotatedUnit<Q> of(Unit<Q> actualUnit, String annotation)
  {
      return new AnnotatedUnit<>(actualUnit, annotation);
  }

}
