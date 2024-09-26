package com.barnack.utils.opengl.shader;

import android.content.res.AssetManager;
import android.opengl.GLES32;
import android.util.Log;

public class Fragment extends Shader
	{
	private Fragment(int handle) { super(handle); }

	public static class create
		{
		private create() {}

		public static Fragment from_code(String code)
			{
			try
				{
				return new Fragment(Shader.handle_from_code(GLES32.GL_FRAGMENT_SHADER, code));
				}
			catch (Exception e)
				{
				Log.e("Fragment shader", "from_code: ", e);
				return null;
				}
			}
		public static Fragment from_asset(AssetManager asset_manager, String filename)
			{
			try
				{
				return new Fragment(Shader.handle_from_asset(GLES32.GL_FRAGMENT_SHADER, asset_manager, filename));
				}
			catch (Exception e)
				{
				Log.e("Fragment shader", "from_asset: ", e);
				return null;
				}
			}
		}
	}
