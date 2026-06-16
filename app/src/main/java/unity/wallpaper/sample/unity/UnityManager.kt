package unity.wallpaper.sample.unity

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Surface
import com.unity3d.player.UnityPlayerForActivityOrService

/**
 * Minimal singleton that owns a single [UnityPlayerForActivityOrService] and renders it onto the
 * live wallpaper's [Surface].
 *
 * This project only uses Unity as a wallpaper (the Activity is just a button), so unlike the
 * withmo project there is no Activity/Wallpaper surface coordination here — the wallpaper is the
 * only consumer of the Unity player.
 */
object UnityManager {
    private const val MAIN_DISPLAY_INDEX = 0

    private var player: UnityPlayerForActivityOrService? = null
    private val mainHandler = Handler(Looper.getMainLooper())

    @Synchronized
    fun init(context: Context) {
        if (player == null) {
            player = UnityPlayerForActivityOrService(context.applicationContext)
        }
    }

    /** Attach Unity's renderer to the given wallpaper surface. */
    @Synchronized
    fun attach(surface: Surface) {
        player?.displayChanged(MAIN_DISPLAY_INDEX, surface)
    }

    /** Detach Unity from the current surface before it is destroyed (avoids a render-thread crash). */
    @Synchronized
    fun detach() {
        player?.displayChanged(MAIN_DISPLAY_INDEX, null)
    }

    fun resume() = mainHandler.post {
        @Suppress("DEPRECATION")
        player?.resume()
    }

    fun pause() = mainHandler.post {
        @Suppress("DEPRECATION")
        player?.pause()
    }

    fun focusGained(gained: Boolean) = mainHandler.post {
        player?.windowFocusChanged(gained)
    }
}
