package com.example.wodezoon;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import forAPP.SC;
import forAPP.bag;
import forAPP.countTime;

public class SZ extends Activity {
	Button		scBytime,HB,save;
	RadioGroup	sex;
	RadioButton	Nan,Nv;
	EditText[]	et = new EditText[3];
	TextView	ppres;
	boolean		NN = true;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sc);
		this.scBytime	= (Button)findViewById(R.id.scbytime);
		this.HB			= (Button)findViewById(R.id.scbytgdz);
		this.save		= (Button)findViewById(R.id.save);
		this.et[0]		= (EditText)findViewById(R.id.datetime1);
		this.et[1]		= (EditText)findViewById(R.id.startDate);
		this.et[2]		= (EditText)findViewById(R.id.round);
		this.ppres		= (TextView)findViewById(R.id.ppres);
		this.sex		= (RadioGroup)findViewById(R.id.RadioGroup01);
		this.Nan		= (RadioButton)findViewById(R.id.SexNan);
		this.Nv			= (RadioButton)findViewById(R.id.SexNv);
		scBytime.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				SC tmp1	= new SC(et[0].getText().toString().replaceAll("\\D", "-")); 
				ppres.setText(new countTime(et[0].getText().toString()).getYL()+"\r\n"+tmp1.DayYun()+tmp1.WXN()+"大运情况:\r\n"+bag.DYun(tmp1, NN));
				Front();
			}
        });
		
		HB.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				ppres.setText("大运流年:\r\n"+bag.LNian(et[0].getText().toString(), et[1].getText().toString(), et[2].getText().toString(), NN));
				Front();
			}
        });
		
		save.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Writable w1 = new Writable(getApplicationContext());
				try {
					w1.createSDFile("SCBZ.txt");
				} catch (IOException e) {}
				w1.writeSDFile(ppres.getText().toString(), "SCBZ.txt");
			}
        });
		
		sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				if(arg1 == Nan.getId()){
					NN = true;
				}else{
					NN = false;
				}
			}
        });
	}
	protected void Front() {
		ppres.setTextSize(13);
		ppres.setTypeface(Typeface.SANS_SERIF);
	}
}