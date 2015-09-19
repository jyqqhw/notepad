package com.az.notepad;

import com.az.motepad.domain.DataBase;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class firstLoverWishLongClick extends Activity implements OnClickListener {
	private TextView lover_wish_list_delete;
	private DataBase database;
	private SQLiteDatabase dbWriter;
	private int delete_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFinishOnTouchOutside(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_lover_wish_list_longclick);
		
		Intent intent = getIntent();
		delete_id = intent.getIntExtra("delete_id", -1);
		
		database = new DataBase(this);
		dbWriter = database.getWritableDatabase();
		
		lover_wish_list_delete = (TextView) findViewById(R.id.lover_wish_list_delete);
		lover_wish_list_delete.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		dbWriter.delete(DataBase.TABLE_NAME_LOVE, DataBase.ID_LOVE+"=?", 
				new String[]{ String.valueOf(delete_id) });
		finish();
	}
	
	
}
