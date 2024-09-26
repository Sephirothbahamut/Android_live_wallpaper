package com.barnack.wallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import androidx.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.Optional;

public class shaders_getter
	{
	String           shader_code_vertex     = "";
	String           shader_code_fragment   = "";
	Optional<String> shader_code_custom_opt = Optional.empty();

	shaders_getter(@NotNull AssetManager assets_manager, @NotNull Context context)
		{
		try
			{
			InputStream shader_istream_vertex   = assets_manager.open("shaders/triangle.vert");
			InputStream shader_istream_fragment = assets_manager.open("shaders/shader.frag"  );
			shader_code_vertex   = com.barnack.utils.Utils.read_input_stream(shader_istream_vertex  );
			shader_code_fragment = com.barnack.utils.Utils.read_input_stream(shader_istream_fragment);
			}
		catch(Exception e)
			{
			Log.wtf("Test activity", "Failed to load default shaders code.", e);
			}

		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		boolean           use_default = sharedPrefs.getBoolean("use_default", true);
		if(!use_default)
			{
			try
				{
				String      custom_shader_uri_string = sharedPrefs.getString("custom_shader_uri", "qwe");
				Uri         shader_uri_custom        = Uri.parse(custom_shader_uri_string);
				InputStream shader_istream_custom    = context.getContentResolver().openInputStream(shader_uri_custom);
				String      shader_code_custom       = com.barnack.utils.Utils.read_input_stream(shader_istream_custom);
				shader_code_custom_opt = Optional.of(shader_code_custom);
				}
			catch(Exception e)
				{
				Log.e("Test activity", "Failed to load custom fragment shader code, rollback to default.", e);
				}
			}
		}
	}
