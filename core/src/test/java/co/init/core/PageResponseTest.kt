package co.init.core

import co.init.core.data.PageResponse
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.Serializable

class PageResponseTest {

    @Test
    fun `test object creation`() {
        val pageResponse = PageResponse(1, listOf("test"), 2, 3)

        assertEquals(1, pageResponse.page)
        assertEquals(listOf("test"), pageResponse.results)
        assertEquals(2, pageResponse.totalPages)
        assertEquals(3, pageResponse.totalResults)
    }

    @Test
    fun `test if all fields has SerializedName annotation`() {
        val pageResponseClass = PageResponse::class.java

        val pageField = pageResponseClass.getDeclaredField("page")
        val resultsField = pageResponseClass.getDeclaredField("results")
        val totalPagesField = pageResponseClass.getDeclaredField("totalPages")
        val totalResultsField = pageResponseClass.getDeclaredField("totalResults")

        assertTrue(pageField.isAnnotationPresent(SerializedName::class.java))
        assertTrue(resultsField.isAnnotationPresent(SerializedName::class.java))
        assertTrue(totalPagesField.isAnnotationPresent(SerializedName::class.java))
        assertTrue(totalResultsField.isAnnotationPresent(SerializedName::class.java))
    }

    @Test
    fun `test page response implements Serializable interface`() {
        assertTrue(PageResponse::class.java.interfaces.contains(Serializable::class.java))
    }

    @Test
    fun `test copy function`() {
        val originalPageResponse = PageResponse(1, listOf("test"), 2, 3)
        val copyPageResponse = originalPageResponse.copy(page = 10, totalPages = 20)

        assertEquals(10, copyPageResponse.page)
        assertEquals(20, copyPageResponse.totalPages)

        assertEquals(originalPageResponse.results, copyPageResponse.results)
        assertEquals(originalPageResponse.totalResults, copyPageResponse.totalResults)
    }
}