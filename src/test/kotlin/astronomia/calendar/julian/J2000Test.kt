package astronomia.calendar.julian

import de.wariashi.astronomia.calendar.julian.J2000
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class J2000Test {
    @Test
    fun testReferenceEpoch() {
        val referenceEpoch = LocalDateTime.of(2000, 1, 1, 11, 58, 55, 816_000_000).atZone(ZoneOffset.UTC)
        val date = J2000.of(referenceEpoch)
        Assertions.assertEquals(0.0, date.getValue())
    }
}
