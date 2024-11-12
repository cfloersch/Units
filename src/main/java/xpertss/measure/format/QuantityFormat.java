package xpertss.measure.format;

import xpertss.measure.Quantity;
import java.io.IOException;
import java.text.ParsePosition;

/**
 * Formats instances of {@link Quantity}.
 *
 * <h3><a id="synchronization">Synchronization</a></h3>
 * Instances of this class are not required to be thread-safe. It is recommended to use separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized externally.
 *
 * @see Quantity
 */
public interface QuantityFormat {

    /**
     * Formats the specified quantity into an {@code Appendable}.
     *
     * @param quantity
     *          the quantity to format.
     * @param destination
     *          the appendable destination.
     * @return the specified {@code Appendable}.
     * @throws IOException
     *           if an I/O exception occurs.
     */
    public Appendable format(Quantity<?> quantity, Appendable destination) throws IOException;

    /**
     * Formats the specified {@link Quantity}.
     *
     * @param quantity
     *            the {@link Quantity} to format, not {@code null}
     * @return the string representation using the settings of this {@link QuantityFormat}.
     */
    String format(Quantity<?> quantity);

    /**
     * Parses a portion of the specified {@code CharSequence} from the specified position to produce a {@link Quantity}.
     * If parsing succeeds, then the index of the {@code pos} argument is updated to the index after the last character used.
     *
     * @param csq
     *          the {@code CharSequence} to parse.
     * @param pos
     *          a ParsePosition object holding the current parsing index and error parsing index information as described above.
     * @return the quantity parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException
     *           if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
     */
    public Quantity<?> parse(CharSequence csq, ParsePosition pos) throws IllegalArgumentException, MeasurementParseException;

    /**
     * Parses a portion of the specified {@code CharSequence} from the specified position to produce a {@link Quantity}.
     *
     * @param csq
     *          the {@code CharSequence} to parse.
     * @return the quantity parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException
     *           if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
     */
    public Quantity<?> parse(CharSequence csq) throws MeasurementParseException;

    /**
     * Returns {@code true} if this {@link QuantityFormat} depends on a {@code Locale} to perform its tasks.
     * <p>
     * In environments that do not support a {@code Locale}, e.g. Java ME, this usually returns {@code false}.
     * </p>
     *
     * @return whether this format depends on the locale.
     */
    default boolean isLocaleSensitive() {
        return false;
    }
}
