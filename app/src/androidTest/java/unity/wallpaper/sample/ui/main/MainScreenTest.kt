package unity.wallpaper.sample.ui.main

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import unity.wallpaper.sample.R
import org.junit.Rule
import org.junit.Test

/** UI tests for [unity.wallpaper.sample.ui.main.MainScreen]. */
class MainScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun setWallpaperButton_exists() {
    composeTestRule.setContent { MainScreen(onSetWallpaper = {}) }
    val label = composeTestRule.activity.getString(R.string.button_set_wallpaper)
    composeTestRule.onNodeWithText(label).assertExists()
  }
}
