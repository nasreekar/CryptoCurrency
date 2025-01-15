package com.abhijith.cryptocurrency

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhijith.cryptocurrency.ui.common.ErrorScreen
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListScreen
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListState
import com.abhijith.cryptocurrency.ui.screens.currencyList.components.CurrencyListContent
import com.abhijith.domain.model.Currency
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyListScreenTest {

    private val testCryptoCurrencies = listOf(
        Currency(id = "ETH", name = "Ethereum", symbol = "ETH"),
        Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
        Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
        Currency(id = "ETN", name = "ETN Coin", symbol = "ETN")
    )

    private val testFiatCurrencies = listOf(
        Currency(id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD"),
        Currency(id = "USD", name = "United States Dollar", symbol = "$", code = "USD"),
        Currency(id = "INR", name = "Indian Rupee", symbol = "₹", code = "INR"),
        Currency(id = "GBP", name = "British Pound", symbol = "£", code = "GBP")
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var uiState: CurrencyListState

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testLoadingState() {
        composeTestRule.setContent {
            CurrencyListScreen(
                type = CurrencyType.CRYPTO
            )

            uiState = CurrencyListState.Loading
        }

        composeTestRule
            .onNodeWithTag("loading_screen")
            .assertIsDisplayed()
    }

    @Test
    fun testEmptyState() {
        composeTestRule.setContent {
            CurrencyListScreen(
                type = CurrencyType.CRYPTO
            )
            uiState = CurrencyListState.Empty

            ErrorScreen(
                imgRes = android.R.drawable.ic_menu_search,
                message = stringResource(id = R.string.no_records_in_db)
            )
        }

        composeTestRule
            .onNodeWithTag("error_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Looks like there is no data in the database. Try inserting data first and try again!!!")
            .assertIsDisplayed()
    }

    @Test
    fun testErrorState() {

        composeTestRule.setContent {
            CurrencyListScreen(
                type = CurrencyType.CRYPTO
            )

            uiState = CurrencyListState.Error(
                messageResId = R.string.loading_currency_list_error,
                formatArgs = listOf("Error message")
            )
            ErrorScreen(
                imgRes = android.R.drawable.ic_menu_search,
                message = stringResource(id = R.string.loading_currency_list_error, "Error message")
            )
        }

        composeTestRule
            .onNodeWithTag("error_screen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Failed to load currency list: Error message")
            .assertIsDisplayed()
    }

    @Test
    fun testSuccessState() {
        composeTestRule.setContent {
            CurrencyListScreen(
                type = CurrencyType.CRYPTO
            )

            uiState = CurrencyListState.Success
            CurrencyListContent(currencies = testCryptoCurrencies)
        }

        // Verify the list content is displayed
        composeTestRule
            .onNodeWithTag("currency_list_content")
            .assertIsDisplayed()

        // Verify search bar is displayed
        composeTestRule
            .onNodeWithTag("currency_search_bar")
            .assertIsDisplayed()

        // Verify currency items are displayed
        composeTestRule
            .onNodeWithText("Ethereum")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Ethereum Classic")
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionalityWithCryptoCurrencyList() {
        composeTestRule.setContent {
            CurrencyListContent(currencies = testCryptoCurrencies)
        }

        composeTestRule
            .onNodeWithTag("currency_search_bar")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_data")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_text_field")
            .performTextInput("Bitcoin")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("currency_list_item_BTC", useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionalityWithFiatCurrencyList() {
        composeTestRule.setContent {
            CurrencyListContent(currencies = testFiatCurrencies)
        }

        composeTestRule
            .onNodeWithTag("currency_search_bar")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_data")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_text_field")
            .performTextInput("d")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("currency_list_item_SGD", useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_list_item_USD", useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionalityWithAllCurrencyList() {
        composeTestRule.setContent {
            CurrencyListContent(currencies = testFiatCurrencies + testCryptoCurrencies)
        }

        composeTestRule
            .onNodeWithTag("currency_search_bar")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_data")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_text_field")
            .performTextInput("b")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("currency_list_item_BTC", useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_list_item_GBP", useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionalityWithNoResult() {
        composeTestRule.setContent {
            CurrencyListContent(currencies = testFiatCurrencies + testCryptoCurrencies)
        }

        composeTestRule
            .onNodeWithTag("currency_search_bar")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("currency_data")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_text_field")
            .performTextInput("foo")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("error_screen").assertIsDisplayed()

        composeTestRule
            .onNodeWithText("No Results Found!!!")
            .assertIsDisplayed()
    }

    @Test
    fun testSearchClearFunctionality() {
        composeTestRule.setContent {
            CurrencyListContent(currencies = testCryptoCurrencies)
        }

        composeTestRule
            .onNodeWithTag("search_text_field")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_text_field")
            .performTextInput("Bitcoin")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("clear_button")
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("currency_data")
            .assertExists()
            .assertIsDisplayed()
    }
}