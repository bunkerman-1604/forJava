package forAPP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String pres =  "记录时间:" + this.logtime + new countTime(this.logtime).getYL() +"\r\n";
		pres = pres + this.tar.getExpression();
		pres = pres + NJGONG(this.tar);
		pres = pres + this.tmp1.DayYun() ;
		pres = pres + this.tmp1.WXN();
		return pres;
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
	public static String DYun(SC s1, boolean sex) {
    	String	pres = "";
    	ArrayList<?> tmp = s1.DYLN(sex, 8);
		int[]	tgdz = (int[]) tmp.get(0),ress = new int[13];
		String[] res = (String[]) tmp.get(1);
		if (3 == tmp.size()){
			ress = (int[]) tmp.get(2);
		}
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
			pres = pres + (j+1)*10+"---------"+res[j]+":\r\n" + sres.DayYun() + sres.WXN();
		}
		return "===>"+ress[9]+"-"+ress[10]+"-"+ress[11]+"("+ress[12]+")"+"<===\r\n"+pres;
	}
	public static String LNian (String date1, String date2, String rounder, boolean NN){
		Pattern		p1 = Pattern.compile("^\\d{4}(\\D\\d{1,2}){3}"),
					p2 = Pattern.compile("^\\d{1,4}\\Z");
		SC	tmp3,tmp10,tmp11;
		int[][]	tmp8, tmp9;
		int startDate,round;
		ArrayList<Object>	tmp4;
		int[]	tmp1, tmp5, tmp6;
		String	res  = "";
		Matcher	m1 = p1.matcher(date1);
		Matcher m2 = p2.matcher(date2);
		Matcher m3 = p2.matcher(rounder);
		if (m1.find() && m2.find() && m3.find()){
			tmp8 = new int[2][6];
			tmp9 = new int[2][4];
			String[]	tmp2 = date1.split("\\D");//获得datatime1的年月日时
			tmp1 = new int[4];
			for (int i = 0;i < 4;i++){
				tmp1[i] = Integer.parseInt(tmp2[i]);
			}
			startDate = Integer.parseInt(m2.group());//获得起始日期
			round = Integer.parseInt(m3.group());//获得跨度范围数值
			tmp3 = new SC(tmp1[0]+"-"+tmp1[1]+"-"+tmp1[2]+"-"+tmp1[3]);//构造datatime1的SC实例
			tmp9[0] = tmp3.getTG();
			tmp9[1] = tmp3.getDZ();
			for (int i = 0;i < 4;i++){
				tmp8[0][i] = tmp9[0][i];
				tmp8[1][i] = tmp9[1][i];
			}
			tmp4 = tmp3.DYLN(NN, 10);//构造datatime1的大运实例
			tmp5 = (int[]) tmp4.get(0);//大运TGDZ
			tmp6 = (int[]) tmp4.get(2);//起运时间
			if (tmp6[9] <= startDate){//判断起始日期是否大于起运日期
				for (int i = 0;i < round;i++){
					tmp10 = new SC((startDate + i)+"-3-1");//构造流年
					tmp8[0][4] = tmp10.getTG()[0];//流年天干
					tmp8[1][4] = tmp10.getDZ()[0];//流年地支
					tmp8[0][5] = tmp5[((startDate - tmp6[9] + i)/10)*2];//大运天干
					tmp8[1][5] = tmp5[((startDate - tmp6[9] + i)/10)*2+1];//大运地支
					tmp11 = new SC(tmp8[0],tmp8[1]);
					res = res +"===>"+(startDate+i)+"<===\r\n" +tmp11.DayYun() + tmp11.WXN();
				}
			}
		}
		return res;
	}
}
