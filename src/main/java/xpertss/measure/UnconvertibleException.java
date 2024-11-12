package xpertss.measure;

/**
 * Signals that a problem of some sort has occurred due to the impossibility of
 * constructing a converter between two units. For example, the multiplication
 * of offset units are usually units not convertible to their
 * {@linkplain Unit#getSystemUnit() system unit}.
 */
public class UnconvertibleException extends MeasurementException {

    /**
     * Constructs a {@code UnconvertibleException} with the given message.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     */
    public UnconvertibleException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@code UnconvertibleException} with the given cause.
     *
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     */
    public UnconvertibleException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code UnconvertibleException} with the given message and cause.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     *
     */
    public UnconvertibleException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
