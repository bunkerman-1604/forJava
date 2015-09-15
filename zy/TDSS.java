package mybackage;

public class TDSS{
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
