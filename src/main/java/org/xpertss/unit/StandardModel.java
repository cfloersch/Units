/*
 * Copyright 2024 XpertSoftware
 *
 * Created By: cfloersch
 * Date: 11/12/2024
 */
package org.xpertss.unit;

import org.xpertss.unit.converters.AbstractConverter;
import xpertss.measure.Dimension;
import xpertss.measure.UnitConverter;

import java.util.Map;

public class StandardModel implements DimensionalModel {


   static final StandardModel INSTANCE = new StandardModel();

   @Override
   public Dimension getFundamentalDimension(Dimension dimension)
   {
      Map<? extends Dimension, Integer> dimensions = dimension.getBaseDimensions();
      if (dimensions == null) return dimension; // Fundamental dimension.
      // Dimensional Product.
      Dimension fundamentalProduct = Dimension.NONE;
      for (Map.Entry<? extends Dimension, Integer> e : dimensions.entrySet()) {
         fundamentalProduct = fundamentalProduct.multiply(this.getFundamentalDimension(e.getKey())).pow(e.getValue());
      }
      return fundamentalProduct;
   }

   @Override
   public UnitConverter getDimensionalTransform(Dimension dimension)
   {
      Map<? extends Dimension, Integer> dimensions = dimension.getBaseDimensions();
      if (dimensions == null) return AbstractConverter.IDENTITY; // Fundamental dimension.
      // Dimensional Product.
      UnitConverter toFundamental = AbstractConverter.IDENTITY;
      for (Map.Entry<? extends Dimension, Integer> e : dimensions.entrySet()) {
         UnitConverter cvtr = this.getDimensionalTransform(e.getKey());
         if (!(cvtr.isLinear()))
            throw new UnsupportedOperationException("Non-linear dimensional transform");
         int pow = e.getValue();
         if (pow < 0) { // Negative power.
            pow = -pow;
            cvtr = cvtr.inverse();
         }
         for (int j = 0; j < pow; j++) {
            toFundamental = toFundamental.concatenate(cvtr);
         }
      }
      return toFundamental;
   }
}
