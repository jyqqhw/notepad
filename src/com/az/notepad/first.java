package com.az.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class first extends Activity{

	private LinearLayout first_lover_wish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);

		first_lover_wish = (LinearLayout) findViewById(R.id.first_lover_wish);

	}

	public void love(View v) {
		Intent intent = new Intent(this,firstLoverWish.class);
		startActivity(intent);
	}


}
