package com.barnack.utils.opengl;

import android.opengl.GLES32;
import android.util.Log;

public class Utils
	{
	public static void throw_on_error() throws Exception
		{
		StringBuilder string_builder = new StringBuilder();

		int opengl_error = GLES32.glGetError();
		while(opengl_error != GLES32.GL_NO_ERROR)
			{
			string_builder.append(opengl_error);
			string_builder.append('\n');
			opengl_error = GLES32.glGetError();
			}

		if(string_builder.length() > 0)
			{
			throw new Exception(string_builder.toString());
			}
		}

	public static boolean validate()
		{
		try
			{
			throw_on_error();
			return true;
			}
		catch (Exception e)
			{
			Log.e("opengl.Utils.validate", "exception", e);
			return false;
			}
		}
	}
