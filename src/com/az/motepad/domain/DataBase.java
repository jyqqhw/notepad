package com.az.motepad.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper{


	//日记本数据库
	public static final String TABLE_NAME = "notes";
	public static final String CONTENT = "content";
	public static final String PICTURE = "picture";
	public static final String VIDEO = "video";
	public static final String ID = "_id";
	public static final String TIME = "time";
	//情侣愿望数据库
	public static final String TABLE_NAME_LOVE = "wishes";
	public static final String CONTENT_LOVE= "content_love";
	public static final String ID_LOVE = "_id";
	public static final String TIME_LOVE = "time_love";


	public DataBase(Context context){
		super(context, "database", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//日记本数据库
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PICTURE + " TEXT NOT NULL," + VIDEO + " TEXT NOT NULL," 
				+ CONTENT + " TEXT NOT NULL," + TIME  + " TEXT NOT NULL)");
		//情侣愿望数据库
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_LOVE + "("
				+ ID_LOVE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ CONTENT_LOVE + " TEXT NOT NULL," + TIME_LOVE  + " TEXT NOT NULL)");

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


}
