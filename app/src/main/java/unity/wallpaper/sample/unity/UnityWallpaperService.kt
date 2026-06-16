package unity.wallpaper.sample.unity

import android.service.wallpaper.WallpaperService
import android.view.Surface
import android.view.SurfaceHolder

/**
 * Live wallpaper that renders the bundled Unity scene onto the home/lock screen background.
 */
class UnityWallpaperService : WallpaperService() {

    override fun onCreateEngine(): Engine = UnityEngine()

    private inner class UnityEngine : Engine() {

        override fun onCreate(holder: SurfaceHolder) {
            super.onCreate(holder)
            UnityManager.init(applicationContext)
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)
            startUnity(holder.surface)
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            stopUnity()
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            if (visible) {
                startUnity(surfaceHolder.surface)
            } else {
                UnityManager.focusGained(false)
                UnityManager.pause()
            }
        }

        private fun startUnity(surface: Surface) {
            UnityManager.attach(surface)
            UnityManager.resume()
            UnityManager.focusGained(true)
        }

        private fun stopUnity() {
            UnityManager.focusGained(false)
            UnityManager.pause()
            UnityManager.detach()
        }
    }
}
