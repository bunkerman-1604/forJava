package mybackage;

import java.text.SimpleDateFormat;

public class bag {
	private	WWG	tar;
	private	String	logtime;
	private	SC	tmp1;
	private	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-H");
	public bag(WWG w1){
		this.tar	=	w1;
		this.logtime = sdf.format(new java.util.Date());
		this.tmp1 = new SC(this.logtime);
	}
	public String getExpression(){
		String pres =  "记录时间:" + this.logtime + "\r\n";
		pres = pres + this.tar.getExpression();
		pres = pres + NJGONG(this.tar);
		pres = pres + this.tmp1.DayYun() ;
		pres = pres + this.tmp1.WXN();
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
	public static String[] TakeTwenty(String tar) {
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
	private String NJGONG(WWG tar2) {
		String	pres = "";
		NaJia tem1;
		Gong tem2;
		int[] tg = new int[6];
		int[] dz = new int[6];
		int WG,NG,SHY;
		String[] bb = new WX(this.tar).getLQ();
		pres = pres + bb[0]+" "+bb[2]+" "+bb[4]+" "+bb[6]+" "+bb[8]+" \r\n";
		pres = pres + bb[1]+" "+bb[3]+" "+bb[5]+" "+bb[7]+" "+bb[9]+" \r\n";
		for(int j = 0;j < 2;j++){
			if(j == 0){
				NG = tar2.getBG(1).getBGNum();
				WG = tar2.getBG(0).getBGNum();
			}else{
				NG = tar2.getBG(5).getBGNum();
				WG = tar2.getBG(4).getBGNum();
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
		return pres;
	}
	
}