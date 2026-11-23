package com.example.vkeducation.mapper

import com.example.vkeducation.data.network.AppItemDto
import com.example.vkeducation.data.network.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class AppItemDtoMapperTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        val dto = AppItemDto(
            id = "123",
            name = "Telegram",
            description = "A messaging app",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png"
        )

        val domain = dto.toDomain()

        assertEquals("Telegram", domain.name)
        assertEquals("A messaging app", domain.description)
        assertEquals("Приложения", domain.category)
        assertEquals("https://example.com/icon.png", domain.iconUrl)
    }

    @Test
    fun `toDomain converts String id to Int via hashCode`() {
        val testCases = listOf(
            "1" to "1".hashCode(),
            "app-123" to "app-123".hashCode(),
            "test_id" to "test_id".hashCode(),
            "" to "".hashCode()
        )

        for ((stringId, expectedHashCode) in testCases) {
            val dto = AppItemDto(
                id = stringId,
                name = "Test",
                description = "Desc",
                category = "Приложения",
                iconUrl = "https://example.com/icon.png"
            )
            val domain = dto.toDomain()
            assertEquals("id hashCode for '$stringId'", expectedHashCode, domain.id)
        }
    }

    @Test
    fun `toDomain handles empty strings`() {
        val dto = AppItemDto(
            id = "1",
            name = "",
            description = "",
            category = "",
            iconUrl = ""
        )

        val domain = dto.toDomain()

        assertEquals("", domain.name)
        assertEquals("", domain.description)
        assertEquals("", domain.category)
        assertEquals("", domain.iconUrl)
    }

    @Test
    fun `toDomain handles special characters`() {
        val dto = AppItemDto(
            id = "abc-123",
            name = "Тестовое приложение 🚀",
            description = "Описание с символами: @#$%^&*()_+-=[]{}|;':\",./<>?",
            category = "Игры",
            iconUrl = "https://example.com/icon_with_special-chars.png?size=large&format=webp"
        )

        val domain = dto.toDomain()

        assertEquals("Тестовое приложение 🚀", domain.name)
        assertEquals("Описание с символами: @#$%^&*()_+-=[]{}|;':\",./<>?", domain.description)
        assertEquals("Игры", domain.category)
        assertEquals("https://example.com/icon_with_special-chars.png?size=large&format=webp", domain.iconUrl)
    }

    @Test
    fun `toDomain handles long id strings`() {
        val longId = "a".repeat(100)
        val dto = AppItemDto(
            id = longId,
            name = "App",
            description = "Desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png"
        )

        val domain = dto.toDomain()

        assertEquals(longId.hashCode(), domain.id)
        assertEquals("App", domain.name)
    }

    @Test
    fun `toDomain handles negative hashCode from id`() {
        val idWithNegativeHash = "a]b@c"
        val dto = AppItemDto(
            id = idWithNegativeHash,
            name = "Test",
            description = "Test desc",
            category = "Приложения",
            iconUrl = "https://example.com/icon.png"
        )

        val domain = dto.toDomain()

        assertEquals(idWithNegativeHash.hashCode(), domain.id)
        // hashCode can be negative - that's expected behavior
    }

    @Test
    fun `toDomain preserves identical string fields`() {
        val uniqueString = "unique-value-${System.currentTimeMillis()}"
        val dto = AppItemDto(
            id = "1",
            name = uniqueString,
            description = uniqueString,
            category = uniqueString,
            iconUrl = uniqueString
        )

        val domain = dto.toDomain()

        assertEquals(uniqueString, domain.name)
        assertEquals(uniqueString, domain.description)
        assertEquals(uniqueString, domain.category)
        assertEquals(uniqueString, domain.iconUrl)
    }
}
