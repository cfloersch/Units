package xpertss.measure;

/**
 * Exception used to indicate a problem while dealing with units of measurement.
 * <p/>
 * This exception is used to indicate problems with creating, retrieving and
 * manipulating units of measurement objects.
 * <p/>
 * implSpec This class is intended for use in a single thread. Exception thrown
 * when errors occur during Units of Measurement operations.
 */
public class MeasurementException extends RuntimeException {

    /**
     * Constructs a {@code MeasurementException} with the given message.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     */
    public MeasurementException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@code MeasurementException} with the given cause.
     *
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     */
    public MeasurementException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code MeasurementException} with the given message and cause.
     *
     * @param message
     *            the detail message, or {@code null} if none.
     * @param cause
     *            the cause of this exception, or {@code null} if none.
     *
     */
    public MeasurementException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@code MeasurementException} with no given message.
     *
     */
    protected MeasurementException() {
        super();
    }
}
