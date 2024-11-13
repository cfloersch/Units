/**
 * [OPTIONAL] Provides quantitative properties or attributes of thing such as mass, time,
 * distance, heat, and angular separation. Quantities of different kinds are represented
 * by sub-types of the {@link xpertss.measure.Quantity} interface.
 * <p/>
 * This package supports <cite>measurable</cite> quantities, which can be expressed as
 * ({@link java.lang.Number}, {@link xpertss.measure.Unit}) tuples. Those tuples are not
 * necessarily used directly in numerically intensive code. They are more useful as
 * meta-data converted to the application internal representation (for example {@code
 * double} primitive type with the requirement to provide values in meters) before
 * computation begins.
 * <p/>
 * Quantity sub-types are also used as parameterized type to characterize generic
 * classes and provide additional compile time check. This technique is used extensively
 * by the {@link xpertss.measure.Unit} interface, but users can apply the same approach
 * to their own classes. In the example below, {@code Sensor}, {@code MyQuantity} and
 * {@code Vector3D} are user-defined classes:
 * <p/>
 * <code>
 *    // A general-purpose Sensor class used for temperature measurements:<br>
 *    Sensor<Temperature> sensor ...;<br>
 *    Temperature temp = sensor.getValue();<br><br>
 *
 *    // A vector of velocity in a three-dimensional space.<br>
 *    Unit<Speed> = metrePerSecond = METRE.divide(SECOND);<br>
 *    Vector3D<Speed> aircraftSpeed = new Vector3D(200.0, 50.0, -0.5, metrePerSecond);
 * </code>
 */
package xpertss.measure.quantity;
