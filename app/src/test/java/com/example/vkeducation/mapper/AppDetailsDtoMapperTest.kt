package com.example.vkeducation.mapper

import com.example.vkeducation.data.network.AppDetailsDto
import com.example.vkeducation.data.network.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDetailsDtoMapperTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        val dto = AppDetailsDto(
            id = "456",
            name = "VK Education",
            description = "Educational platform",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png",
            developerName = "VK Company",
            developerUrl = "https://vk.com",
            screenshots = listOf("https://example.com/screen1.png", "https://example.com/screen2.png"),
            version = "2.1.0",
            downloads = 1000000L,
            rating = 4.7f
        )

        val domain = dto.toDomain()

        assertEquals("VK Education", domain.name)
        assertEquals("Educational platform", domain.description)
        assertEquals("Приложения", domain.category)
        assertEquals("https://example.com/icon.png", domain.iconUrl)
        assertEquals("VK Company", domain.developerName)
        assertEquals("https://vk.com", domain.developerUrl)
        assertEquals(listOf("https://example.com/screen1.png", "https://example.com/screen2.png"), domain.screenshots)
        assertEquals("2.1.0", domain.version)
        assertEquals(1000000L, domain.downloads)
        assertEquals(4.7f, domain.rating, 0.001f)
    }

    @Test
    fun `toDomain converts String id to Int via hashCode`() {
        val testCases = listOf(
            "1" to "1".hashCode(),
            "details-789" to "details-789".hashCode(),
            "" to "".hashCode()
        )

        for ((stringId, expectedHashCode) in testCases) {
            val dto = AppDetailsDto(
                id = stringId,
                name = "Test",
                description = "Desc",
                category = "Приложения",
                iconUrl = "https://example.com/icon.png",
                developerName = "Dev",
                developerUrl = "https://dev.com",
                screenshots = emptyList(),
                version = "1.0",
                downloads = 0L,
                rating = 0f
            )
            val domain = dto.toDomain()
            assertEquals("id hashCode for '$stringId'", expectedHashCode, domain.id)
        }
    }

    @Test
    fun `toDomain maps screenshots list correctly`() {
        val screenshots = listOf(
            "https://example.com/screenshot1.png",
            "https://example.com/screenshot2.png",
            "https://example.com/screenshot3.png"
        )

        val dto = AppDetailsDto(
            id = "1",
            name = "Test",
            description = "Desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png",
            developerName = "Dev",
            developerUrl = "https://dev.com",
            screenshots = screenshots,
            version = "1.0",
            downloads = 100L,
            rating = 4.0f
        )

        val domain = dto.toDomain()

        assertEquals(screenshots.size, domain.screenshots.size)
        screenshots.forEachIndexed { index, url ->
            assertEquals(url, domain.screenshots[index])
        }
    }

    @Test
    fun `toDomain handles numeric fields downloads and rating`() {
        val dto = AppDetailsDto(
            id = "1",
            name = "Test",
            description = "Desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png",
            developerName = "Dev",
            developerUrl = "https://dev.com",
            screenshots = emptyList(),
            version = "1.0",
            downloads = Long.MAX_VALUE,
            rating = 5.0f
        )

        val domain = dto.toDomain()

        assertEquals(Long.MAX_VALUE, domain.downloads)
        assertEquals(5.0f, domain.rating, 0.001f)
    }

    @Test
    fun `toDomain handles empty screenshots list`() {
        val dto = AppDetailsDto(
            id = "1",
            name = "Test",
            description = "Desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png",
            developerName = "Dev",
            developerUrl = "https://dev.com",
            screenshots = emptyList(),
            version = "1.0",
            downloads = 0L,
            rating = 0f
        )

        val domain = dto.toDomain()

        assertEquals(emptyList<String>(), domain.screenshots)
        assertEquals(0, domain.screenshots.size)
    }

    @Test
    fun `toDomain handles zero values for numeric fields`() {
        val dto = AppDetailsDto(
            id = "1",
            name = "Test",
            description = "Desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png",
            developerName = "Dev",
            developerUrl = "https://dev.com",
            screenshots = emptyList(),
            version = "0.0.1",
            downloads = 0L,
            rating = 0f
        )

        val domain = dto.toDomain()

        assertEquals(0L, domain.downloads)
        assertEquals(0f, domain.rating, 0.001f)
    }

    @Test
    fun `toDomain handles all string fields with special characters`() {
        val dto = AppDetailsDto(
            id = "app-123",
            name = "VK Education 📚",
            description = "Описание: обучение, тесты, курсы — всё в одном приложении!",
            category = "Образование",
            iconUrl = "https://example.com/icon.png?v=2&size=large",
            developerName = "VK Team ©",
            developerUrl = "https://vk.com/education?ref=test",
            screenshots = listOf("https://example.com/screen.png?size=hd"),
            version = "3.0.0-beta+build.123",
            downloads = 999999L,
            rating = 4.95f
        )

        val domain = dto.toDomain()

        assertEquals("VK Education 📚", domain.name)
        assertEquals("Описание: обучение, тесты, курсы — всё в одном приложении!", domain.description)
        assertEquals("Образование", domain.category)
        assertEquals("https://example.com/icon.png?v=2&size=large", domain.iconUrl)
        assertEquals("VK Team ©", domain.developerName)
        assertEquals("https://vk.com/education?ref=test", domain.developerUrl)
        assertEquals("3.0.0-beta+build.123", domain.version)
    }
}
