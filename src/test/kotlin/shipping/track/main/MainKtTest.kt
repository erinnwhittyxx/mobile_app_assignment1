package shipping.track.main

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class MainKtTest{

    private val testMain: Sample = Sample()

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, testMain.main(40, 2))
    }
}