package com.example.wodezoon;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BaseInfo extends Activity {
	Button		but2,but3,but4,but5;
	TextView	text1;
	@SuppressLint("SimpleDateFormat")
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-H");
	@Override
	//Graghic 类划线函数！！！
	protected void  onActivityResult(int requestCode, int resultCode, Intent data){//获取子页面返回值
		text1 = (TextView)findViewById(R.id.ppres);
		String temp = data.getStringExtra("uName");
		text1.setText(temp);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);
        but2  = (Button)findViewById(R.id.PP);
        but3  = (Button)findViewById(R.id.SC);
        but4  = (Button)findViewById(R.id.CountDays);
        but5  = (Button)findViewById(R.id.DG);
        but2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				TiaoZhuan(PP.class); 
//				TiaoZhuan(PP.class,"canshu","gogogo");
//				TiaoZhuan(PP.class,"canshu","comehere",1);
			}
        });
        but3.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				TiaoZhuan(SZ.class);
			}
        });
        but4.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				TiaoZhuan(openCountDays.class);
			}
        });
        but5.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				TiaoZhuan(PlayOpenGL.class);
			}
        });
	}

	@SuppressWarnings("rawtypes")
	private void TiaoZhuan(Class Target) {
		// TODO Auto-generated method stub
		Intent tent = new Intent(this,Target);
		startActivity(tent);
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private void TiaoZhuan(Class Target, String paramName, String value) {//跳转传参
		// TODO Auto-generated method stub
		Intent tent = new Intent(this,Target);
		tent.putExtra(paramName, value);
		startActivity(tent);
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private void TiaoZhuan(Class Target, String paramName, String value, int type) {//跳转传参并获得返回值
		// TODO Auto-generated method stub
		Intent tent = new Intent(this,Target);
		tent.putExtra(paramName, value);
		startActivityForResult(tent,type);
	}
    
}
