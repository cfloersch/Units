/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.xpertss.unit.converters;

import org.xpertss.unit.math.Calculator;
import org.xpertss.unit.math.Calculus;
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
    this.offset = Calculus.currentNumberSystem().narrow(offset);
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
    return Calculus.currentNumberSystem().isZero(offset);
  }

  @Override
  protected boolean canReduceWith(AbstractConverter that)
  {
  	return that instanceof AddConverter;
  }

  @Override
  protected AbstractConverter reduce(AbstractConverter that)
  {
    NumberSystem ns = Calculus.currentNumberSystem();
    Number newOffset = ns.add(offset, ((AddConverter)that).offset);
    return new AddConverter(newOffset);
  }
  
  @Override
  public AddConverter inverseWhenNotIdentity()
  {
    NumberSystem ns = Calculus.currentNumberSystem();
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
    NumberSystem ns = Calculus.currentNumberSystem();
    int signum = ns.signum(offset);
    return String.format("x -> x %s %s", signum < 0 ? "-" : "+", ns.abs(offset));
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
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
    if (this == o) {
      return 0;
    }
    if (o instanceof AddConverter) {
      NumberSystem ns = Calculus.currentNumberSystem();  
      return ns.compare(this.getValue(), ((AddConverter) o).getValue());
    }
    return -1;
  }




}
