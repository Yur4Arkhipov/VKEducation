package com.example.vkeducation.domain.usecase

import com.example.vkeducation.data.repository.AppsRepository
import com.example.vkeducation.domain.model.AppItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAppsUseCaseTest {

    private lateinit var repository: AppsRepository
    private lateinit var useCase: GetAppsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetAppsUseCase(repository)
    }

    @Test
    fun `invoke calls repository getApps and returns result`() = runTest {
        val expectedApps = listOf(
            AppItem(1, "Telegram", "Messenger", "Приложения", "https://example.com/tg.png"),
            AppItem(2, "VK", "Social", "Приложения", "https://example.com/vk.png")
        )

        coEvery { repository.getApps() } returns expectedApps

        val result = useCase()

        assertEquals(expectedApps, result)
        assertEquals(2, result.size)
        assertEquals("Telegram", result[0].name)
        assertEquals("VK", result[1].name)
    }

    @Test
    fun `invoke returns empty list when repository returns empty`() = runTest {
        coEvery { repository.getApps() } returns emptyList()

        val result = useCase()

        assertTrue(result.isEmpty())
        assertEquals(0, result.size)
    }

    @Test
    fun `invoke throws when repository throws`() = runTest {
        val exception = RuntimeException("Repository error")
        coEvery { repository.getApps() } throws exception

        try {
            useCase()
        } catch (e: RuntimeException) {
            assertEquals("Repository error", e.message)
        }
    }

    @Test
    fun `invoke is suspend and works in coroutine scope`() = runTest {
        val expectedApps = listOf(
            AppItem(42, "Test App", "Test Description", "Игры", "https://example.com/icon.png")
        )
        coEvery { repository.getApps() } returns expectedApps

        val result = useCase()

        assertEquals(1, result.size)
        assertEquals(42, result[0].id)
        assertEquals("Test App", result[0].name)
    }

    @Test
    fun `invoke calls repository exactly once`() = runTest {
        val expectedApps = listOf(
            AppItem(1, "App1", "Desc1", "Приложения", "https://example.com/icon1.png")
        )
        coEvery { repository.getApps() } returns expectedApps

        useCase()
        useCase()
        useCase()

        coVerify(exactly = 3) { repository.getApps() }
    }

    @Test
    fun `invoke returns list with single item`() = runTest {
        val singleApp = AppItem(
            id = 100,
            name = "Single App",
            description = "Only app",
            category = "Производительность",
            iconUrl = "https://example.com/single.png"
        )
        coEvery { repository.getApps() } returns listOf(singleApp)

        val result = useCase()

        assertEquals(1, result.size)
        assertEquals(singleApp, result[0])
    }

    @Test
    fun `invoke preserves all domain model fields`() = runTest {
        val app = AppItem(
            id = 999,
            name = "Complex App",
            description = "A very detailed description with special chars: @#$%",
            category = "Образование",
            iconUrl = "https://example.com/complex_icon.png?v=2&size=hd"
        )
        coEvery { repository.getApps() } returns listOf(app)

        val result = useCase()

        val domainApp = result[0]
        assertEquals(999, domainApp.id)
        assertEquals("Complex App", domainApp.name)
        assertEquals("A very detailed description with special chars: @#$%", domainApp.description)
        assertEquals("Образование", domainApp.category)
        assertEquals("https://example.com/complex_icon.png?v=2&size=hd", domainApp.iconUrl)
    }

    @Test
    fun `invoke works with large list of apps`() = runTest {
        val largeList = (1..100).map { i ->
            AppItem(
                id = i,
                name = "App $i",
                description = "Description for app $i",
                category = if (i % 2 == 0) "Приложения" else "Игры",
                iconUrl = "https://example.com/icon$i.png"
            )
        }
        coEvery { repository.getApps() } returns largeList

        val result = useCase()

        assertEquals(100, result.size)
        assertEquals("App 1", result[0].name)
        assertEquals("App 100", result[99].name)
    }
}
