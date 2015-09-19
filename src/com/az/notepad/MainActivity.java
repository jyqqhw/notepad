package com.az.notepad;

import com.az.motepad.domain.DataBase;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends ActivityGroup {

	private TabHost tabhost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new DataBase(this);
		tabhost = (TabHost) findViewById(R.id.tabhost);
		TabWidget tabWidget = tabhost.getTabWidget();
		tabhost.setup(getLocalActivityManager());
		LayoutInflater inflater = getLayoutInflater().from(this);
		
		View home = inflater.inflate(R.layout.tab_view, null);
		home.setBackgroundResource(R.drawable.home);
		View diary = inflater.inflate(R.layout.tab_view, null);
		diary.setBackgroundResource(R.drawable.diary);
		View remember = inflater.inflate(R.layout.tab_view, null);
		remember.setBackgroundResource(R.drawable.remenber);
		View setting = inflater.inflate(R.layout.tab_view, null);
		setting.setBackgroundResource(R.drawable.setting);
		
		tabhost.addTab(tabhost.newTabSpec("first").setIndicator(home).setContent(new Intent(this, first.class)));
		tabhost.addTab(tabhost.newTabSpec("second").setIndicator(diary).setContent(new Intent(this, second.class)));
		tabhost.addTab(tabhost.newTabSpec("third").setIndicator(remember).setContent(new Intent(this, third.class)));
		tabhost.addTab(tabhost.newTabSpec("four").setIndicator(setting).setContent(new Intent(this, four.class)));
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
