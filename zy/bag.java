package backage;
import java.text.SimpleDateFormat;

public class bag {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-H");
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

