package com.barnack.utils.opengl.shader;

import android.content.res.AssetManager;
import android.opengl.GLES32;
import android.util.Log;

public class Vertex extends Shader
	{
	private Vertex(int handle) { super(handle); }

	public static class create
		{
		private create() {}

		public static Vertex from_code(String code)
			{
			try
				{
				return new Vertex(Shader.handle_from_code(GLES32.GL_VERTEX_SHADER, code));
				}
			catch (Exception e)
				{
				Log.e("Vertex shader", "from_code: ", e);
				return null;
				}
			}
		public static Vertex from_asset(AssetManager asset_manager, String filename)
			{
			try
				{
				return new Vertex(Shader.handle_from_asset(GLES32.GL_VERTEX_SHADER, asset_manager, filename));
				}
			catch (Exception e)
				{
				Log.e("Vertex shader", "from_asset: ", e);
				return null;
				}
			}
		}
	}
