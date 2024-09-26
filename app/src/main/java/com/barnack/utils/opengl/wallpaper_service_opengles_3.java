package com.barnack.utils.opengl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.SurfaceHolder;

import com.learnopengles.android.livewallpaper.GLWallpaperService;

public abstract class wallpaper_service_opengles_3 extends GLWallpaperService
	{
	@Override
	public Engine onCreateEngine()
		{
		return new engine_opengles_3();
		}

	class engine_opengles_3 extends GLEngine
		{
		@Override
		public void onCreate(SurfaceHolder surfaceHolder)
			{
			super.onCreate(surfaceHolder);

			// Check if the system supports OpenGL ES 3.2.
			final ActivityManager   activityManager   = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
			final boolean           supportsEs3       = configurationInfo.reqGlEsVersion >= 0x30000;

			if (!supportsEs3)
				{
				Log.e("Test activity", "OpenGL ES 3.0 support is required.");
				}

			setEGLContextClientVersion(3);
			setPreserveEGLContextOnPause(true);
			setRenderer(getNewRenderer());
			}
		}

	protected abstract Renderer getNewRenderer();
	}