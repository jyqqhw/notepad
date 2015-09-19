package com.az.notepad;

import com.az.motepad.domain.DataBase;
import com.az.motepad.domain.NotePadUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class secondShowDetail extends Activity implements OnClickListener {
	private TextView second_detail_return, second_detail_time , second_detail_edit ,
	      second_detail_delete, second_detail_content_show,second_detail_complete;
	private EditText second_detail_content;
	private ScrollView second_scrollview1 , second_scrollview2;
	private SQLiteDatabase dbWriter;
	private DataBase database;
	private Intent intent;
	private int ID = 0;
	private String CONTENT = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_list_detail);
		
		database = new DataBase(this);
		dbWriter = database.getWritableDatabase();
		
		second_scrollview1 = (ScrollView) findViewById(R.id.second_scrollview1);
		second_scrollview2 = (ScrollView) findViewById(R.id.second_scrollview2);
		second_detail_complete = (TextView) findViewById(R.id.second_detail_complete);
		second_detail_return = (TextView) findViewById(R.id.second_detail_return);
		second_detail_time = (TextView) findViewById(R.id.second_detail_time);
		second_detail_edit = (TextView) findViewById(R.id.second_detail_edit);
		second_detail_delete = (TextView) findViewById(R.id.second_detail_delete);
		second_detail_content = (EditText) findViewById(R.id.second_detail_content);
		second_detail_content_show = (TextView) findViewById(R.id.second_detail_content_show);
		second_detail_complete.setOnClickListener(this);
		second_detail_return.setOnClickListener(this);
		second_detail_edit.setOnClickListener(this);
		second_detail_delete.setOnClickListener(this);

		intent = getIntent();
		ID = intent.getIntExtra(database.ID, 0);
		CONTENT = intent.getStringExtra(DataBase.CONTENT);
		second_detail_time.setText(intent.getStringExtra(DataBase.TIME));
		second_detail_content.setText(CONTENT);
		second_detail_content_show.setText(CONTENT);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.second_detail_return:
			finish();
			break;
		case R.id.second_detail_edit:
			second_scrollview1.setVisibility(View.GONE);
			second_scrollview2.setVisibility(View.VISIBLE);
			second_detail_complete.setVisibility(View.VISIBLE);
			break;
		case R.id.second_detail_delete:
			AlertDialog.Builder dialog = new Builder(this);
			dialog.setMessage("确定要删除吗？")
			.setPositiveButton("确定", new myListener())
			.setNegativeButton("取消", new myListener())
			.create().show();
			break;
		case R.id.second_detail_complete:
			String str1 = second_detail_content.getText().toString();
			ContentValues cv = new ContentValues();
			cv.put(DataBase.CONTENT, str1);
			cv.put(DataBase.TIME, NotePadUtils.getTime());
			if(!str1.isEmpty()&&!CONTENT.equals(str1)){
				dbWriter.update(DataBase.TABLE_NAME, cv, DataBase.ID + "=?", new String[]{String.valueOf(ID)});
				second_detail_content_show.setText(str1);
			}
			
			second_scrollview1.setVisibility(View.VISIBLE);
			second_scrollview2.setVisibility(View.GONE);
			second_detail_complete.setVisibility(View.GONE);
			break;
		}
	}

	public class myListener implements android.content.DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				dbWriter.delete(DataBase.TABLE_NAME, DataBase.ID + "=?", new String[]{String.valueOf(ID)});
				finish();
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				break;
			}
		}
	}

	
}
