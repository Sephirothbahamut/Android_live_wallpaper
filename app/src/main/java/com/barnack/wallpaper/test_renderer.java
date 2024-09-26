package com.barnack.wallpaper;

import android.opengl.GLES32;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class test_renderer implements GLSurfaceView.Renderer
	{
	@Override
	public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
		{

		}

	@Override
	public void onSurfaceChanged(GL10 gl10, int width, int height)
		{
		GLES32.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);
		}

	@Override
	public void onDrawFrame(GL10 gl10)
		{
		GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT);
		}
	}
