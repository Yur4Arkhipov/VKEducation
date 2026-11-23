package com.example.vkeducation.data.repository

import com.example.vkeducation.data.network.AppsApiService
import com.example.vkeducation.data.network.AppItemDto
import com.example.vkeducation.data.network.toDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AppsRepositoryImplTest {

    private lateinit var apiService: AppsApiService
    private lateinit var repository: AppsRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        repository = AppsRepositoryImpl(apiService)
    }

    @Test
    fun `getApps returns mapped domain list from API`() = runTest {
        val dtoList = listOf(
            AppItemDto("1", "Telegram", "Messenger", "Приложения", "https://example.com/tg.png"),
            AppItemDto("2", "YouTube", "Video platform", "Приложения", "https://example.com/yt.png")
        )

        coEvery { apiService.getCatalog() } returns dtoList

        val result = repository.getApps()

        assertEquals(2, result.size)
        assertEquals("Telegram", result[0].name)
        assertEquals("YouTube", result[1].name)
    }

    @Test
    fun `getApps maps DTO to Domain correctly for each item`() = runTest {
        val dto = AppItemDto(
            id = "abc-123",
            name = "VK",
            description = "Social network",
            category = "Приложения",
            iconUrl = "https://example.com/vk_icon.png"
        )

        coEvery { apiService.getCatalog() } returns listOf(dto)

        val result = repository.getApps()

        val domain = result[0]
        assertEquals("abc-123".hashCode(), domain.id)
        assertEquals("VK", domain.name)
        assertEquals("Social network", domain.description)
        assertEquals("Приложения", domain.category)
        assertEquals("https://example.com/vk_icon.png", domain.iconUrl)
    }

    @Test
    fun `getApps returns empty list when API returns empty`() = runTest {
        coEvery { apiService.getCatalog() } returns emptyList()

        val result = repository.getApps()

        assertTrue(result.isEmpty())
        assertEquals(0, result.size)
    }

    @Test
    fun `getApps throws exception when API call fails`() = runTest {
        val exception = IOException("Network error")
        coEvery { apiService.getCatalog() } throws exception

        try {
            repository.getApps()
        } catch (e: IOException) {
            assertEquals("Network error", e.message)
        }
    }

    @Test
    fun `getApps is suspend and can be called from coroutine`() = runTest {
        val dtoList = listOf(
            AppItemDto("1", "App1", "Desc1", "Приложения", "https://example.com/icon1.png")
        )
        coEvery { apiService.getCatalog() } returns dtoList

        val result = repository.getApps()

        assertEquals(1, result.size)
        assertEquals("App1", result[0].name)
    }

    @Test
    fun `getApps calls repository exactly once`() = runTest {
        val dtoList = listOf(
            AppItemDto("1", "Test", "Desc", "Приложения", "https://example.com/icon.png")
        )
        coEvery { apiService.getCatalog() } returns dtoList

        repository.getApps()

        coVerify(exactly = 1) { apiService.getCatalog() }
    }

    @Test
    fun `getApps maps multiple items with different categories`() = runTest {
        val dtoList = listOf(
            AppItemDto("1", "App1", "Desc", "Приложения", "https://example.com/icon1.png"),
            AppItemDto("2", "Game1", "Game desc", "Игры", "https://example.com/icon2.png"),
            AppItemDto("3", "Tool1", "Tool desc", "Производительность", "https://example.com/icon3.png")
        )
        coEvery { apiService.getCatalog() } returns dtoList

        val result = repository.getApps()

        assertEquals(3, result.size)
        assertEquals("Приложения", result[0].category)
        assertEquals("Игры", result[1].category)
        assertEquals("Производительность", result[2].category)
    }

    @Test
    fun `getApps preserves icon URLs from DTO`() = runTest {
        val iconUrls = listOf(
            "https://example.com/icon1.png",
            "https://example.com/icon2.webp",
            "https://cdn.example.com/icons/app3.jpg"
        )
        val dtoList = iconUrls.mapIndexed { index, url ->
            AppItemDto((index + 1).toString(), "App$index", "Desc", "Приложения", url)
        }
        coEvery { apiService.getCatalog() } returns dtoList

        val result = repository.getApps()

        result.forEachIndexed { index, appItem ->
            assertEquals(iconUrls[index], appItem.iconUrl)
        }
    }
}
