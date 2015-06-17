package com.HW;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class openCountDays extends MainActivity {
	EditText	D1,D2;
	Button		count;
	TextView	countRes;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdays);
		D1 = (EditText) findViewById(R.id.date1);
		D2 = (EditText) findViewById(R.id.date2);
		count =(Button)	findViewById(R.id.Count);
		countRes=(TextView)findViewById(R.id.countdaysResult);
		Toast.makeText(this, "WELCOME", Toast.LENGTH_LONG).show();
		count.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String date1 = D1.getText().toString().replaceAll("\\D", "-");
				String date2 = D2.getText().toString();
				countRes.setText(new countTime(date1,date2).getDiff()+"");
			}
        });
	}
	

}
