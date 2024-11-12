/*
 * Copyright 2024 XpertSoftware
 *
 * Created By: cfloersch
 * Date: 11/12/2024
 */
package org.xpertss.unit;


import xpertss.measure.Dimension;
import xpertss.measure.UnitConverter;

public interface DimensionalModel {

   /**
    * Returns the fundamental dimension for the one specified. If the specified dimension is a
    * dimensional product, the dimensional product of its fundamental dimensions is returned.
    * Physical quantities are considered commensurate only if their fundamental dimensions are
    * equals using the current physics model.
    *
    * @param dimension
    *          the dimension for which the fundamental dimension is returned.
    * @return <code>this</code> or a rational product of fundamental dimension.
    */
   public Dimension getFundamentalDimension(Dimension dimension);


   /**
    * Returns the dimensional transform of the specified dimension. If the specified dimension
    * is a fundamental dimension or a product of fundamental dimensions the identity converter
    * is returned; otherwise the converter from the system unit (SI) of the specified dimension
    * to the system unit (SI) of its fundamental dimension is returned.
    *
    * @param dimension
    *          the dimension for which the dimensional transform is returned.
    * @return the dimensional transform (identity for fundamental dimensions).
    */
   public UnitConverter getDimensionalTransform(Dimension dimension);


   public static DimensionalModel current()
   {
      return StandardModel.INSTANCE;
   }

}
