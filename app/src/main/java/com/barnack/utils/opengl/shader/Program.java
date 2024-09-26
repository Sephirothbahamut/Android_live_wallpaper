package com.barnack.utils.opengl.shader;

import android.opengl.GLES32;
import android.util.Log;

import com.barnack.utils.opengl.Utils;

public class Program
	{
	private int handle;

	public int get_handle() { return handle; }

	private Program(Vertex vertex, Fragment fragment) throws Exception
		{
		if(vertex == null || fragment == null || !vertex.is_valid() || !fragment.is_valid())
			{
			throw new Exception("Attempting to construct a shader program from invalid shaders.");
			}

		handle = GLES32.glCreateProgram();
		GLES32.glAttachShader(handle, vertex  .get_handle());
		GLES32.glAttachShader(handle, fragment.get_handle());
		GLES32.glLinkProgram (handle);

		int[] linkStatus = new int[1];
		GLES32.glGetProgramiv(handle, GLES32.GL_LINK_STATUS, linkStatus, 0);
		if (linkStatus[0] != GLES32.GL_TRUE)
			{
			Exception e = new Exception("Failed to link shader program.\n" + GLES32.glGetProgramInfoLog(handle));
			GLES32.glDeleteProgram(handle);
			throw e;
			}

		GLES32.glUseProgram(handle);

		try
			{
			Utils.throw_on_error();
			}
		catch(Exception e)
			{
			GLES32.glDeleteProgram(handle);
			throw e;
			}
		}

	public void clear()
		{
		if (handle != 0) { GLES32.glDeleteProgram(handle); }
		}

	public boolean is_valid() { return handle != 0; }



	public static class create
		{
		private create() { }

		public static Program from_shaders(Vertex vertex, Fragment fragment)
			{
			try
				{
				return new Program(vertex, fragment);
				}
			catch (Exception e)
				{
				Log.e("Shader program", "from_shaders: ", e);
				return null;
				}
			}
		}
	}
