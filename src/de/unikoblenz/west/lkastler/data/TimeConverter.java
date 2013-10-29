package de.unikoblenz.west.lkastler.data;

import java.util.Date;

import android.text.format.DateFormat;

abstract public class TimeConverter {

	public static String timeLongToString(long time) {
		
		return DateFormat.format("dd.MM.yy hh:mm", new Date(time)).toString();
	}
}
