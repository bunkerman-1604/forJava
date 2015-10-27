package com.example.wodezoon;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import forAPP.SC;
import forAPP.WWG;
import forAPP.bag;

public class PP extends Activity {
	EditText	etem;
	Button		but1,but2,WTD,save;
	TextView	txt1;
	String		fileName = "PPTEMP.txt";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pp);
		etem	= (EditText)findViewById(R.id.threenums);
		but1	= (Button)	findViewById(R.id.threeNum);
		but2	= (Button)	findViewById(R.id.Random);
		WTD		= (Button)	findViewById(R.id.WTD);
		save	= (Button)	findViewById(R.id.save);
		txt1	= (TextView)findViewById(R.id.ppres);
//		getParamValue();//获得其他页面的返回值
//		putParamValue();//向其他页面发送传递值
		but1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String	TT = etem.getText().toString();
				Pattern p1 = Pattern.compile("#|-");
				Matcher m1 = p1.matcher(TT);
				if(m1.find() == true){
					String[]	tmp = TT.split("\\D");
					if(tmp.length == 3){
						int[]	num		= new int[tmp.length];
						for(int i = 0;i < tmp.length;i++){
							num[i] = Integer.parseInt(tmp[i]);
						}
						txt1.setText(new bag(new WWG(num[0],num[1],num[2])).getExpression());
					}else if(tmp.length > 3 && tmp.length < 9){
						int[]	num1	= new int[2];
						int[]	num2	= new int[tmp.length-2];
						num1[0] = Integer.parseInt(tmp[0]);
						num1[1] = Integer.parseInt(tmp[1]);
						for(int i = 0;i < num2.length ;i++){
							num2[i] = Integer.parseInt(tmp[i+2]);
						}
						txt1.setTag(new bag(new WWG(num1[0],num1[1],num2)).getExpression());
						Front();
					}else{
						TakeInfo();
					}
				}else{
					String[] 	tmp		= bag.TakeTwenty(TT);
					int			Head	= 0;
					int			Foot	= 0;
					for(int i = 0;i < tmp.length;i++){
						if(i < tmp.length/2){
							Head = Head + Integer.parseInt(tmp[i],16);
						}else{
							Foot = Foot + Integer.parseInt(tmp[i],16);
						}
					}
					txt1.setText(new bag(new WWG(Head, Foot, (Head%10+Foot%10))).getExpression());
				}
			}
        });
		but2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String bb = new Date().getTime()+"";
				int[] param = new int[3];
				param[0] = Integer.parseInt(bb.substring(8,10));
				param[1] = Integer.parseInt(bb.substring(10,12));
				param[2] = Integer.parseInt(bb.substring(12,13));
				txt1.setText(new bag(new WWG(param[0],param[1],param[2])).getExpression());
				Front();
			}
        });
		WTD.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				int[]	div = {2,4};//count of date and count of 'year/month/day/hour'(3 or 4)
				SC[]	date = new SC[3];
				int[][]	tmp  = new int[6][2*div[1]];
				date[0] = new SC("1989-4-15-9");
				date[1] = new SC(BaseInfo.sdf.format(new java.util.Date()));
				for (int i = 0;i < div[0];i++){
					tmp[2*i]   = date[i].getTG();
					tmp[2*i+1] = date[i].getDZ();
				}
				for (int i = 0;i < div[1];i++){
					tmp[4][i] = tmp[0][i];
					tmp[5][i] = tmp[1][i];
					tmp[4][div[1] + i] = tmp[2][i];
					tmp[5][div[1] + i] = tmp[3][i];
				}
				date[2] = new SC(tmp[4],tmp[5]);
				txt1.setText(date[2].DayYun()+date[2].WXN());
				Front();
			}
        });
		save.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Writable w1 = new Writable(getApplicationContext());
				try {
					w1.createSDFile("WTD.txt");
				} catch (IOException e) {}
				w1.writeSDFile(txt1.getText().toString(), "WTD.txt");
			}
        });
	}
	@SuppressWarnings("unused")
	private void putParamValue() {//设置关闭时，传送参数——"uName";
		Intent resultIntent = new Intent();  
		resultIntent.putExtra("uName", "legend2");  
		setResult(1, resultIntent); 
	}
	@SuppressWarnings("unused")
	private void getParamValue() {//获得父页面传来的参数——"canshu";
		Intent i = getIntent(); 
		String uName = i.getStringExtra("canshu");  
		txt1.setText(uName);
	}
	protected void Front() {
		txt1.setTextSize(13);
		txt1.setTypeface(Typeface.SANS_SERIF);
	}
	protected void TakeInfo() {
		Toast.makeText(this, "Number-Type is Error !", Toast.LENGTH_SHORT).show();
	}
}
