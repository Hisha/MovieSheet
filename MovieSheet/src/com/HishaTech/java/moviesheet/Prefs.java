package com.HishaTech.java.moviesheet;

import java.util.prefs.Preferences;

public class Prefs {

	private Preferences Pref = Preferences.userRoot().node(
			this.getClass().getName());

	public Prefs() {
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		return Pref.getBoolean(key, defaultValue);
	}

	public String getByteArray(String key, String defaultValue) {
		return Pref.get(key, defaultValue);
	}

	public double getDouble(String key, double defaultValue) {
		return Pref.getDouble(key, defaultValue);
	}

	public float getFloat(String key, float defaultValue) {
		return Pref.getFloat(key, defaultValue);
	}

	public int getInt(String key, int defaultValue) {
		return Pref.getInt(key, defaultValue);
	}

	public long getLong(String key, long defaultValue) {
		return Pref.getLong(key, defaultValue);
	}

	public String getString(String key, String defaultValue) {
		return Pref.get(key, defaultValue);
	}

	public void saveBoolean(String key, Boolean value) {
		Pref.putBoolean(key, value);
	}

	public void saveByteArray(String key, byte[] value) {
		Pref.putByteArray(key, value);
	}

	public void saveDouble(String key, double value) {
		Pref.putDouble(key, value);
	}

	public void saveFloat(String key, float value) {
		Pref.putFloat(key, value);
	}

	public void saveInt(String key, int value) {
		Pref.putInt(key, value);
	}

	public void saveLong(String key, long value) {
		Pref.putLong(key, value);
	}

	public void saveString(String key, String value) {
		Pref.put(key, value);
	}

}