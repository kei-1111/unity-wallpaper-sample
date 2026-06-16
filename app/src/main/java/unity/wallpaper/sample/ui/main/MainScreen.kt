package unity.wallpaper.sample.ui.main

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import unity.wallpaper.sample.R
import unity.wallpaper.sample.theme.UnityWallpaperSampleTheme
import unity.wallpaper.sample.unity.UnityWallpaperService

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  MainScreen(onSetWallpaper = { openLiveWallpaperPreview(context) }, modifier = modifier)
}

@Composable
internal fun MainScreen(onSetWallpaper: () -> Unit, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Button(onClick = onSetWallpaper) {
      Text(text = stringResource(R.string.button_set_wallpaper))
    }
  }
}

/**
 * Open the system live-wallpaper preview for [UnityWallpaperService] so the user can apply it with
 * a single tap. Falls back to the generic live-wallpaper chooser if the direct preview is
 * unavailable.
 */
private fun openLiveWallpaperPreview(context: Context) {
  val component = ComponentName(context, UnityWallpaperService::class.java)
  val previewIntent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
    putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, component)
  }
  if (previewIntent.resolveActivity(context.packageManager) != null) {
    context.startActivity(previewIntent)
  } else {
    context.startActivity(Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER))
  }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
  UnityWallpaperSampleTheme { MainScreen(onSetWallpaper = {}) }
}
