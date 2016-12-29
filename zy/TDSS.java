package universe;

public class TDSS{
	private int[] tg,dz;
	public TDSS(SC s1){
		this.tg = s1.getTG();
		this.dz = s1.getDZ();
	}
	public String getSS(){//TODO
		return "tygr:"+TYGR()+"\r\n";
	}
	//tianyi
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
	//taiji
	private String TJGR() {
		String res = null;
		if(this.tg.length > 2){
			for(int i = 0;i < this.tg.length;i++){
				if((this.dz[i] == 1 || this.dz[i] == 7) && (tg[2] == 1 || tg[2] == 2)){
					res = "甲乙生人子午中";
				}else if((this.dz[i] == 2 || this.dz[i] == 5 || this.dz[i] == 8 || this.dz[i] == 11) && (tg[2] == 5 || tg[2] == 6)){
					res = "戊己两干临四季";
				}else if((this.dz[i] == 4 || this.dz[i] == 10) && (tg[2] == 3 || tg[2] == 4)){
					res = "丙丁鸡兔定亨通";
				}else if((this.dz[i] == 6 || this.dz[i] == 9) && (tg[2] == 0 || tg[2] == 9)){
					res = "壬癸巳申偏喜美";
				}else if((this.dz[i] == 0 || this.dz[i] == 3) && (tg[2] == 7 || tg[2] == 8)){
					res = "庚辛寅亥禄丰隆";
				} 
			}
		}
		return res;
	}
	//tiande
	private String TDGR() {
		String res = "正丁|二申|三壬|四辛|五亥|六甲|七癸|八寅|九丙|十乙|冬巳|腊庚";
		return res;
	}
	//yuede
	private String YDGR() {
		String res = null;
		if(this.tg.length > 2){
			for(int i = 0;i < this.tg.length;i++){
				if((this.dz[i] == 3 || this.dz[i] == 7 || this.dz[i] == 11) && (tg[2] == 3)){
					res = "寅午戌生者见丙";
				}else if((this.dz[i] == 9 || this.dz[i] == 1 || this.dz[i] == 5) && (tg[2] == 9)){
					res = "申子辰生者见壬";
				}else if((this.dz[i] == 0 || this.dz[i] == 4 || this.dz[i] == 8) && (tg[2] == 1)){
					res = "亥卯未生者见甲";
				}else if((this.dz[i] == 6 || this.dz[i] == 10 || this.dz[i] == 2) && (tg[2] == 7)){
					res = "巳酉丑生者见庚";
				}
			}
		}
		return res;
	}
}
