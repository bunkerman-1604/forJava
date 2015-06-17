package com.HW;

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

public class SZ extends Activity {
	Button		scBytime,HB,save;
	RadioGroup	sex;
	RadioButton	Nan,Nv;
	EditText	datatime1,tg,dz;
	TextView	ppres;
	int			NN = 1;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sc);
		this.scBytime	= (Button)findViewById(R.id.scbytime);
		this.HB			= (Button)findViewById(R.id.scbytgdz);
		this.save		= (Button)findViewById(R.id.save);
		this.datatime1	= (EditText)findViewById(R.id.datetime1);
		this.tg			= (EditText)findViewById(R.id.tg);
		this.dz			= (EditText)findViewById(R.id.dz);
		this.ppres		= (TextView)findViewById(R.id.ppres);
		this.sex		= (RadioGroup)findViewById(R.id.RadioGroup01);
		this.Nan		= (RadioButton)findViewById(R.id.SexNan);
		this.Nv			= (RadioButton)findViewById(R.id.SexNv);
		scBytime.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				SC tmp1	= new SC(datatime1.getText().toString().replaceAll("\\D", "-")); 
				ppres.setText(MainActivity.DayYun(tmp1)+MainActivity.WXN(tmp1)+MainActivity.DYun(tmp1, NN));
				Front();
			}
        });
		
		HB.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String[] tmp1 = tg.getText().toString().split("\\D");
				String[] tmp2 = dz.getText().toString().split("\\D");
				if(tmp1.length == tmp2.length && tmp1.length > 3){
					int[] TG = new int[tmp1.length];
					int[] DZ = new int[tmp1.length];
					for(int i = 0;i < tmp1.length;i++){
						TG[i] = Integer.parseInt(tmp1[i]);
						DZ[i] = Integer.parseInt(tmp2[i]);
					}
					SC temp = new SC(TG,DZ);
					ppres.setText(MainActivity.DayYun(temp)+MainActivity.WXN(temp)+MainActivity.DYun(temp, NN));
					Front();
				}
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
					NN = 1;
				}else{
					NN = 0;
				}
			}
        });
	}
	protected void Front() {
		ppres.setTextSize(13);
		ppres.setTypeface(Typeface.SANS_SERIF);
	}
}
