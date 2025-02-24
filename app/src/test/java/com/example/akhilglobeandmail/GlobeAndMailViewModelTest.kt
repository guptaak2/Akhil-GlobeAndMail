package com.example.akhilglobeandmail

import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostInfo
import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostsInfo
import com.example.akhilglobeandmail.domain.model.GlobeAndMailResponse
import com.example.akhilglobeandmail.domain.repository.GlobeAndMailRepository
import com.example.akhilglobeandmail.presentation.viewmodel.GlobeAndMailViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GlobeAndMailViewModelTest {

    private lateinit var sut: GlobeAndMailViewModel

    private val mockRepository = mockk<GlobeAndMailRepository>(relaxed = true)
    private val mockDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(mockDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    private fun createSut() {
        sut = GlobeAndMailViewModel(mockRepository)
    }

    @Test
    fun `test posts list set when api returns posts`() = runTest {
        coEvery { mockRepository.fetchPosts() } returns GlobeAndMailResponse.Success(
            GlobeAndMailPostsInfo(
                posts = buildList {
                    repeat(10) {
                        add(GlobeAndMailPostInfo("image", "title", "body", authors = "authorsList", true))
                    }
                }
            )
        )
        createSut()
        coVerify(exactly = 1) {
            mockRepository.fetchPosts()
        }
        Assert.assertEquals(10, sut.globeAndMailPosts.value.postsList.size)
        Assert.assertTrue(sut.globeAndMailPosts.value.errorMessage.isEmpty())
    }

    @Test
    fun `test error message set when api returns empty list of posts`() = runTest {
        coEvery { mockRepository.fetchPosts() } returns GlobeAndMailResponse.Success(
            GlobeAndMailPostsInfo(
                posts = emptyList()
            )
        )
        createSut()
        coVerify(exactly = 1) {
            mockRepository.fetchPosts()
        }
        Assert.assertEquals(0, sut.globeAndMailPosts.value.postsList.size)
        Assert.assertTrue(sut.globeAndMailPosts.value.errorMessage.isNotEmpty())
    }

    @Test
    fun `test error message set when api returns error`() = runTest {
        coEvery { mockRepository.fetchPosts() } returns GlobeAndMailResponse.Error(
            "error message!",
            Exception()
        )
        createSut()
        coVerify(exactly = 1) {
            mockRepository.fetchPosts()
        }
        Assert.assertEquals(0, sut.globeAndMailPosts.value.postsList.size)
        Assert.assertTrue(sut.globeAndMailPosts.value.errorMessage.isEmpty().not())
    }
}