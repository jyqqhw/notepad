package com.az.notepad;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.az.motepad.domain.DataBase;
import com.az.motepad.domain.NotePadUtils;

public class firstLoverWish extends Activity implements OnClickListener {

	private TextView first_lover_wish_add;
	private ListView first_lover_wish_listview;
	private SQLiteDatabase dbWriter;
	private SQLiteDatabase dbReader;
	private DataBase dataBase;
	private Cursor cursor;
	private CheckBox first_lover_wish_liststyle_choose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_lover_wish);

		dataBase = new DataBase(this);
		dbWriter = dataBase.getWritableDatabase();
		dbReader = dataBase.getReadableDatabase();

		first_lover_wish_liststyle_choose = (CheckBox) findViewById(R.id.first_lover_wish_liststyle_choose);
		first_lover_wish_add = (TextView) findViewById(R.id.first_lover_wish_add);
		first_lover_wish_listview = (ListView) findViewById(R.id.first_lover_wish_listview);
		first_lover_wish_add.setOnClickListener(this);
		//first_lover_wish_liststyle_choose.setOnCheckedChangeListener(new myCheckListener());
	}
	@Override
	protected void onResume() {
		super.onResume();
		cursor = dbReader.query(DataBase.TABLE_NAME_LOVE, null, null, null, null, null, 
				DataBase.TIME_LOVE + " desc");     //查询数据库
		//创建一个光标适配器
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.first_lover_wish_liststyle,
				cursor, new String[]{DataBase.TIME_LOVE,DataBase.CONTENT_LOVE},
				new int[]{R.id.first_lover_wish_liststyle_time,R.id.first_lover_wish_liststyle_content});
		first_lover_wish_listview.setAdapter(adapter);   //列表设置适配器
		first_lover_wish_listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				cursor.moveToPosition(position);
				int delete_id = cursor.getInt(cursor.getColumnIndex(DataBase.ID_LOVE));
			Intent intent = new Intent(firstLoverWish.this, firstLoverWishLongClick.class);
			intent.putExtra("delete_id", delete_id);
			startActivity(intent);
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, firstLoverWishAdd.class);
		startActivityForResult(intent, 1);
	}

	//选择项改变监听事件
	public class myCheckListener implements OnCheckedChangeListener{
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_CANCELED:
			break;
		case RESULT_OK:
			String wish = data.getStringExtra("wish");
			ContentValues cv = new ContentValues();
			cv.put(DataBase.TIME_LOVE, NotePadUtils.getTime());
			cv.put(DataBase.CONTENT_LOVE, wish);
			dbWriter.insert(DataBase.TABLE_NAME_LOVE, null, cv);
			break;
		}
		
	}
	
	
	

}
