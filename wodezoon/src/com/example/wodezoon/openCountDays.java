package com.example.wodezoon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import forAPP.countTime;

public class openCountDays extends Activity {
	EditText	D1,D2;
	Button		count,getDate;
	TextView	countRes;
	Pattern		p1 = Pattern.compile("^(-{0,1})\\d{1,}\\Z");//从开始到结尾都是数字
	Pattern		p2 = Pattern.compile("\\d{4}(-\\d{1,2}){2,3}");//匹配日期
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdays);
		D1	= (EditText) findViewById(R.id.date1);
		D2	= (EditText) findViewById(R.id.date2);
		count	= (Button)	findViewById(R.id.Count);
		getDate	= (Button)	findViewById(R.id.getDate);
		countRes=(TextView)findViewById(R.id.countdaysResult);
		Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
		count.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String date1 = D1.getText().toString().replaceAll("\\D", "-");
				String date2 = D2.getText().toString().replaceAll("\\D", "-");
				countRes.setText(new countTime(date1,date2).getDiff()+"");
			}
        });
		getDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String tmp1,tmp2;
				Matcher m1 = p1.matcher(D1.getText().toString().replaceAll("\\D", "-"));
				Matcher m2 = p1.matcher(D2.getText().toString().replaceAll("\\D", "-"));
				Matcher m3 = p2.matcher(D1.getText().toString().replaceAll("\\D", "-"));
				Matcher m4 = p2.matcher(D2.getText().toString().replaceAll("\\D", "-"));
				boolean[]	type = {m1.find(),m2.find(),m3.find(),m4.find()};
				if ((type[0]||type[1])&&(type[2]||type[3])){
					if (type[0]){
						tmp1 = m1.group();
					}else{
						tmp1 = m2.group();
					}
					if (type[2]){
						tmp2 = m3.group();
					}else{
						tmp2 = m4.group();
					}
					countRes.setText(new countTime(tmp2).getDate(Integer.parseInt(tmp1)));
				}
			}
		});
	}
	

}
