package com.barnack.wallpaper;

import android.app.ActivityManager;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class test_activity extends AppCompatActivity
	{
	private GLSurfaceView opengl_surface;
	private GLSurfaceView.Renderer renderer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager   activityManager   = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean           supportsEs3       = configurationInfo.reqGlEsVersion >= 0x30000;

		if (!supportsEs3)
			{
			Log.e("Test activity", "OpenGL ES 3.0 support is required.");
			}

		AssetManager assets_manager = getAssets();
		shaders_getter shaders_getter = new shaders_getter(assets_manager, this);
		renderer = new renderer(shaders_getter);

		opengl_surface = findViewById(R.id.surface_view);
		opengl_surface.setPreserveEGLContextOnPause(true);
		opengl_surface.setEGLContextClientVersion(3);
		opengl_surface.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		opengl_surface.setRenderer(renderer);
		opengl_surface.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

		Button btn_set_wallpaper = findViewById(R.id.btn_set_wallpaper);
		btn_set_wallpaper.setOnClickListener(view ->
			{
			//if(Build.VERSION.SDK_INT >= 16)
			//	{
			//	Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
			//	intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, service.class));
			//	startActivity(intent);
			//	}
			//else
				{
				Intent intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
				intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
				startActivity(intent);
				}
			});
		}
	}