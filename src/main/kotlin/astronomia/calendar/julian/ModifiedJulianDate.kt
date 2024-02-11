package de.wariashi.astronomia.calendar.julian

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * The [ModifiedJulianDate] corresponds to the number of days that have passed since November 17, 1858, 0:00 UTC in the Gregorian calendar.
 *
 * @param value the number of days that have passed since November 17, 1858, 0:00 UTC in the Gregorian calendar
 */
class ModifiedJulianDate(private val value: Double) {
    /**
     * @return the number of days that have passed since November 17, 1858, 0:00 UTC in the Gregorian calendar
     */
    fun getValue(): Double {
        return value
    }

    /**
     * Creates a [JulianDate] which corresponds to this [ModifiedJulianDate].
     *
     * @return a corresponding [JulianDate]
     */
    fun toJulianDate(): JulianDate {
        return JulianDate(value - OFFSET_TO_JULIAN_DATE)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        /**
         * The offset between a [JulianDate] and the corresponding [ModifiedJulianDate].
         */
        const val OFFSET_TO_JULIAN_DATE = -2_400_000.5

        /**
         * The reference epoch, meaning that the [ModifiedJulianDate] `0.0` corresponds to this date in the Gregorian calendar.
         */
        private val referenceEpoch = LocalDateTime.of(1858, 11, 17, 0, 0).atOffset(ZoneOffset.UTC)

        /**
         * The number of milliseconds in a day.
         */
        private const val MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1_000

        /**
         * Creates a [ModifiedJulianDate] representing the current time.
         *
         * @return a [ModifiedJulianDate] representing the current time
         */
        fun now(): ModifiedJulianDate {
            val currentDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
            return of(currentDateTime)
        }

        /**
         * Creates a [ModifiedJulianDate] representing the given time.
         *
         * @return a [ModifiedJulianDate] representing the given time
         */
        fun of(zonedDateTime: ZonedDateTime): ModifiedJulianDate {
            val dateTimeInMilliseconds = (1_000 * zonedDateTime.toEpochSecond()) + (zonedDateTime.nano / 1_000_000)
            val referenceInMilliseconds = (1_000 * referenceEpoch.toEpochSecond()) + (referenceEpoch.nano / 1_000_000)
            val differenceInMilliseconds = dateTimeInMilliseconds - referenceInMilliseconds
            val julianDay = differenceInMilliseconds.toDouble() / MILLISECONDS_PER_DAY.toDouble()

            return ModifiedJulianDate(julianDay)
        }
    }
}
