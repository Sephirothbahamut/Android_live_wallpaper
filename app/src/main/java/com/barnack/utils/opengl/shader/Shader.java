package com.barnack.utils.opengl.shader;

import android.content.res.AssetManager;
import android.opengl.GLES32;

import com.barnack.utils.Utils;

import java.io.InputStream;

public class Shader
	{
	protected Shader(int handle) { this.handle = handle; }

	private int handle;
	public int get_handle() { return handle; }

	public void clear()
		{
		if (handle != 0) { GLES32.glDeleteShader(handle); }
		}

	protected static int handle_from_code(int type, String code) throws Exception
		{
		final int handle = GLES32.glCreateShader(type);
		GLES32.glShaderSource(handle, code);
		GLES32.glCompileShader(handle);

		// Get the compilation status.
		final int[] compileStatus = new int[1];
		GLES32.glGetShaderiv(handle, GLES32.GL_COMPILE_STATUS, compileStatus, 0);

		// If the compilation failed, delete the shader.
		if (compileStatus[0] == 0)
			{
			Exception e = new Exception("Failed to compile shader.\n" + GLES32.glGetShaderInfoLog(handle));
			GLES32.glDeleteShader(handle);
			throw e;
			}

		return handle;
		}

	protected static int handle_from_input_stream(int type, InputStream input_stream) throws Exception
		{
		String code = Utils.read_input_stream(input_stream);
		return handle_from_code(type, code);
		}

	protected static int handle_from_asset(int type, AssetManager asset_manager, String filename) throws Exception
		{
		InputStream input_stream = asset_manager.open(filename);
		return handle_from_input_stream(type, input_stream);
		}

	public boolean is_valid() { return handle != 0; }
	}
