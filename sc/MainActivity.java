package com.HW;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setBackgroundDrawableResource(R.drawable.pictur_mainbackground);
		setContentView(R.layout.activity_main);
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
		Intent tent = new Intent(MainActivity.this,Target);
		startActivity(tent);
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private void TiaoZhuan(Class Target, String paramName, String value) {//跳转传参
		// TODO Auto-generated method stub
		Intent tent = new Intent(MainActivity.this,Target);
		tent.putExtra(paramName, value);
		startActivity(tent);
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private void TiaoZhuan(Class Target, String paramName, String value, int type) {//跳转传参并获得返回值
		// TODO Auto-generated method stub
		Intent tent = new Intent(MainActivity.this,Target);
		tent.putExtra(paramName, value);
		startActivityForResult(tent,type);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static String PrintWWG(WWG w1) {
		String logtime = sdf.format(new java.util.Date());
		String pres =  "记录时间:" + logtime + "\r\n";
		SC tmp1 = new SC(logtime);
		int[] tg = new int[6];
		int[] dz = new int[6];
		int WG,NG,SHY;
		NaJia tem1;
		Gong tem2;
		pres = pres + w1.Print();
		String[] bb = new WX(w1).getLQ();
		pres = pres + bb[0]+" "+bb[2]+" "+bb[4]+" "+bb[6]+" "+bb[8]+" \r\n";
		pres = pres + bb[1]+" "+bb[3]+" "+bb[5]+" "+bb[7]+" "+bb[9]+" \r\n";
		for(int j = 0;j < 2;j++){
			if(j == 0){
				NG = w1.getBG(1).getBGName();
				WG = w1.getBG(0).getBGName();
			}else{
				NG = w1.getBG(5).getBGName();
				WG = w1.getBG(4).getBGName();
			}
			tem1 = new NaJia(WG,NG);
			tem2 = new Gong(WG,NG);
			pres = pres + "Class:"+tem2.getGClass()+"\r\n";
			SHY	 = tem2.getGS();
			if(SHY == 7){
				SHY = 4;
			}
			if(SHY == 0){
				SHY = 3;
			}
			tg[0] = tg[1] = tg[2] = tem1.getTG()[0];
			tg[3] = tg[4] = tg[5] = tem1.getTG()[1];
			dz = tem1.getDZ();
			SC tmp = new SC(tg,dz);
			String[] res = tmp.getBZ();
			for(int i = 0;i < res.length;i++){
				if(SHY == i+1){
					pres = pres + res[i]+"OO";
				}else{
					pres = pres + res[i]+"--";
				}
			}
			pres = pres + "\r\n";
		}
		pres = pres + DayYun(tmp1) + WXN(tmp1);
		return pres;
	}
	
	public static SC HB(String dat1,String dat2,int n){
		SC res;
		int[] tg;
		int[] dz;
		if(n == 0){
			tg = new int[8];
			dz = new int[8];
			SC tmp1 = new SC(dat1);
			SC tmp2 = new SC(dat2);
			for(int i = 0;i < 4;i++){
				tg[i] = tmp1.getTG()[i];
				tg[i+4] = tmp2.getTG()[i];
				dz[i] = tmp1.getDZ()[i];
				dz[i+4] = tmp2.getDZ()[i];
			}
		}else{
			tg = new int[7];
			dz = new int[7];
			SC tmp1 = new SC(dat1);
			SC tmp2 = new SC(dat2);
			for(int i = 0;i < 4;i++){
				tg[i] = tmp1.getTG()[i];
				dz[i] = tmp1.getDZ()[i];
				if(i < 3){
					tg[i+4] = tmp2.getTG()[i];
					dz[i+4] = tmp2.getDZ()[i];
				}
			}
		}
		res = new SC(tg,dz);
		return res;
	}
	public static String DayYun(SC tmp1) {
		String[] temp0 = tmp1.getBZ();
		String[] temp1 = tmp1.tgch();
		String[] temp2 = tmp1.dzch();
		String[] temp3 = tmp1.dzcg().split("-");
		String	 res = "天干地支:\r\n";
		for(int i = 0;i < temp0.length;i++){
			if(i == 4){
				res = res + "\r\n";
			}
			res = res + temp0[i].replaceAll(" ", "") +"  ";
		}
		res = res+"\r\n天干冲合:\r\n";
		for(int i = 0;i <temp1.length;i++){
			if(temp1[i].length() > 3){
				res = res + temp1[i] +"  \r\n";
			}
		}
		res = res+"地支冲合:\r\n";
		for(int i = 0;i < temp2.length;i++){
			if(temp2[i].length() > 5){
				res = res + temp2[i] +"  \r\n";
			}
		}
		res = res+"地支藏干:\r\n";
		if(temp3[0] != null){
			res = res + "正气:"+temp3[0]+"\r\n";
		}
		if(temp3[1] != null){
			res = res + "中气:"+temp3[1]+"\r\n";
		}
		if(temp3[2] != null){
			res = res + "余气:"+temp3[2]+"\r\n";
		}
		return res;
	} 
    public static String WXN(SC s1) {
		WX wx = new WX(s1);
		String res = "十神状况:\r\n";
		String[] res1 = wx.getRes();
		String[] res2 = wx.getLQ();
		for(int j = 0;j < 2;j++){
			for(int i = res2.length-1;i > -1;i--){
				if((i+j)%2 == 0){
					res = res + res2[i];
				}
			}
			res = res + "\r\n";
		}
		res = res +"五行状况:\r\n";
		for(int j = 0;j < 2;j++){
			for(int i = res1.length-1;i > -1;i--){
				if((i+j)%2 == 0){
					res = res + res1[i];
				}
			}
			res = res + "\r\n";
		}
		res = res + "五行数量:\r\n";
		res = res + wx.printWX();
		return res;
	}
	
    public static String DYun(SC s1,int sex) {
    	//TODO
    	String pres = "大运流年情况:\r\n";
		int[] tgdz = (int[]) s1.DYLN(sex%2).get(0);
		String[] res = (String[]) s1.DYLN(sex%2).get(1);
		int[] stg= s1.getTG();
		int[] sdz= s1.getDZ();
		int[] TG = new int[5];
		int[] DZ = new int[5];
		for(int i = 0;i < 4;i++){
			TG[i] = stg[i];
			DZ[i] = sdz[i];
		}
		for(int j = 0;j < res.length;j++){
			TG[4] = tgdz[2*j];
			DZ[4] = tgdz[2*j+1];
			SC sres = new SC(TG,DZ);
			pres = pres + (j+1)*10+"---------"+res[j]+":\r\n";
			pres = pres + DayYun(sres);
			pres = pres + WXN(sres);
		}
		return pres;
	}
   static public String[] TakeTwenty(String tar) {
		String[] res = new String[tar.getBytes().length];
		byte[] tmp	= tar.getBytes();
		for(int i = 0;i < tmp.length;i++){
			String temp = Integer.toHexString(tmp[i]);
			if(temp.length() > 2){
				res[i] =temp.substring(temp.length()-2,temp.length());
			}else{
				res[i] =temp;
			}
		}
		return res;
	}
}
class Gong{
	private short GClass,GS;//0-归；7-游；6-本
	public Gong(int b){
		int[] e = null ;
		for(int j = 0;j < 7;j++){
			System.out.println(" "+(j+1));
			if(j == 6){
				e = new int[1];
			}else{
				e = new int[j+1];
			}
			for(int i = 0;i < j+1;i++){
				if(i == 0 && j == 6){
					e[i] = 5;
					break;
				}else if(i == 5){
					e[i] = i-1;
				}else{
					e[i] = i+1;
				}
			}
			WWG w1 = new WWG(b,b,e);
			for(int k = 0;k < 6;k++){
				w1.DY(w1.getBG(4+k/3), k);
				System.out.println();
			}
		}
		this.GClass = (short) b;
		this.GS = -1;
	}
	public Gong(int a,int b){//a代表上,b代表下
		if(a%8 == 0){
			if(b%8 == 0){
				this.GClass = 8;
				this.GS = 6;
			}else if(b%8 == 1){
				this.GClass = 8;
				this.GS = 3;
			}else if(b%8 == 2){
				this.GClass = 8;
				this.GS = 2;
			}else if(b%8 == 3){
				this.GClass = 6;
				this.GS = 7;
			}else if(b%8 == 4){
				this.GClass = 8;
				this.GS = 1;
			}else if(b%8 == 5){
				this.GClass = 4;
				this.GS = 4;
			}else if(b%8 == 6){
				this.GClass = 6;
				this.GS = 0;
			}else if(b%8 == 7){
				this.GClass = 2;
				this.GS = 5;
			}
		}else if(a%8 == 1){
			if(b%8 == 0){
				this.GClass = 1;
				this.GS = 3;
			}else if(b%8 == 1){
				this.GClass = 1;
				this.GS = 6;
			}else if(b%8 == 2){
				this.GClass = 7;
				this.GS = 5;
			}else if(b%8 == 3){
				this.GClass = 3;
				this.GS = 0;
			}else if(b%8 == 4){
				this.GClass = 5;
				this.GS = 4;
			}else if(b%8 == 5){
				this.GClass = 1;
				this.GS = 1;
			}else if(b%8 == 6){
				this.GClass = 3;
				this.GS = 7;
			}else if(b%8 == 7){
				this.GClass = 1;
				this.GS = 2;
			}
		}else if(a%8 == 2){
			if(b%8 == 0){
				this.GClass = 2;
				this.GS = 2;
			}else if(b%8 == 1){
				this.GClass = 8;
				this.GS = 5;
			}else if(b%8 == 2){
				this.GClass = 2;
				this.GS = 6;
			}else if(b%8 == 3){
				this.GClass = 6;
				this.GS = 4;
			}else if(b%8 == 4){
				this.GClass = 4;
				this.GS = 0;
			}else if(b%8 == 5){
				this.GClass = 4;
				this.GS = 7;
			}else if(b%8 == 6){
				this.GClass = 2;
				this.GS = 1;
			}else if(b%8 == 7){
				this.GClass = 2;
				this.GS = 3;
			}
		}else if(a%8 == 3){
			if(b%8 == 0){
				this.GClass = 1;
				this.GS = 7;
			}else if(b%8 == 1){
				this.GClass = 1;
				this.GS = 0;
			}else if(b%8 == 2){
				this.GClass = 7;
				this.GS = 4;
			}else if(b%8 == 3){
				this.GClass = 3;
				this.GS = 6;
			}else if(b%8 == 4){
				this.GClass = 5;
				this.GS = 5;
			}else if(b%8 == 5){
				this.GClass = 3;
				this.GS = 2;
			}else if(b%8 == 6){
				this.GClass = 3;
				this.GS = 3;
			}else if(b%8 == 7){
				this.GClass = 3;
				this.GS = 1;				
			}
		}else if(a%8 == 4){
			if(b%8 == 0){
				this.GClass = 4;
				this.GS = 1;
			}else if(b%8 == 1){
				this.GClass = 8;
				this.GS = 4;
			}else if(b%8 == 2){
				this.GClass = 2;
				this.GS = 0;
			}else if(b%8 == 3){
				this.GClass = 6;
				this.GS = 5;
			}else if(b%8 == 4){
				this.GClass = 4;
				this.GS = 6;
			}else if(b%8 == 5){
				this.GClass = 4;
				this.GS = 3;
			}else if(b%8 == 6){
				this.GClass = 4;
				this.GS = 2;
			}else if(b%8 == 7){
				this.GClass = 4;
				this.GS = 7;
			}
		}else if(a%8 == 5){
			if(b%8 == 0){
				this.GClass = 1;
				this.GS = 4;
			}else if(b%8 == 1){
				this.GClass = 5;
				this.GS = 1;
			}else if(b%8 == 2){
				this.GClass = 7;
				this.GS = 7;
			}else if(b%8 == 3){
				this.GClass = 5;
				this.GS = 2;
			}else if(b%8 == 4){
				this.GClass = 5;
				this.GS = 3;
			}else if(b%8 == 5){
				this.GClass = 5;
				this.GS = 6;
			}else if(b%8 == 6){
				this.GClass = 3;
				this.GS = 5;
			}else if(b%8 == 7){
				this.GClass = 7;
				this.GS = 0;
			}
		}else if(a%8 == 6){
			if(b%8 == 0){
				this.GClass = 8;
				this.GS = 0;
			}else if(b%8 == 1){
				this.GClass = 8;
				this.GS = 7;
			}else if(b%8 == 2){
				this.GClass = 6;
				this.GS = 1;
			}else if(b%8 == 3){
				this.GClass = 6;
				this.GS = 3;
			}else if(b%8 == 4){
				this.GClass = 6;
				this.GS = 2;
			}else if(b%8 == 5){
				this.GClass = 4;
				this.GS = 5;
			}else if(b%8 == 6){
				this.GClass = 6;
				this.GS = 6;
			}else if(b%8 == 7){
				this.GClass = 2;
				this.GS = 4;
			}
		}else if(a%8 == 7){
			if(b%8 == 0){
				this.GClass = 1;
				this.GS = 5;
			}else if(b%8 == 1){
				this.GClass = 7;
				this.GS = 2;
			}else if(b%8 == 2){
				this.GClass = 7;
				this.GS = 3;
			}else if(b%8 == 3){
				this.GClass = 7;
				this.GS = 1;
			}else if(b%8 == 4){
				this.GClass = 5;
				this.GS = 7;
			}else if(b%8 == 5){
				this.GClass = 5;
				this.GS = 0;
			}else if(b%8 == 6){
				this.GClass = 3;
				this.GS = 4;
			}else if(b%8 == 7){
				this.GClass = 7;
				this.GS = 6;
			}
		}
	}
	public short getGClass(){
		return this.GClass;
	}
	public short getGS(){
		return this.GS;
	}
}
class NaJia{
	private int[] TG = new int[2];
	private int[] DZ = new int[6];
	public NaJia(int a,int b){
		WG(a%8);//shang
		NG(b%8);//xia
	}
	private void WG(int a) {
		if(a == 0){
			TG[1] =0;
			DZ[3] =2;
			DZ[4] =0;
			DZ[5] =10;
		}else if(a == 1){
			TG[1] =9;
			DZ[3] =7;
			DZ[4] =9;
			DZ[5] =11;
		}else if(a == 2){
			TG[1] =4;
			DZ[3] =0;
			DZ[4] =10;
			DZ[5] =8;
		}else if(a == 3){
			TG[1] =6;
			DZ[3] =10;
			DZ[4] =8;
			DZ[5] =6;
		}else if(a == 4){
			TG[1] =7;
			DZ[3] =7;
			DZ[4] =9;
			DZ[5] =11;
		}else if(a == 5){
			TG[1] =8;
			DZ[3] =8;
			DZ[4] =6;
			DZ[5] =4;
		}else if(a == 6){
			TG[1] =5;
			DZ[3] =9;
			DZ[4] =11;
			DZ[5] =1;
		}else{
			TG[1] =3;
			DZ[3] =11;
			DZ[4] =1;
			DZ[5] =3;
		}
	}
	private void NG(int a) {
		if(a == 0){
			TG[0] =2;
			DZ[0] =8;
			DZ[1] =6;
			DZ[2] =4;
		}else if(a == 1){
			TG[0] =1;
			DZ[0] =1;
			DZ[1] =3;
			DZ[2] =5;
		}else if(a == 2){
			TG[0] =4;
			DZ[0] =6;
			DZ[1] =4;
			DZ[2] =2;
		}else if(a == 3){
			TG[0] =6;
			DZ[0] =4;
			DZ[1] =2;
			DZ[2] =0;
		}else if(a == 4){
			TG[0] =7;
			DZ[0] =1;
			DZ[1] =3;
			DZ[2] =5;
		}else if(a == 5){
			TG[0] =8;
			DZ[0] =2;
			DZ[1] =0;
			DZ[2] =10;
		}else if(a == 6){
			TG[0] =5;
			DZ[0] =3;
			DZ[1] =5;
			DZ[2] =7;
		}else{
			TG[0] =3;
			DZ[0] =5;
			DZ[1] =7;
			DZ[2] =9;
		}
	}
	public int[] getTG(){
		return this.TG;
	}
	public int[] getDZ(){
		return this.DZ;
	}
}
class WWG{
	private BG[] a;
	private int[] bd;
	Calendar c1 = Calendar.getInstance();
	public WWG(int a,int b,int c){
		TG(a,b);
		this.a[4] = BG(a,b,c)[0];
		this.a[5] = BG(a,b,c)[1];
		this.bd = new int[1];
		this.bd[0] = c%6;
		if(c%6 != 0){
			this.bd[0] = c%6;
		}else{
			this.bd[0] = 6;
		}
	}
	public WWG(int a,int b,int[] c){
		TG(a,b);
		this.a[4] = BG(a,b,c)[0];
		this.a[5] = BG(a,b,c)[1];
		this.bd = c;
	}
	public String DY(BG b,int i){
		return b.BIO(2-i%3);
	}
	public String Print(){
		String res = "";
		for(int i = 0;i < 6;i++){
			res = res + DY(this.a[0+i/3],i);
			res = res + DY(this.a[2+i/3],i);
			res = res + DY(this.a[4+i/3],i);
			res = res + DY(this.a[6+i/3],i);
			res = res + DY(this.a[8+i/3],i);
			res = res + "\r\n";
		}
		return res;
	}
	public void IO(String path){}
	public BG getBG(int i){
		if(i>=0 && i<10){
			return this.a[i];
		}else{
			System.out.println("getBG Is Error !");
			return null;
		}
	}
	public int[] getBD(){
		return this.bd;
	}
	private void TG(int a,int b){
		this.a = new BG[10];
		this.a[0] = new BG(a%8);
		this.a[1] = new BG(b%8);
		this.a[2] = HHG(this.a[0],this.a[1])[0];
		this.a[3] = HHG(this.a[0],this.a[1])[1];
		this.a[6]=CG(a%8);
		this.a[7]=CG(b%8);
		this.a[8]=ZG(b%8);
		this.a[9]=ZG(a%8);
	}
	private BG[] BG( int a, int b, int[] c){
		BG[] bg = new BG[2];
		bg[0] = new BG(a%8);
		bg[1] = new BG(b%8);
		for(int i = 0 ;i < c.length;i++){
			if(c[i]%6>0 && c[i]%6<4){
				bg[1].BYY(c[i]%6-1);
			}else if(c[i]%6 == 0){
				bg[0].BYY(2);
			}else{
				bg[0].BYY(c[i]%6-4);
			}
		}
		return bg;
	}
	private BG[] BG( int a, int b, int c){
		BG[] bg = new BG[2];
		bg[0] = new BG(a%8);
		bg[1] = new BG(b%8);
		if(c%6>0 && c%6<4){
			bg[1].BYY(c%6-1);
		}else if(c%6 == 0){
			bg[0].BYY(2);
		}else{
			bg[0].BYY(c%6-4);
		}
		return bg;
	}
	private BG[] HHG(BG bg1, BG bg2){
		BG[] bg = new BG[2];
		bg[0] = new BG(0);
		bg[1] = new BG(1);
		bg[0].setYY(2, bg1.getYY(1));
		bg[0].setYY(1, bg1.getYY(0));
		bg[0].setYY(0, bg2.getYY(2));
		bg[1].setYY(2, bg1.getYY(0));
		bg[1].setYY(1, bg2.getYY(2));
		bg[1].setYY(0, bg2.getYY(1));
		return bg;
	}
	private BG ZG(int i){
		BG bg;
		if(i == 2){
			bg = new BG(5);
		}else if(i == 4){
			bg = new BG(7);
		}else if(i == 5){
			bg = new BG(2);
		}else if(i == 7){
			bg = new BG(4);
		}else{
			bg = new BG(i);
		}
		return bg;
	}
	private BG CG(int i){
		BG bg;
		if(i== 0){
			bg = new BG(1);
		}else if(i == 1){
			bg = new BG(0);
		}else if(i == 2){
			bg = new BG(7);
		}else if(i == 3){
			bg = new BG(6);
		}else if(i == 4){
			bg = new BG(5);
		}else if(i == 5){
			bg = new BG(4);
		}else if(i == 6){
			bg = new BG(3);
		}else if(i == 7){
			bg = new BG(2);
		}else{
			bg = null;
			System.out.println("CG异常");
		}
		return bg;
	}
}
class WX{
	private int[] tg,dz,bd;
	private int[] bgName = new int[10];
	private double[] wx = new double[10];//JMTSH
	private String[] wxName = {"阳金","阴金","阳木","阴木","阳土","阴土","阳水","阴水","阳火","阴火"};
	private String[] lqName = {"正印","偏印","正官","七杀","正财","偏财","伤官","食神","帮比","劫财"};
//	private String[] LQ = {"父母","兄弟","子女","妻财","官鬼"};
	private String[] res;
	public WX(WWG parameter){
		this.res = new String[10];
		this.bd = parameter.getBD();
		this.tg = null;
		this.dz = null;
		for(int i = 0;i < 10;i++){
			this.bgName[i] = parameter.getBG(i).getBGName();
			this.res[i] = this.wxName[bgwx(this.bgName[i])];
			if(this.bgName[i] == 3 || this.bgName[i] == 6){
				this.wx[bgwx(this.bgName[i])+i%2]++;
			}else{
				this.wx[bgwx(this.bgName[i])]++;
			}
		}
	}
	public WX(SC param){
		this.res = new String[param.getLenth()*2];
		this.tg = param.getTG();
		this.dz = param.getDZ();
		int a = 0,b = 0;
		for(int i = 0;i < param.getLenth();i++){
			a = tgwx(this.tg[i]);
			b = dzwx(this.dz[i]);
			this.res[i*2] = wxName[a];
			if(i != 2){
				this.wx[a]++;
			}
			if(i == 1){
				this.wx[b] = this.wx[b]*1.5;
			}
			this.wx[b]++;
			this.res[i*2+1] = wxName[b];
		}
	}
	public String printWX(){
		String res = "J:"+(this.wx[0]+this.wx[1])+"\r\n";
		res = res + "M:"+(this.wx[2]+this.wx[3])+"\r\n";
		res = res + "T:"+(this.wx[4]+this.wx[5])+"\r\n";
		res = res + "S:"+(this.wx[6]+this.wx[7])+"\r\n";
		res = res + "H:"+(this.wx[8]+this.wx[9])+"\r\n";
		return res;
	}
	public String[] getRes(){
		return this.res;
	}
	public String[] getLQ(){
		String[] res;
		if(this.tg != null){
			res = new String[this.res.length];
			int[] lq = LQ(tgwx(this.tg[2]));
			int tg = 0,dz = 0;
			for(int i = 0;i < 4;i++){
				tg = this.tg[i];
				dz = this.dz[i];
				for(int j = 0 ;j < lq.length;j++){
					if(lq[j] == tgwx(tg)){
						res[2*i] = this.lqName[j];
					}
					if(lq[j] == dzwx(dz)){
						res[2*i+1] = this.lqName[j];
					}
				}
			}
			res[4] = "元神";
			for(int i = 4;i < this.tg.length;i++){
				tg = this.tg[i];
				dz = this.dz[i];
				for(int j = 0 ;j < lq.length;j++){
					if(lq[j] == tgwx(tg)){
						res[2*i] = this.lqName[j];
					}
					if(lq[j] == dzwx(dz)){
						res[2*i+1] = this.lqName[j];
					}
				}
			}
		}else{
			res = new String[10];
			int[] lq = new int[10];
			lq = LQ(bgwx(this.bgName[this.bd[0]/4]));
			for(int i = 0;i < 10;i++){
				int tmp0 = this.bgName[i],tmp1;
				if(tmp0 == 3 || tmp0 == 6){
					tmp1 = bgwx(tmp0) + i%2;
				}else{
					tmp1 = bgwx(tmp0);
				}
				for(int j = 0;j < lq.length;j++){
					if(lq[j] == tmp1){
						res[i] = this.lqName[j];
						break;
					}
				}
				if(i == (this.bd[0]-1)/3){
					res[i] = "元神";
				}
			}
		}
		return res;
	}
	private int[] LQ(int wx){//JMTSH 
		int[] lq = new int[10];
		if(wx%2 == 0){
			for(int i = 0;i < 5;i++){
				lq[2*i] = ((wx/2)*2+4*i+5)%10;
				lq[2*i+1] = ((wx/2)*2+4*i+4)%10;
			}
		}else{
			for(int i = 0;i < 5;i++){
				lq[2*i] = ((wx/2)*2+4*i+4)%10;
				lq[2*i+1] = ((wx/2)*2+4*i+5)%10;
			}
		}
		return lq;
	}
	private int bgwx(int i) {
		if(i == 1){
			return 0;
		}else if(i == 2){
			return 1;
		}else if(i == 3){
			return 8;
		}else if(i == 4){
			return 2;
		}else if(i == 5){
			return 3;
		}else if(i == 6){
			return 6;
		}else if(i == 7){
			return 4;
		}else{
			return 5;
		}
	}
	private int dzwx(int i) {//JMTSH
		if(i == 1){
			return 6;
		}else if(i == 2){
			return 5;
		}else if(i == 3){
			return 2;
		}else if(i == 4){
			return 3;
		}else if(i == 5){
			return 4;
		}else if(i == 6){
			return 9;
		}else if(i == 7){
			return 8;
		}else if(i == 8){
			return 5;
		}else if(i == 9){
			return 0;
		}else if(i == 10){
			return 1;
		}else if(i == 11){
			return 4;
		}else{
			return 7;
		}
	}
	private int tgwx(int i) {//JMTSH
		if(i == 1){
			return 2;
		}else if(i == 2){
			return 3;
		}else if(i == 3){
			return 8;
		}else if(i == 4){
			return 9;
		}else if(i == 5){
			return 4;
		}else if(i == 6){
			return 5;
		}else if(i == 7){
			return 0;
		}else if(i == 8){
			return 1;
		}else if(i == 9){
			return 6;
		}else{
			return 7;
		}
	}
}
class BG{
	private YY[] yy = new YY[3];
	private int BGName;
	public BG(int a){//下中上
		if(a%8 == 0){
			this.yy[0] = new YY(0);this.yy[1] = new YY(0);this.yy[2] = new YY(0);
		}else if(a%8 == 1){
			this.yy[0] = new YY(1);this.yy[1] = new YY(1);this.yy[2] = new YY(1);
		}else if(a%8 == 2){
			this.yy[0] = new YY(1);this.yy[1] = new YY(1);this.yy[2] = new YY(0);
		}else if(a%8 == 3){
			this.yy[0] = new YY(1);this.yy[1] = new YY(0);this.yy[2] = new YY(1);
		}else if(a%8 == 4){
			this.yy[0] = new YY(1);this.yy[1] = new YY(0);this.yy[2] = new YY(0);
		}else if(a%8 == 5){
			this.yy[0] = new YY(0);this.yy[1] = new YY(1);this.yy[2] = new YY(1);
		}else if(a%8 == 6){
			this.yy[0] = new YY(0);this.yy[1] = new YY(1);this.yy[2] = new YY(0);
		}else if(a%8 == 7){
			this.yy[0] = new YY(0);this.yy[1] = new YY(0);this.yy[2] = new YY(1);
		}
	}
	public int getBGName(){
		if(this.yy[0].QY()%2 == 0){
			if(this.yy[1].QY()%2 == 0){
				if(this.yy[2].QY()%2 == 0){
					this.BGName = 8;
				}else{
					this.BGName = 7;
				}
			}else{
				if(this.yy[2].QY()%2 == 0){
					this.BGName = 6;
				}else{
					this.BGName = 5;
				}
			}
		}else{
			if(this.yy[1].QY()%2 == 0){
				if(this.yy[2].QY()%2 == 0){
					this.BGName = 4;
				}else{
					this.BGName = 3;
				}
			}else{
				if(this.yy[2].QY()%2 == 0){
					this.BGName = 2;
				}else{
					this.BGName = 1;
				}
			}
		}
		return this.BGName;
	}
	public void setYY(int i,int j){
		if(i >=0 && i<=2){
			this.yy[i].FY(j%3);
		}else{
			System.out.println("setBG失败参数"+i);
		}
	}
	public int getYY(int i){
		if(i >=0 && i<=2){
			return this.yy[i].QY();
		}else{
			System.out.println("getBG失败参数"+i);
			return -1;
		}
	}
	public void BYY(int i){
		if(i >=0 && i<=2){
			this.yy[i].BY();
		}else{
			System.out.println("BBG失败参数"+i);
		}
	}
	public String BGP(int b){
		return this.yy[b%3].DY();
	}
	public String BIO(int b){
		return this.yy[b%3].IO();
	}
}
class YY{
	private int a;
	public YY(int a){
		this.a = Math.abs(a);
	}
	public void FY(int i){
		this.a = i;
	}
	public int QY(){
		return this.a;
	}
	public void BY(){
		this.a++;
	}
	public String DY(){
		if(this.a%2==1){
			return "─── ";
		}else{
			return "─ ─ ";
		}
	}
	public String IO(){
		if(this.a%2==1){
			return "─── ";
		}else{
			return "─  ─ ";
		}
	}
}
class TDSS{
	private int[] tg,dz;
	public TDSS(SC s1){
		this.tg = s1.getTG();
		this.dz = s1.getDZ();
	}
	public String getSS(){//TODO
		return TYGR();
	}
	private String TYGR() {
		String res = null;
		if(this.tg.length > 2){
			for(int i = 0;i < this.tg.length;i++){
				if((this.dz[i] == 2 || this.dz[i] == 8) && (tg[2] == 1 || tg[2] == 5)){
					res = "甲戊并牛羊";
				}else if((this.dz[i] == 1 || this.dz[i] == 9) && (tg[2] == 2 || tg[2] == 6)){
					res = "乙己鼠猴乡";
				}else if((this.dz[i] == 0 || this.dz[i] == 10) && (tg[2] == 3 || tg[2] == 4)){
					res = "丙丁猪鸡位";
				}else if((this.dz[i] == 4 || this.dz[i] == 6) && (tg[2] == 0 || tg[2] == 9)){
					res = "壬癸兔蛇藏";
				}else if((this.dz[i] == 3 || this.dz[i] == 7) && (tg[2] == 7 || tg[2] == 8)){
					res = "庚辛逢虎马";
				}
			}
		}
		return res;
	}
}
class SC{
	private String[] TG = {"癸","甲","乙","丙","丁","戊","己","庚","辛","壬"};
	private String[] DZ = {"亥","子","丑","寅","卯","辰","巳","午","未","申","酉","戌"};
	private int[] tg,dz;
	private String SC;
	public SC(String NYRS){
		Pattern p = Pattern.compile("\\d{4}(\\D\\d{1,2}){0,3}$");
		Matcher m = p.matcher(NYRS);
		if(m.find()){
			this.SC = m.group();
			this.tg = TGDZ(this.SC, 0);
			this.dz = TGDZ(this.SC, 1);
		}else{
			System.out.print("构造SC失败，参数无法匹配NYRS！");
			System.exit(0);
		}
	}
	public SC(int[] tg,int[] dz){
		if(tg.length != dz.length){
			System.out.println("构造SC失败!TG长度:"+tg.length+"DZ长度:"+dz.length);
		}else{
			if(tg.length < 3){
				System.out.println("tg数量不足！tg长度："+tg.length);
			}else{
				this.tg = tg;
				this.dz = dz;
			}
		}
	}
	public int getLenth(){
		if(this.SC != null){
			return this.SC.split("\\D").length;
		}else{
			return this.tg.length;
		}
	}
	public int[] getTG(){
		return this.tg;
	}
	public int[] getDZ(){
		return this.dz;
	}
	public String[] getBZ(){
		String[] res = new String[getLenth()];
		for(int i = 0 ;i < res.length;i++){
			res[i] = this.TG[this.tg[i]]+" "+this.DZ[this.dz[i]];
		}
		return res;
	}
	public String gzch(){
		String res1 = "CH";
		short[] ch = new short[9];
		String[] c = {"甲午","乙巳","丙戌","丁亥","戊子","己亥","庚辰","辛巳","壬午"};//g1-z7;g2-z6;g3-z11;g4-z0;g5-z1;g6-z0;g7-z5;g8-z6;g9-z7;
		for(int i = 0;i < this.dz.length;i++){
			if(this.dz[i] == 0){
				if(this.tg[i] == 4){
					ch[3]++;
				}
				if(this.tg[i] == 6){
					ch[5]++;
				}
			}else if(this.dz[i] == 1){
				if(this.tg[i] == 5){
					ch[4]++;
				}
			}else if(this.dz[i] == 5){
				if(this.tg[i] == 7){
					ch[6]++;
				}
			}else if(this.dz[i] == 6){
				if(this.tg[i] == 2){
					ch[1]++;
				}
				if(this.tg[i] == 8){
					ch[7]++;
				}
			}else if(this.dz[i] == 7){
				if(this.tg[i] == 1){
					ch[0]++;
				}
				if(this.tg[i] == 9){
					ch[8]++;
				}
			}else if(this.dz[i] == 11){
				if(this.tg[i] == 3){
					ch[2]++;
				}
			}
		}
		for(int i = 0;i < ch.length;i++){
			if(ch[i] != 0){
				res1 = res1+"gzH"+c[i];
			}
		}
		return res1.substring(2)+" ";
	}
	public String[] tgch(){
		String[] temp = new String[2];//SKH
		String res3 = "XC";
		String res4 = "WH";
		short[] cc = new short[4];
		short[] hh = new short[5];
		String[] XC = {" 甲木庚金为相冲"," 乙木辛金为相冲"," 丙火壬水为相冲"," 丁火癸水为相冲"};//1-7,2-8,3-9,4-0
		String[] WH = {" 甲木己土化合土"," 乙木庚金化合金"," 丙火辛金化合水"," 丁火壬水化合木"," 戊土癸水化合火"};//1-6,2-7,3-8,4-9,5-0
		for(int i = 0;i < this.tg.length;i++){
			for(int j = 0;j < this.tg.length;j++){//he
				if(this.tg[i] < this.tg[j]){//XC
					if(this.tg[j] == (this.tg[i]+6)%10){
						if(this.tg[i]+this.tg[j] == this.tg[i]+8){
							cc[(this.tg[i]+this.tg[j]-8)/2]++;
						}
					}else if(this.tg[i]+this.tg[j] == 4 && (this.tg[i] == 0||this.tg[j] == 0)){
						cc[3]++;
					}
				}else{
					if(this.tg[i] == (this.tg[j]+6)%10){
						if(this.tg[i]+this.tg[j] == this.tg[j]+8){
							cc[(this.tg[i]+this.tg[j]-8)/2]++;
						}
					}else if(this.tg[i]+this.tg[j] == 4 && (this.tg[i] == 0||this.tg[j] == 0)){
						cc[3]++;
					}
				}
				if(this.tg[i] == (this.tg[j]+5)%10){//WH
					hh[(this.tg[i]+4)%5]++;
				}
			}
		}
		for(int i = 0;i < 4;i++){
			if(cc[i] != 0){
				res3 = res3+"@"+cc[i]/2+"个"+XC[i];
			}
		}
		for(int i = 0;i < 5;i++){
			if(hh[i] != 0){
				res4 = res4+"@"+hh[i]/2+"个"+WH[i];
			}
		}
		temp[0] = res3.substring(2);
		temp[1] = res4.substring(2);
		return temp;
	}
	public String[] dzch() {
		String[] temp = new String[6];//SKH
		temp[0] = liu(this.dz).split("-")[0];
		temp[1] = liu(this.dz).split("-")[1];
		temp[2] = liu(this.dz).split("-")[2];
		temp[3] = sanXN(this.dz);
		temp[4] = sanHH(this.dz).split("-")[0];
		temp[5] = sanHH(this.dz).split("-")[1];
		return temp;
	}
	public String dzcg(){
		String res1 = "zq";
		String res2 = "gq";
		String res3 = "yq";
		String[] temp1 = new String[this.dz.length];//正气
		String[] temp2 = new String[this.dz.length];//中气
		String[] temp3 = new String[this.dz.length];//余气
		for(int i = 0;i < this.dz.length;i++){
			int tmp;
			if(((this.dz[i]+11)%12)%3 == 0){//正冬、春、夏、秋
				tmp = ((this.dz[i]+11)%12)/2;
				if(tmp == 3 && ((this.dz[i]+11)%12)%2 == 0){
					temp1[i] = 4+"";
					temp2[i] = 6+"";
				}else{
					temp1[i] = tmp*2+"";
				}
				continue;
			}else if(this.dz[i]%3 == 0){//临冬、春、夏、秋
				tmp = this.dz[i]/3;
				if(tmp == 0){
					temp1[i] = 9+"";
					temp2[i] = 1+"";
				}else{
					temp1[i] = 2*(tmp-1)+1+"";
					temp2[i] = 2*(tmp-1)+3+"";
					temp3[i] = 2*(tmp-1)+5+"";
				}
				continue;
			}else{//墓冬、春、夏、秋
				if(this.dz[i] == 2){
					temp1[i] = 6+"";
					temp2[i] = 0+"";
					temp3[i] = 8+"";
					continue;
				}else if(this.dz[i] == 5){
					temp1[i] = 5+"";
					temp2[i] = 2+"";
					temp3[i] = 0+"";
					continue;
				}else if(this.dz[i] == 8){
					temp1[i] = 6+"";
					temp2[i] = 4+"";
					temp3[i] = 2+"";
					continue;
				}else if(this.dz[i] == 11){
					temp1[i] = 5+"";
					temp2[i] = 8+"";
					temp3[i] = 4+"";
				}
			}
		}
		for(int i = 0;i < this.dz.length;i++){
			if(temp1[i] != null){
				res1 = res1+" "+this.TG[Integer.parseInt(temp1[i])];
			}else{
				res1 = res1+" ○";
			}
			if(temp2[i] != null){
				res2 = res2+" "+this.TG[Integer.parseInt(temp2[i])];
			}else{
				res2 = res2+" ○";
			}
			if(temp3[i] != null){
				res3 = res3+" "+this.TG[Integer.parseInt(temp3[i])];
			}else{
				res3 = res3+" ○";
			}
		}
		return res1.substring(2)+"-"+res2.substring(2)+"-"+res3.substring(2)+" ";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList DYLN(int sex){
		ArrayList res = new ArrayList();
		int[] res0 = new int[16];
		String[] res1 = new String[8];
		if(sex == 0){//NV
			if(this.tg[0]%2 == 0){
				for(int i = 0;i < 8;i++){
					res0[2*i] = (this.tg[1]+1+i)%10;
					res0[2*i+1] = (this.dz[1]+1+i)%12;
					res1[i] =this.TG[res0[2*i]]+" "+this.DZ[res0[2*i+1]];
				}
			}else{
				for(int i = 0;i < 8;i++){
					res0[2*i] = (this.tg[1]+9-i)%10;
					res0[2*i+1] = (this.dz[1]+11-i)%12;
					res1[i] =this.TG[res0[2*i]]+" "+this.DZ[res0[2*i+1]];
				}
			}
		}else{
			if(this.tg[0]%2 == 0){
				for(int i = 0;i < 8;i++){
					res0[2*i] = (this.tg[1]+9-i)%10;
					res0[2*i+1] = (this.dz[1]+11-i)%12;
					res1[i] =this.TG[res0[2*i]]+" "+this.DZ[res0[2*i+1]];
				}
			}else{
				for(int i = 0;i < 8;i++){
					res0[2*i] = (this.tg[1]+1+i)%10;
					res0[2*i+1] = (this.dz[1]+1+i)%12;
					res1[i] =this.TG[res0[2*i]]+" "+this.DZ[res0[2*i+1]];
				}
			}
		}
		res.add(res0);
		res.add(res1);
		return res;
	}
	private String liu(int[] dz2) {
		String res1 = "he";
		String res2 = "ha";
		String res3 = "ch";
		String[] LHe = {" 寅亥生合木"," 巳申绊合水"," 子丑绊合土"," 午未生合土"," 卯戌绊合火"," 辰酉生合金"};//3-0,6-9,1-2,7-8,4-11,5-10
		String[] LHa = {" 申亥相害"," 寅巳相害"," 子未相害"," 午丑相害"," 卯辰相害"," 酉戌相害"};//9-0,3-6,1-8,7-2,4-5,10-11
		String[] LCh = {" 巳亥相冲"," 寅申相冲"," 子午相冲"," 丑未相冲"," 卯酉相冲"," 辰戌相冲"};//6-0,3-9,1-7,2-8,4-10,5-11
		short[] tmp1 = new short[6];
		short[] tmp2 = new short[6];
		short[] tmp3 = new short[6];
		for(int i = 0;i < dz2.length;i++){
			if(dz2[i]%3 == 0){//寅巳申亥
				for(int j = 0;j < dz2.length;j++){//he
					if(dz2[i] < dz2[j]){
						if(dz2[j] == (dz2[i]+(2*((dz2[i]/3)%2)-1)*9+12)%12){
							tmp1[(dz2[i]/3)/2]++;
							continue;
						}else if(dz2[j] == (dz2[i]+(2*((1+dz2[i]/3)%2)-1)*9+12)%12){//ha
							tmp2[((dz2[i]+10)%9)/4]++;
							continue;
						}else if(dz2[j] == (dz2[i]+6)%12){//ch
							tmp3[(dz2[i]%6)/3]++;
						}
					}else{
						if(dz2[i] == (dz2[j]+(2*((dz2[j]/3)%2)-1)*9+12)%12){
							tmp1[(dz2[j]/3)/2]++;
							continue;
						}else if(dz2[i] == (dz2[j]+(2*((1+dz2[j]/3)%2)-1)*9+12)%12){//ha
							tmp2[((dz2[j]+10)%9)/4]++;
							continue;
						}else if(dz2[i] == (dz2[j]+6)%12){//ch
							tmp3[(dz2[j]%6)/3]++;
						}
					}
				}
				continue;
			}else if(dz2[i]%6 == 1 || dz2[i]%6 == 2){//子丑午未
				for(int j = 0;j < dz2.length;j++){//he
					if(dz2[i] < dz2[j]){
						if(dz2[j] == dz2[i]+1*(2*(dz2[i]%2)-1)){
							tmp1[dz2[i]/7+2]++;
							continue;
						}else if(dz2[j] == (dz2[i]+7*(2*(dz2[i]%2)-1)+12)%12){//ha
							tmp2[(dz2[i]%4)/2+2]++;
							continue;
						}else if(dz2[j] == (dz2[i]+6*(1-2*(dz2[i]/7))+12)%12){//ch
							tmp3[3-dz2[i]%2]++;
						}
					}else{
						if(dz2[i] == dz2[j]+1*(2*(dz2[j]%2)-1)){
							tmp1[dz2[j]/7+2]++;
							continue;
						}else if(dz2[i] == (dz2[j]+7*(2*(dz2[j]%2)-1)+12)%12){//ha
							tmp2[(dz2[j]%4)/2+2]++;
							continue;
						}else if(dz2[i] == (dz2[j]+6*(1-2*(dz2[j]/7))+12)%12){//ch
							tmp3[3-dz2[j]%2]++;
						}
					}
				}
				continue;
			}else if(dz2[i]%6 == 4 || dz2[i]%6 == 5){//卯辰酉戌
				for(int j = 0;j < dz2.length;j++){//he
					if(dz2[i] < dz2[j]){
						if(dz2[j] == 15-dz2[i]){
							tmp1[5-(dz2[i]%5)%3]++;
							continue;
						}else if(dz2[j] == dz2[i]-2*(dz2[i]%2)+1){//ha
							tmp2[4+dz2[i]/6]++;
							continue;
						}else if(dz2[j] == dz2[i]-6*(2*(dz2[i]/6)-1)){//ch
							tmp3[4+dz2[i]%2]++;
						}
					}else{
						if(dz2[i] == 15-dz2[j]){
							tmp1[5-(dz2[j]%5)%3]++;
							continue;
						}else if(dz2[i] == dz2[j]-2*(dz2[j]%2)+1){//ha
							tmp2[4+dz2[j]/6]++;
							continue;
						}else if(dz2[i] == dz2[j]-6*(2*(dz2[j]/6)-1)){//ch
							tmp3[4+dz2[j]%2]++;
						}
					}
				}
			}
		}
		for(int i = 0;i < 6;i++){
			if(tmp1[i] != 0){
				res1 = res1+"LHE"+tmp1[i]/2+"个"+LHe[i];
			}
			if(tmp2[i] != 0){
				res2 = res2+"LHH"+tmp2[i]/2+"个"+LHa[i];
			}
			if(tmp3[i] != 0){
				res3 = res3+"LCH"+tmp3[i]/2+"个"+LCh[i];
			}
		}
		return res1.substring(2)+"-"+res2.substring(2)+"-"+res3.substring(2)+" ";
	}
	private String sanXN(int[] dz2) {
		String res ="SX";
		short[] tmp = new short[4];
		String[] SXN = {" 寅巳申相刑"," 丑戌未相刑"," 子卯相刑"," 亥辰午酉自刑"};//3-6-9;2-8-11(5);1-4;5;7;10;0;
		for(int i = 0;i < dz2.length;i++){
			if(dz2[i] == 6){
				for(int j = 0;j < dz2.length;j++){
					if(dz2[j] == 3){
						for(int k = 0;k < dz2.length;k++){
							if(dz2[k] == 9){
								tmp[0]++;
							}
						}
					}
				}
				continue;
			}else if(dz2[i] == 8){//2-8-11
				for(int j = 0;j < dz2.length;j++){
					if(dz2[j] == 2){
						for(int k = 0;k < dz2.length;k++){
							if(dz2[k] == 11){
								tmp[1]++;
							}
						}
					}
				}
				continue;
			}else if(dz2[i] == 1 || dz2[i] == 4){//1-4
				for(int j = 0;j < dz2.length;j++){
					if(dz2[j] == dz2[i]+3*(1-2*(dz2[i]/3))){
						tmp[2]++;
					}
				}
				continue;
			}else if(6 == dz2[i]%6 + 4*(dz2[i]/6)+1 || 6 == dz2[i]%6 - 8*((dz2[i]+11)/12-1) + 2*(2*(dz2[i]/10)-1)){//0-5-7-10
				for(int j = 0;j < i;j++){//TODO
					if(dz2[j] == dz2[i]){//算法暂定
						tmp[3]++;
					}
				}
				for(int j = i+1;j < dz2.length;j++){//TODO
					if(dz2[j] == dz2[i]){//算法暂定
						tmp[3]++;
					}
				}
			}
		}
		for(int i = 0;i < 4;i++){
			if(tmp[i] != 0){
				if(i < 2){
					res = res+"!"+tmp[i]+"个"+SXN[i];
				}else{
					res = res+"!"+tmp[i]/2+"个"+SXN[i];
				}
			}
		}
		return res.substring(2)+" ";
	}
	private String sanHH(int[] dz2) {
		String res1 ="He";
		String res2 ="Hu";
		short[] tmp1 = new short[6];
		short[] tmp2 = new short[6];
		String[] sanHe = {" 申子辰化合水"," 亥卯未化合木"," 寅午戌化合火"," 巳酉丑化合金"};//9-1(13)-5(17);0-4-8;3-7-11;6-10-2(14);
		String[] sanHu = {" 亥子丑会北水"," 寅卯辰会东木"," 巳午未会南火"," 申酉戌会西金"};//0-1-2;3-4-5;6-7-8;9-10-11;
		for(int i = 0;i < dz2.length;i++){
			if(dz2[i]%3 == 1){//确认是否存在太极点
				for(int j = 0;j < dz2.length;j++){
					if(dz2[j] == (dz2[i]+8)%12){//确认是否存在合
						for(int k = 0;k < dz2.length;k++){
							if(dz2[k] == (dz2[i]+4)%12){
								tmp1[dz2[i]/3]++;
							}
						}
						continue;
					}else if(dz2[j] == dz2[i]-1){//确认是否存在会
						for(int k = 0;k < dz2.length;k++){
							if(dz2[k] == dz2[i]+1){
								tmp2[dz2[i]/3]++;
							}
						}
					}
				}
			}
		}
		for(int i = 0;i < 6;i++){
			if(tmp1[i] != 0){
				res1 = res1+"~"+tmp1[i]+"个"+sanHe[i];
			}
			if(tmp2[i] != 0){
				res2 = res2+"~"+tmp2[i]+"个"+sanHu[i];
			}
		}
		return res1.substring(2)+"-"+res2.substring(2)+" ";
	}
	private int[] TGDZ(String sc2, int j) {
		String[] timeSC = sc2.split("\\D");
		int[] res = new int[timeSC.length];
		for(int i = 0;i < timeSC.length;i++){
			if(j == 0){//TG
				if(i == 0){//N
					res[i] = (Integer.parseInt(timeSC[i])-3)%10;
				}else if(i == 1){//Y
					if(Integer.parseInt(timeSC[i]) == 1){
						res[i-1] = (res[i-1]+9)%10;
						res[i] = (NYS(res[i-1])+11)%10;
					}else{
						res[i] = (NYS(res[i-1])+Integer.parseInt(timeSC[i])-2)%10;
					}
				}else if(i == 2){//R
					if(Integer.parseInt(timeSC[i]) < JQ(Integer.parseInt(timeSC[0]),Integer.parseInt(timeSC[1]))){
						if(Integer.parseInt(timeSC[i-1]) == 1){
							res[i-1] = (NYS(res[i-2])+Integer.parseInt(timeSC[i-1])-1)%10;
						}else{
							res[i-1] = (NYS(res[i-2])+Integer.parseInt(timeSC[i-1])-3)%10;
							if(Integer.parseInt(timeSC[i-1]) == 2){
								res[i-2] = (res[i-2]+9)%10;
							}
						}
					}
					res[i] =(new countTime(this.SC).getDiff()-2)%10;
				}else if(i == 3){//S
					if(Integer.parseInt(timeSC[i]) == 23){
						res[i] = (NYS(res[i-1])+8)%10;
					}else{
						res[i] = (NYS(res[i-1])+(Integer.parseInt(timeSC[i])+1)/2+8)%10;
					}
				}else{//FM
					res[i] = (Integer.parseInt(timeSC[i]))%10;
				}
			}else{//DZ
				if(i == 0){//N
					res[i] = (Integer.parseInt(timeSC[i])-3)%12;
				}else if(i == 1){//Y
					if(Integer.parseInt(timeSC[i]) == 1){
						res[i-1] = (res[i-1]+11)%12;
					}
					res[i] = (Integer.parseInt(timeSC[i])+1)%12;
				}else if(i == 2){//R
					if(Integer.parseInt(timeSC[i]) < JQ(Integer.parseInt(timeSC[0]),Integer.parseInt(timeSC[1]))){
						res[i-1] = (Integer.parseInt(timeSC[i-1]))%12;
						if(Integer.parseInt(timeSC[i-1]) == 2){
							res[i-2] = (res[i-2]+11)%12;
						}
					}
					res[i] = (new countTime(this.SC).getDiff()+2)%12;
				}else if(i == 3){//S
					res[i] = ((Integer.parseInt(timeSC[i])+3)/2)%12;
				}else{//FM
					res[i] = Integer.parseInt(timeSC[i])%12;
				}
			}
		}
		return res;
	}
	
	private int JQ(int parseInt, int i) {
		int firstDay = 0;
		int[] fry = {31,28,31,30,31,30,31,31,30,31,30,31};
		int[] ry = {31,29,31,30,31,30,31,31,30,31,30,31};
		if(i == 1){
			firstDay = (int) (((parseInt-1)*0.2422+4.475)-((parseInt-1)/4-15));
			if((parseInt-1)%4 == 0){
				for(int j = 2;j < 13;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- ry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- ry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- ry[j-1];
					}else{
						firstDay = firstDay + 304/10- ry[j-1];
					}
				}
			}else{
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- fry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- fry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- fry[j-1];
					}else{
						firstDay = firstDay + 304/10- fry[j-1];
					}
				}
			}
		}else{
			firstDay = (int) ((parseInt*0.2422+4.475)-(parseInt/4-15));
			if(parseInt%4 == 0){
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- ry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- ry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- ry[j-1];
					}else{
						firstDay = firstDay + 304/10- ry[j-1];
					}
				}
			}else{
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- fry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- fry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- fry[j-1];
					}else{
						firstDay = firstDay + 304/10- fry[j-1];
					}
				}
			}
		}
		return firstDay;
	}
	private int NYS(int res2){
		if(res2 == 0 || res2 ==5){
			return 1;
		}else if(res2 == 1 || res2 ==6){
			return 3;
		}else if(res2 == 2 || res2 ==7){
			return 5;
		}else if(res2 == 3 || res2 ==8){
			return 7;
		}else{
			return 9;
		}
	}
}
class countTime{
    private int[] ry={31,29,31,30,31,30,31,31,30,31,30,31};
    private int[] fry={31,28,31,30,31,30,31,31,30,31,30,31};
    private String t1,t2;
    private Pattern p = Pattern.compile("\\d{4}(\\D\\d{1,2}){2,3}$");
    public countTime(String d1,String d2){
		Matcher m1 = p.matcher(d1);
		Matcher m2 = p.matcher(d2);
		if(m1.find() && m2.find()){
			this.t1 = m1.group();
			this.t2 = m2.group();
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1+"d2="+d2);
			System.exit(0);
		}
    }
    public countTime(String d1){
		Matcher m = p.matcher(d1);
		if(m.find()){
			this.t1 = m.group();
			this.t2 = "1001-1-1";
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1);
			System.exit(0);
		}
    }
    public int getDiff(){
    	if(Integer.parseInt(this.t1.split("\\D")[0])-Integer.parseInt(this.t2.split("\\D")[0]) > 0){
    		return Diff(this.t1,this.t2);
    	}else{
    		return Diff(this.t2,this.t1);
    	}
    }
    private int Diff(String y1,String y2) {
    	int res = 0;
    	int temp1 = Integer.parseInt(y1.split("\\D")[0]);
    	int temp2 = Integer.parseInt(y2.split("\\D")[0]);
    	int temp3 = Integer.parseInt(y1.split("\\D")[1]);
    	int temp4 = Integer.parseInt(y2.split("\\D")[1]);
    	int temp5 = Integer.parseInt(y1.split("\\D")[2]);
    	int temp6 = Integer.parseInt(y2.split("\\D")[2]);
    	for (int i = 0;i<1+temp1-temp2;i++)//所有整年的总天数天数
        {
            if((temp2+i)%100 == 0){
            	if((temp2+i)%400 == 0){
            		res = RN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}else{
            		res = FRN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}
            }else{
            	if((temp2+i)%4 == 0){
            		res = RN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}else{
            		res = FRN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}
            }
        }
		return res;
    }
	private int RN(int i,int temp1, int temp2, int temp3, int temp4, int temp5,
			int temp6,int res) {
		res = res + 366;
    	if(i == 0){//去除firstYeard多的
    		for(int j = 0;j < temp4-1;j++){
    			res = res - this.ry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//去除lastYear多的
        	for(int j = temp3-1;j < 12;j++){
        		res = res - this.ry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
	private int FRN(int i,int temp1, int temp2, int temp3, int temp4, int temp5,
			int temp6,int res) {
		res = res + 365;
    	if(i == 0){//去除firstYeard多的
    		for(int j = 0;j < temp4-1;j++){
    			res = res - this.fry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//去除lastYear多的
        	for(int j = temp3-1;j < 12;j++){
        		res = res - this.fry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
}