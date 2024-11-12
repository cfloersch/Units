package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import org.xpertss.unit.math.NumberSystem;
import xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * This class represents a converter adding a constant offset to numeric values
 * (<code>double</code> based).
 */
public final class AddConverter extends AbstractConverter {

  /**
   * Holds the offset.
   */
  private final Number offset;

  /**
   * Creates an additive converter having the specified offset.
   *
   * @param offset
   *          the offset value.
   */
  public AddConverter(Number offset)
  {
    this.offset = NumberSystem.current().narrow(offset);
  }

  /**
   * Returns the offset value for this add converter.
   *
   * @return the offset value.
   */
  public Number getOffset()
  {
    return offset;
  }
  
  @Override
  public boolean isIdentity()
  {
    return NumberSystem.current().isZero(offset);
  }

  @Override
  protected boolean canReduceWith(AbstractConverter that)
  {
  	return that instanceof AddConverter;
  }

  @Override
  protected AbstractConverter reduce(AbstractConverter that)
  {
    NumberSystem ns = NumberSystem.current();
    Number newOffset = ns.add(offset, ((AddConverter)that).offset);
    return new AddConverter(newOffset);
  }
  
  @Override
  public AddConverter inverseWhenNotIdentity()
  {
    NumberSystem ns = NumberSystem.current();
    Number newOffset = ns.negate(offset);
    return new AddConverter(newOffset);
  }

  @Override
  protected Number convertWhenNotIdentity(Number value)
  {
      return Calculator.of(offset)
                        .add(value)
                        .peek();
  }

  @Override
  public String transformationLiteral()
  {
    NumberSystem ns = NumberSystem.current();
    int signum = ns.signum(offset);
    return String.format("x -> x %s %s", signum < 0 ? "-" : "+", ns.abs(offset));
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj instanceof AddConverter) {
      AddConverter other = (AddConverter) obj;
      return Objects.equals(offset, other.offset);
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(offset);
  }

  @Override
  public boolean isLinear()
  {
    return isIdentity();
  }


  public Number getValue()
  {
    return offset;
  }

  @Override
  public int compareTo(UnitConverter o)
  {
    if (this == o) return 0;
    if (o instanceof AddConverter) {
      NumberSystem ns = NumberSystem.current();
      return ns.compare(this.getValue(), ((AddConverter) o).getValue());
    }
    return -1;
  }




}
