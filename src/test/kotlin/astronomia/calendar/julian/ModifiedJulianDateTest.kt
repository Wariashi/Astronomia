package astronomia.calendar.julian

import de.wariashi.astronomia.calendar.julian.ModifiedJulianDate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class ModifiedJulianDateTest {
    @Test
    fun testReferenceEpoch() {
        val referenceEpoch = LocalDateTime.of(1858, 11, 17, 0, 0).atZone(ZoneOffset.UTC)
        val date = ModifiedJulianDate.of(referenceEpoch)
        Assertions.assertEquals(0.0, date.getValue())
    }
}
