package com.az.notepad;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.az.motepad.domain.DataBase;

public class second extends Activity implements OnClickListener {
	private DataBase dataBase;
	private SQLiteDatabase dbReader;
	private Cursor cursor;
	private TextView to_edit;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		lv = (ListView) findViewById(R.id.second_listview);
		to_edit = (TextView) findViewById(R.id.to_edit);
		to_edit.setOnClickListener(this);

		dataBase = new DataBase(this);
		dbReader = dataBase.getReadableDatabase();

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, secondAddNote.class);
		startActivity(intent);

	}

	@Override
	protected void onResume() {
		super.onResume();
		cursor = dbReader.query(DataBase.TABLE_NAME, null, null, null, null, null, DataBase.TIME + " desc");
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.second_list_style, cursor, 
				new String[]{DataBase.TIME,DataBase.CONTENT}, new int[]{R.id.second_list_style_time,R.id.second_list_style_content});
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				cursor.moveToPosition(position);
				Intent intent = new Intent(second.this, secondShowDetail.class);
				intent.putExtra(DataBase.ID, cursor.getInt(cursor.getColumnIndex(DataBase.ID))); //删除数据需要
				intent.putExtra(DataBase.TIME, cursor.getString(cursor.getColumnIndex(DataBase.TIME)));
				intent.putExtra(DataBase.CONTENT, cursor.getString(cursor.getColumnIndex(DataBase.CONTENT)));
				startActivity(intent);
			}

		});
	}


}
