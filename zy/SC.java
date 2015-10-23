package bager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SC{
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
	public String DYun(SC s1,int sex) {
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
			pres = pres + (j+1)*10+"---------"+res[j]+":\r\n" + sres.DayYun() + sres.WXN();
		}
		return pres;
	}
	public String DayYun() {
		String[] temp0 = getBZ();
		String[] temp1 = tgch();
		String[] temp2 = dzch();
		String[] temp3 = dzcg().split("-");
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
		res = res+"干支冲合:\r\n"+gzch();
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
	public String WXN() {
		WX wx = new WX(this);
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
	private String[] tgch(){
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
	private String[] dzch() {
		String[] temp = new String[6];//SKH
		temp[0] = liu(this.dz).split("-")[0];
		temp[1] = liu(this.dz).split("-")[1];
		temp[2] = liu(this.dz).split("-")[2];
		temp[3] = sanXN(this.dz);
		temp[4] = sanHH(this.dz).split("-")[0];
		temp[5] = sanHH(this.dz).split("-")[1];
		return temp;
	}
	private String gzch(){
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
	private String dzcg(){
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
	private ArrayList<Object> DYLN(int sex){
		ArrayList<Object> res = new ArrayList<Object>();
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
					if(Integer.parseInt(timeSC[i]) < countTime.JQ(Integer.parseInt(timeSC[0]),Integer.parseInt(timeSC[1]))[0]){
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
					if(Integer.parseInt(timeSC[i]) < countTime.JQ(Integer.parseInt(timeSC[0]),Integer.parseInt(timeSC[1]))[0]){
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
