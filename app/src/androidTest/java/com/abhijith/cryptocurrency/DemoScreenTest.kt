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
    fun testClickOnInsertDataToShowSnackBar() {
        composeTestRule.waitForIdle()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.insert_data))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.insert_data))
            .performClick()

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
    fun testClickOnDeleteDataToShowSnackBar() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.delete_data))
            .assertIsDisplayed().performClick()

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
    fun testClickOnShowCryptoToNavigateToNextScreenWithCryptoKey() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.show_crypto))
            .assertIsDisplayed().performClick()

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
    fun testClickOnShowFiatToNavigateToNextScreenWithFiatKey() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.show_fiat))
            .assertIsDisplayed().performClick()

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
    fun testClickOnShowAllToNavigateToNextScreenWithAllKey() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("demo_screen_container")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("demo_screen_container")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.show_all))
            .assertIsDisplayed().performClick()

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