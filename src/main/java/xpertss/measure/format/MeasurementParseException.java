package xpertss.measure.format;

import xpertss.measure.MeasurementException;

/**
 * Signals that an error has been reached unexpectedly while parsing.
 */
public class MeasurementParseException extends MeasurementException {

    /**
     * The zero-based character position in the string being parsed at which the error was found while parsing.
     */
    private int position;

    /** The original input data. */
    private CharSequence data;

    /**
     * Constructs a MeasurementParseException with the specified detail message, parsed text and index. A detail message is a String that describes
     * this particular exception.
     *
     * @param message
     *            the detail message
     * @param parsedData
     *            the parsed text, should not be null
     * @param position
     *            the position where the error was found while parsing.
     */
    public MeasurementParseException(String message, CharSequence parsedData, int position) {
        super(message);
        this.data = parsedData;
        this.position = position;
    }

    /**
     * Constructs a MeasurementParseException with the parsed text and offset. A detail message is a String that describes this particular exception.
     *
     * @param parsedData
     *            the parsed text, should not be null
     * @param position
     *            the position where the error is found while parsing.
     */
    public MeasurementParseException(CharSequence parsedData, int position) {
        this("Parse Error", parsedData, position);
    }

    /**
     * Constructs a MeasurementParseException with the specified cause.
     *
     * @param cause
     *            the root cause
     */
    public MeasurementParseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a MeasurementParseException with the specified detail message.
     *
     * @param message
     *            the detail message
     */
    public MeasurementParseException(String message) {
        super(message);
    }

    /**
     * Returns the position where the error was found.
     *
     * @return the position of the error
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the string that was being parsed.
     *
     * @return the parsed string, or {@code null}, if {@code null} was passed as input.
     */
    public String getParsedString() {
        if (data == null) {
            return null;
        }
        return data.toString();
    }
}
