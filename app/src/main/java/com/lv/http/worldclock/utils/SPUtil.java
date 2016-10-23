package com.lv.http.worldclock.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;
import java.util.Set;

public class SPUtil {
	private static SharedPreferences getSharedPreferences(Context context){
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp;
	}

	public static Set<String> getStringSet(Context context,String key){
		SharedPreferences sp = getSharedPreferences(context);
		Set<String> result = sp.getStringSet(key, new HashSet<String>());
		return result;
	}

	public static void put(Context context,String key,Object value){
		SharedPreferences sp = getSharedPreferences(context);
		Editor editor = sp.edit();
		if(value instanceof Set){
			editor.putStringSet(key, (Set<String>) value);
		}
		editor.commit();
	}
	
}
