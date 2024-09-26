package com.barnack.wallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class activity_settings extends AppCompatActivity
	{
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		if (savedInstanceState == null)
			{
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.settings, new SettingsFragment())
					.commit();
			}
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null)
			{
			actionBar.setDisplayHomeAsUpEnabled(true);
			}

		ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>()
			{
			@Override
			public void onActivityResult(Uri uri)
				{
				// Handle the returned Uri
				try
					{
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity_settings.this);
					SharedPreferences.Editor editor = preferences.edit();
					editor.putString("custom_shader_uri", uri.toString());
					editor.apply();
					}
				catch (Exception e)
					{
					Log.e("Settings shader picker", "invalid file", e);
					}
				}
			});

		Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener()
			{
			@Override
			public void onClick(View view)
				{
				mGetContent.launch("text/plain");
				}
			});
		}

	public static class SettingsFragment extends PreferenceFragmentCompat
		{
		@Override
		public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
			{
			setPreferencesFromResource(R.xml.root_preferences, rootKey);
			}
		}

	}