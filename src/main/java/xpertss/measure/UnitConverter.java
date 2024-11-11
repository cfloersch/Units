package xpertss.measure;

import java.util.List;

/**
 * A converter of numeric values between different units.
 * <p/>
 * Instances of this class are obtained through the {@link Unit#getConverterTo(Unit)}
 * method.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Conversion_of_units"> Wikipedia:
 * Conversion of units</a>
 */
public interface UnitConverter {

   /**
    * Indicates if this converter is an identity converter. The identity converter returns
    * its input argument ({@code convert(x) == x}).
    * <p/>
    * Note: Identity converters are also always 'linear', see {@link UnitConverter#isLinear()}.
    *
    * @return {@code true} if this converter is an identity converter.
    */
   boolean isIdentity();

   /**
    * Indicates whether this converter represents a (one-dimensional) linear transformation,
    * that is a <a href="https://en.wikipedia.org/wiki/Linear_map">linear map (wikipedia)</a>
    * from a one-dimensional vector space (a scalar) to a one-dimensional vector space.
    * Typically from 'R' to 'R', with 'R' the real numbers.
    * <p/>
    * Given such a 'linear' converter 'A', let 'u', 'v' and 'r' be arbitrary numbers, then the
    * following must hold by definition:
    *
    * <ul>
    * <li>{@code A(u + v) == A(u) + A(v)}</li>
    * <li>{@code A(r * u) == r * A(u)}</li>
    * </ul>
    *
    * <p/>
    * Given a second 'linear' converter 'B', commutativity of composition follows by above
    * definition:
    *
    * <ul>
    * <li>{@code (A o B) (u) == (B o A) (u)}</li>
    * </ul>
    *
    * In other words, two 'linear' converters do have the property that
    * {@code A(B(u)) == B(A(u))}, meaning for 'A' and 'B' the order of their composition
    * does not matter. Expressed as Java code:
    * <p/>
    *{@code A.concatenate(B).convert(u) == B.concatenate(A).convert(u)}
    * <p/>
    * Note: For composing UnitConverters see also {@link UnitConverter#concatenate(UnitConverter)}.
    *
    * @return {@code true} if this converter represents a linear transformation;
    * {@code false} otherwise.
    *
    */
   boolean isLinear();

   /**
    * Returns the inverse of this converter. If {@code x} is a valid value, then
    * {@code x == inverse().convert(convert(x))} to within the accuracy of computer
    * arithmetic.
    *
    * @return the inverse of this converter.
    */
   UnitConverter inverse();

   /**
    * Converts a {@code Number} value.
    *
    * @param value
    *          the {@code Number} value to convert.
    * @return the {@code Number} value after conversion.
    */
   Number convert(Number value);


   /**
    * Concatenates this converter with another converter. The resulting converter is
    * equivalent to first converting by the specified converter (right converter),
    * and then converting by this converter (left converter).
    *
    * @param converter
    *          the other converter to concatenate with this converter.
    * @return the concatenation of this converter with the other converter.
    */
   UnitConverter concatenate(UnitConverter converter);

   /**
    * Returns the steps of fundamental converters making up this converter or {@code this}
    * if the converter is a fundamental converter.
    * <p/>
    * For example, {@code converter1.getConversionSteps()} returns {@code converter1} while
    * {@code converter1.concatenate(converter2).getConversionSteps()} returns
    * {@code converter1, converter2}.
    *
    * @return the list of fundamental converters which concatenated make up this converter.
    */
   List<? extends UnitConverter> getConversionSteps();

}