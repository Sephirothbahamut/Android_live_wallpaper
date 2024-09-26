package com.barnack.wallpaper;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;

import com.barnack.utils.opengl.wallpaper_service_opengles_3;

public class service extends wallpaper_service_opengles_3
	{
	@Override
	protected GLSurfaceView.Renderer getNewRenderer()
		{
		final AssetManager   assets_manager = getAssets();
		final shaders_getter shaders_getter = new shaders_getter(assets_manager, this);
		final renderer renderer = new renderer(shaders_getter);
		return renderer;
		}
	}