package com.az.motepad.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotePadUtils {
	public static String getTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Date curDate = new Date();
		String str = format.format(curDate);
		return str;
	}
}
