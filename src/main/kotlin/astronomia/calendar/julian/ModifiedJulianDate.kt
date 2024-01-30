package de.wariashi.astronomia.calendar.julian

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class ModifiedJulianDate(private var value: Double) {

    fun getValue(): Double {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {

        private val referenceEpoch = LocalDateTime.of(1858, 11, 17, 0, 0).atOffset(ZoneOffset.UTC)
        private const val SECONDS_PER_DAY = 24 * 60 * 60

        fun now(): ModifiedJulianDate {
            val currentDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
            val currentTimeInEpochSeconds = currentDateTime.toEpochSecond()
            val referenceSeconds = referenceEpoch.toEpochSecond()
            val differenceInSeconds = currentTimeInEpochSeconds - referenceSeconds
            val julianDay = differenceInSeconds.toDouble() / SECONDS_PER_DAY.toDouble()

            return ModifiedJulianDate(julianDay)
        }
    }
}
