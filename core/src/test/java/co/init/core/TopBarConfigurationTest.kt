package co.init.core

import co.init.core.data.TopBarConfiguration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.Serializable

class TopBarConfigurationTest {

    @Test
    fun `check object creation with all parameters`() {
        val configuration = TopBarConfiguration(0, true)

        assertEquals(0, configuration.title)
        assertTrue(configuration.showBackButton)
    }

    @Test
    fun `check if TopBarConfiguration implements Serializable interface`() {
        assertTrue(TopBarConfiguration::class.java.interfaces.contains(Serializable::class.java))
    }
}