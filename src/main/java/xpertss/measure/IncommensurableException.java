package xpertss.measure;

/**
 * Signals that a problem of some sort has occurred due to incommensurable of
 * some quantities/units. Only commensurable quantity (quantities with the same
 * dimensions) may be compared, equated, added, or subtracted. Also, one unit
 * can be converted to another unit only if both units are commensurable.
 * <p/>
 * This is a <strong>checked</strong> exception, so it deliberately doesn't
 * inherit from <code>MeasurementException</code> like most other exceptions.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Unit_commensurability#Commensurability">Wikipedia: Unit Commensurability</a>
 */
public class IncommensurableException extends Exception {

    /**
     * Constructs a {@code IncommensurableException} with the given message.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     */
    public IncommensurableException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@code IncommensurableException} with the given cause.
     *
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     *
     */
    public IncommensurableException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code IncommensurableException} with the given message and cause.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     *
     */
    public IncommensurableException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
