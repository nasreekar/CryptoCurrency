package com.abhijith.cryptocurrency

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun app_launches_and_click_on_insert_data_to_show_snackbar() {
        composeTestRule.waitForIdle()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Insert Data").assertIsDisplayed()

        composeTestRule.onNodeWithText("Insert Data").performClick()

        composeTestRule
            .waitUntil(timeoutMillis = 2000) {
                composeTestRule
                    .onAllNodesWithTag("crypto_snackbar")
                    .fetchSemanticsNodes().isNotEmpty()
            }

        composeTestRule.onAllNodesWithTag("crypto_snackbar_dismiss").onFirst().performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("demo_screen_container").assertIsDisplayed()
    }

    @Test
    fun click_on_delete_data_to_show_snackbar() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Delete Data").assertIsDisplayed().performClick()

        composeTestRule
            .waitUntil(timeoutMillis = 2000) {
                composeTestRule
                    .onAllNodesWithTag("crypto_snackbar")
                    .fetchSemanticsNodes().isNotEmpty()
            }

        composeTestRule.onAllNodesWithTag("crypto_snackbar_dismiss").onFirst().performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("demo_screen_container").assertIsDisplayed()
    }

    @Test
    fun click_on_show_crypto_currencies_to_navigate_with_crypto_key() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Show Crypto Currencies").assertIsDisplayed().performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("currency_list_screen_container_Crypto")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("currency_list_screen_container_Crypto")
            .assertIsDisplayed()
    }

    @Test
    fun click_on_show_fiat_currencies_to_navigate_with_fiat_key() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Show Fiat Currencies").assertIsDisplayed().performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("currency_list_screen_container_Fiat")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("currency_list_screen_container_Fiat")
            .assertIsDisplayed()
    }

    @Test
    fun click_on_show_all_currencies_to_navigate_with_all_key() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Show All Currencies").assertIsDisplayed().performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("currency_list_screen_container_All")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("currency_list_screen_container_All")
            .assertIsDisplayed()
    }
}