package de.wariashi.astronomia.calendar.julian

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * The [JulianDate] corresponds to the number of days that have passed since November 24, -4713, 12:00 UTC in the Gregorian calendar.
 *
 * @param value the number of days that have passed since November 24, -4713, 12:00 UTC in the Gregorian calendar
 */
class JulianDate(private val value: Double) {
    /**
     * @return the number of days that have passed since November 24, -4713, 12:00 UTC in the Gregorian calendar
     */
    fun getValue(): Double {
        return value
    }

    /**
     * Creates a [J2000] which corresponds to this [JulianDate].
     *
     * @return a corresponding [J2000]
     */
    fun toJ2000(): J2000 {
        return J2000(value + J2000.OFFSET_TO_JULIAN_DATE)
    }

    /**
     * Creates a [ModifiedJulianDate] which corresponds to this [JulianDate].
     *
     * @return a corresponding [ModifiedJulianDate]
     */
    fun toModifiedJulianDate(): ModifiedJulianDate {
        return ModifiedJulianDate(value + ModifiedJulianDate.OFFSET_TO_JULIAN_DATE)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        /**
         * The reference epoch, meaning that the [JulianDate] `0.0` corresponds to this date in the Gregorian calendar.
         */
        private val referenceEpoch = LocalDateTime.of(-4713, 11, 24, 12, 0).atOffset(ZoneOffset.UTC)

        /**
         * The number of milliseconds in a day.
         */
        private const val MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1_000

        /**
         * Creates a [JulianDate] representing the current time.
         *
         * @return a [JulianDate] representing the current time
         */
        fun now(): JulianDate {
            val currentDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
            return of(currentDateTime)
        }

        /**
         * Creates a [JulianDate] representing the given time.
         *
         * @param zonedDateTime the time reference for creating the [JulianDate]
         *
         * @return a [JulianDate] representing the given time
         */
        fun of(zonedDateTime: ZonedDateTime): JulianDate {
            val dateTimeInMilliseconds = (1_000 * zonedDateTime.toEpochSecond()) + (zonedDateTime.nano / 1_000_000)
            val referenceInMilliseconds = (1_000 * referenceEpoch.toEpochSecond()) + (referenceEpoch.nano / 1_000_000)
            val differenceInMilliseconds = dateTimeInMilliseconds - referenceInMilliseconds
            val julianDay = differenceInMilliseconds.toDouble() / MILLISECONDS_PER_DAY.toDouble()

            return JulianDate(julianDay)
        }
    }
}
