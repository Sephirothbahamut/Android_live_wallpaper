package com.barnack.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils
	{
	public static String read_input_stream(InputStream input_stream) throws IOException
		{
		//https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[]                buffer = new byte[1024];
		for (int length; (length = input_stream.read(buffer)) != -1; )
			{
			result.write(buffer, 0, length);
			}

		// StandardCharsets.UTF_8.name() > JDK 7
		return result.toString("UTF-8");
		}

	public static String read_input_stream_nothrow(InputStream input_stream)
		{
		try
			{
			return read_input_stream(input_stream);
			}
		catch (Exception e)
			{
			Log.e("Utils.read_input_stream", "exception", e);
			return "";
			}
		}
	}
