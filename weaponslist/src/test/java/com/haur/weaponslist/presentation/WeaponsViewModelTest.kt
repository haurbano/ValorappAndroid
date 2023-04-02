package com.haur.weaponslist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haur.weaponslist.domain.models.WeaponPreview
import com.haur.weaponslist.domain.repositories.WeaponsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class WeaponsViewModelTest {

    private val weaponsRepository = Mockito.mock(WeaponsRepository::class.java)
    private lateinit var weaponsViewModel: WeaponsViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        weaponsViewModel = WeaponsViewModel(weaponsRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When fetchWeapons is called, the live data should update its value`() = testDispatcher.runBlockingTest {
        // Given
        Mockito.`when`(weaponsRepository.getWeaponList()).thenReturn(buildWeaponsPreviewTestList())

        // When
        weaponsViewModel.fetchWeapons()

        // Then
        Assert.assertEquals(false, weaponsViewModel.uiState.value?.isLoading)
        Assert.assertEquals(buildWeaponsPreviewTestList(), weaponsViewModel.uiState.value?.weapons)
    }

    private fun buildWeaponsPreviewTestList(): List<WeaponPreview> {
        val weaponsPreviewList = ArrayList<WeaponPreview>()
        (0..4).forEach {
            weaponsPreviewList.add(
                WeaponPreview(
                    uuid = "uuid-$it",
                    name= "name-$it",
                    image= "url-$it",
                    fireRate= it,
                    category = "category=$it"
                )
            )
        }
        return weaponsPreviewList
    }
}