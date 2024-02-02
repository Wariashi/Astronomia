package de.wariashi.astronomia.calendar.julian

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * The [ModifiedJulianDate] corresponds to the number of days that have passed since November 17, 1858, 0:00 in the Gregorian calendar.
 *
 * @param value the number of days that have passed since November 17, 1858, 0:00 in the Gregorian calendar
 */
class ModifiedJulianDate(private var value: Double) {
    /**
     * @return the number of days that have passed since November 17, 1858, 0:00 in the Gregorian calendar
     */
    fun getValue(): Double {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        /**
         * The reference epoch, meaning that the [ModifiedJulianDate] `0.0` corresponds to this date in the Gregorian calendar.
         */
        private val referenceEpoch = LocalDateTime.of(1858, 11, 17, 0, 0).atOffset(ZoneOffset.UTC)

        /**
         * The number of seconds in a day.
         */
        private const val SECONDS_PER_DAY = 24 * 60 * 60

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
            val currentTimeInEpochSeconds = zonedDateTime.toEpochSecond()
            val referenceSeconds = referenceEpoch.toEpochSecond()
            val differenceInSeconds = currentTimeInEpochSeconds - referenceSeconds
            val julianDay = differenceInSeconds.toDouble() / SECONDS_PER_DAY.toDouble()

            return ModifiedJulianDate(julianDay)
        }
    }
}
