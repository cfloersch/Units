package xpertss.measure;

/**
 * A unit prefix is a specifier or mnemonic that is prepended to units of measurement
 * to indicate multiples or fractions of the units.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Unit_prefix">Wikipedia: Unit Prefix</a>
 */
public interface Prefix {
    /**
     * Returns the name of this prefix.
     *
     * @return this prefix name, not {@code null}.
     */
    String getName();

    /**
     * Returns the symbol of this prefix.
     *
     * @return this prefix symbol, not {@code null}.
     */
    public String getSymbol();

    /**
     * Returns the value of this prefix. If the {@code exponent} is different from {@code 1}, this value is the {@code base} part of the associated
     * factor in {@code base^exponent} representation.
     * 
     * @return The prefix value.
     */
    Number getValue();

    /**
     * Exponent part of the associated factor in {@code base^exponent} representation. For different factors, e.g. rational numbers like {@code 1/4}
     * the exponent is always {@code 1}.
     *
     * @return the exponent part of this prefix.
     */
    int getExponent();
}