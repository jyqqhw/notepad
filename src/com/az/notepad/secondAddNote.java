package com.az.notepad;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.az.motepad.domain.DataBase;
import com.az.motepad.domain.NotePadUtils;

public class secondAddNote extends Activity implements OnClickListener {

	private SQLiteDatabase dbWriter;
	private DataBase dataBase;
	private TextView right_complete,left_return,show_time;
	private EditText edit_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_add_note);
		dataBase = new DataBase(this);
		dbWriter = dataBase.getWritableDatabase();
		right_complete = (TextView) findViewById(R.id.edit_right_complete);
		left_return = (TextView) findViewById(R.id.edit_left_return);
		show_time = (TextView) findViewById(R.id.edit_show_time);
		edit_content = (EditText) findViewById(R.id.edit_content);
		right_complete.setOnClickListener(this);
		left_return.setOnClickListener(this);
		show_time.setText(NotePadUtils.getTime());
	}

	public void onClick(View view){
		switch (view.getId()) {
		case R.id.edit_left_return:
			finish();
			break;
		case R.id.edit_right_complete:
			String str = edit_content.getText().toString();
			if(!str.isEmpty()){
				addDB();
			}

			finish();
			break;
		}
	}

	public void addDB(){
		ContentValues cv = new ContentValues();
		cv.put(DataBase.CONTENT, edit_content.getText().toString());
		cv.put(DataBase.TIME, NotePadUtils.getTime());
		cv.put(DataBase.PICTURE, "aa");
		cv.put(DataBase.VIDEO, "bb");
		dbWriter.insert(DataBase.TABLE_NAME, null, cv);
	}



}
