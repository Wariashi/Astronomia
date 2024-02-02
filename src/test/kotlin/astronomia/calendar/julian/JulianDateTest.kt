package astronomia.calendar.julian

import de.wariashi.astronomia.calendar.julian.JulianDate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class JulianDateTest {
    @Test
    fun testReferenceEpoch() {
        val referenceEpoch = LocalDateTime.of(-4713, 11, 24, 12, 0).atZone(ZoneOffset.UTC)
        val date = JulianDate.of(referenceEpoch)
        Assertions.assertEquals(0.0, date.getValue())
    }
}
