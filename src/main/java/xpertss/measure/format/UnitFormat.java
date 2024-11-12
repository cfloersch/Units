package xpertss.measure.format;

import xpertss.measure.Unit;
import java.io.IOException;
import java.text.ParsePosition;

/**
 * Formats instances of {@link Unit} to a {@link String} or {@link Appendable} and parses a {@link CharSequence} to a {@link Unit}.
 *
 * <h1><a id="synchronization">Synchronization</a></h1>
 * <p/>
 * Instances of this class are not required to be thread-safe. It is recommended to use separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized externally.
 *
 * @see Unit
 */
public interface UnitFormat {
    /**
     * Formats the specified {@link Unit}.
     *
     * @param unit
     *            the {@link Unit} to format, not {@code null}
     * @param appendable
     *            the appendable destination.
     * @return the appendable destination passed in with formatted text appended.
     * @throws IOException
     *             if an error occurs while writing to the destination.
     */
    Appendable format(Unit<?> unit, Appendable appendable) throws IOException;

    /**
     * Formats the specified {@link Unit}.
     *
     * @param unit
     *            the {@link Unit} to format, not {@code null}
     * @return the string representation using the settings of this {@link UnitFormat}.
     */
    String format(Unit<?> unit);

    /**
     * Attaches a system-wide label to the specified unit.
     * <p>
     * If the specified label is already associated to a unit the previous association can be discarded or ignored. Depending on the
     * {@link UnitFormat} implementation, this call may be ignored if the particular unit already has a label.
     * </p>
     * If a {@link UnitFormat} implementation is explicitly <b>immutable</b>, similar to e.g. the result of <code>Collections.unmodifiableList()</code>,
     * then an {@linkplain UnsupportedOperationException} may be thrown upon this call.
     * <p>
     * Since <code>UnitFormat</code> implementations often apply the Singleton pattern, <b>system-wide</b> means, the label applies to every instance of
     * <code>UnitFormatA</code> implementing <code>UnitFormat</code> in this case, but not every instance of <code>UnitFormatB</code> or <code>UnitFormatC</code> both
     * also implementing <code>UnitFormat</code>. If a <code>UnitFormat</code> #isLocaleSensitive() it is up to the implementation, whether the label is
     * ignored, applied in a local-neutral manner (in addition to its local-sensitive information) or locale-specific.
     * </p>
     *
     * @param unit
     *            the unit being labeled.
     * @param label
     *            the new label for this unit.
     * @throws IllegalArgumentException
     *             if the label is not a valid identifier. This may include characters not supported by a particular {@link UnitFormat} implementation
     *             (e.g. only <b>ASCII</b> characters for certain devices)
     * @throws UnsupportedOperationException
     *             if the <code>label</code> operation is not supported by this {@link UnitFormat}
     */
    void label(Unit<?> unit, String label);

    /**
     * Returns <code>true</code> if this {@link UnitFormat} depends on a <code>Locale</code> to perform its tasks.
     * <p>
     * In environments that do not support a <code>Locale</code>, e.g. Java ME, this usually returns <code>false</code>.
     * </p>
     *
     * @return Whether this format depends on a locale.
     */
    default boolean isLocaleSensitive() {
        return false;
    }

    /**
     * Parses a portion of the specified <code>CharSequence</code> from the specified position to produce a {@link Unit}.
     * If parsing succeeds, then the index of the <code>pos</code> argument is updated to the index after the last character used.
     *
     * @param csq
     *            the <code>CharSequence</code> to parse.
     * @param pos
     *            a ParsePosition object holding the current parsing index and error parsing index information as described above.
     * @return the unit parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException
     *             if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
     * @since 2.0
     */
    Unit<?> parse(CharSequence csq, ParsePosition pos) throws IllegalArgumentException, MeasurementParseException;

    /**
     * Parses the text into an instance of {@link Unit}.
     * <p>
     * The parse must complete normally and parse the entire text. If the parse completes without reading the entire length of the text, an exception
     * is thrown. If any other problem occurs during parsing, an exception is thrown.
     * <p>
     *
     * @param csq
     *            the {@code CharSequence} to parse.
     * @return the unit parsed from the specified character sequence.
     * @throws MeasurementParseException
     *             if any problem occurs while parsing the specified character sequence (e.g. illegal syntax).
     * @throws UnsupportedOperationException
     *             if the {@link UnitFormat} is unable to parse.
     * @since 2.0
     */
    Unit<?> parse(CharSequence csq) throws MeasurementParseException;
}
