package com.barnack.wallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.opengl.GLES32;
import android.opengl.GLSurfaceView;
import android.os.SystemClock;
import android.util.Log;

import com.barnack.utils.opengl.Utils;
import com.barnack.utils.opengl.shader.Fragment;
import com.barnack.utils.opengl.shader.Program;
import com.barnack.utils.opengl.shader.Vertex;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.Optional;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class renderer implements GLSurfaceView.Renderer
	{
	Program              shader_program;
	final shaders_getter shaders_getter;

	int uniform_handle_time       ;
	int uniform_handle_resolution ;
	int uniform_handle_orientation;

	renderer(shaders_getter shaders_getter)
		{
		this.shader_program = null;
		this.shaders_getter = shaders_getter;
		}

	private boolean recreate_program()
		{
		final Vertex   vertex   = Vertex.create.from_code(shaders_getter.shader_code_vertex);
		Fragment fragment = null;

		if(shaders_getter.shader_code_custom_opt.isPresent())
			{
			fragment = Fragment.create.from_code(shaders_getter.shader_code_custom_opt.get());
			}
		if(fragment == null)
			{
			fragment = Fragment.create.from_code(shaders_getter.shader_code_fragment);
			}

		shader_program    = Program .create.from_shaders(vertex, fragment);

		if(shader_program == null) { return false; }

		Utils.validate();
		uniform_handle_time        = GLES32.glGetUniformLocation(shader_program.get_handle(), "time"       );
		uniform_handle_resolution  = GLES32.glGetUniformLocation(shader_program.get_handle(), "resolution" );
		uniform_handle_orientation = GLES32.glGetUniformLocation(shader_program.get_handle(), "orientation");

		return true;
		}

	private boolean check_program()
		{
		if(shader_program == null)
			{
			return recreate_program();
			}
		return true;
		}

	@Override
	public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
		{
		check_program();
		}

	@Override
	public void onSurfaceChanged(GL10 gl10, int width, int height)
		{
		GLES32.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);

		if(!check_program()) { return; }
		GLES32.glUseProgram(shader_program.get_handle());
		GLES32.glUniform2f(uniform_handle_resolution, (float)width, (float)height);
		}

	@Override
	public void onDrawFrame(GL10 gl10)
		{
		GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT);

		if(shader_program == null) { return; }

		GLES32.glUseProgram(shader_program.get_handle());

		final float time = (SystemClock.currentThreadTimeMillis() / 1000.f);
		GLES32.glUniform1f(uniform_handle_time, time);
		//Log.d("Time: ", String.format("%.3f", time));

		GLES32.glDrawArrays(GLES32.GL_TRIANGLES, 0, 3);
		}
	}
