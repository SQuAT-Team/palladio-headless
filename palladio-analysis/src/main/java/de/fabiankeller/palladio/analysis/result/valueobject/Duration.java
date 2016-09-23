package de.fabiankeller.palladio.analysis.result.valueobject;

import java.util.Locale;

/**
 * Represents a timespan.
 */
public class Duration implements ValueObject {
    private final double milliseconds;

    private Duration(final double milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static Duration ofMilliseconds(final double milliseconds) {
        return new Duration(milliseconds);
    }

    public static Duration ofSeconds(final double seconds) {
        return ofMilliseconds(1000.0 * seconds);
    }

    public static Duration ofMinutes(final double minutes) {
        return ofSeconds(60.0 * minutes);
    }

    public static Duration ofHours(final double hours) {
        return ofMinutes(60.0 * hours);
    }

    public static Duration ofDays(final double days) {
        return ofHours(24.0 * days);
    }

    public static Duration ofWeeks(final double weeks) {
        return ofDays(7.0 * weeks);
    }

    public static Duration ofYears(final double years) {
        return ofDays(365.25 * years);
    }

    @Override
    public String toHumanReadable() {
        return String.format(Locale.ROOT, "%.3fs", this.getSeconds());
    }

    public double getMilliseconds() {
        return this.milliseconds;
    }

    public double getSeconds() {
        return this.getMilliseconds() / 1000.0;
    }

    public double getMinutes() {
        return this.getSeconds() / 60.0;
    }

    public double getHours() {
        return this.getMinutes() / 60.0;
    }

    public double getDays() {
        return this.getHours() / 24.0;
    }

    public double getWeeks() {
        return this.getDays() / 7.0;
    }

    public double getYears() {
        return this.getDays() / 365.25;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Duration duration = (Duration) o;

        return Double.compare(duration.milliseconds, this.milliseconds) == 0;

    }

    @Override
    public int hashCode() {
        final long temp = Double.doubleToLongBits(this.milliseconds);
        return (int) (temp ^ (temp >>> 32));
    }
}
