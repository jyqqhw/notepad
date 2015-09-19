package com.az.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class firstLoverWishAdd extends Activity implements OnClickListener {

	private EditText first_lover_wish_add_edit;
	private Button first_lover_wish_add_send,first_lover_wish_add_cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFinishOnTouchOutside(false);  //设置触摸activity界外是否finish掉；API level >=11
		setContentView(R.layout.first_lover_wish_add);

		first_lover_wish_add_edit = (EditText) findViewById(R.id.first_lover_wish_add_edit);
		first_lover_wish_add_send = (Button) findViewById(R.id.first_lover_wish_add_send);
		first_lover_wish_add_cancel = (Button) findViewById(R.id.first_lover_wish_add_cancel);
		first_lover_wish_add_send.setOnClickListener(this);
		first_lover_wish_add_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.first_lover_wish_add_cancel:
			setResult(RESULT_CANCELED);
			finish();
			break;
		case R.id.first_lover_wish_add_send:
			String str = first_lover_wish_add_edit.getText().toString();
			if(!str.isEmpty()){
				Intent data = new Intent();
				data.putExtra("wish", str);
				setResult(RESULT_OK, data);
				finish();
			}else{
				Toast.makeText(firstLoverWishAdd.this, "愿望不能为空", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}


}
