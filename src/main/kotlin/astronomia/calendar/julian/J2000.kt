package de.wariashi.astronomia.calendar.julian

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * The [J2000] epoch corresponds to the number of days that have passed since January 1, 2000, 12:00 TT in the Gregorian calendar.
 *
 * @param value the number of days that have passed since January 1, 2000, 12:00 TT in the Gregorian calendar
 */
class J2000(private val value: Double) {
    /**
     * @return the number of days that have passed since January 1, 2000, 12:00 TT in the Gregorian calendar
     */
    fun getValue(): Double {
        return value
    }

    /**
     * Creates a [JulianDate] which corresponds to this [J2000].
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
         * The offset between a [JulianDate] and the corresponding [J2000].
         */
        const val OFFSET_TO_JULIAN_DATE = -2_451_544.9992

        /**
         * The reference epoch, meaning that the [J2000] `0.0` corresponds to this date in the Gregorian calendar.
         */
        private val referenceEpoch =
            LocalDateTime.of(2000, 1, 1, 11, 58, 55, 816_000_000).atOffset(ZoneOffset.UTC).toInstant()

        /**
         * The number of milliseconds in a day.
         */
        private const val MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1_000

        /**
         * Creates a [J2000] representing the current time.
         *
         * @return a [J2000] representing the current time
         */
        fun now(): J2000 {
            val currentDateTime = Instant.now()
            return of(currentDateTime)
        }

        /**
         * Creates a [J2000] representing the given time.
         *
         * @param instant the time reference for creating the [J2000]
         *
         * @return a [J2000] representing the given time
         */
        fun of(instant: Instant): J2000 {
            val dateTimeInMilliseconds = (1_000 * instant.epochSecond) + (instant.nano / 1_000_000)
            val referenceInMilliseconds = (1_000 * referenceEpoch.epochSecond) + (referenceEpoch.nano / 1_000_000)
            val differenceInMilliseconds = dateTimeInMilliseconds - referenceInMilliseconds
            val julianDay = differenceInMilliseconds.toDouble() / MILLISECONDS_PER_DAY.toDouble()

            return J2000(julianDay)
        }
    }
}
