package backage;

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
					res = "¼×Îì²¢Å£Ñò";
				}else if((this.dz[i] == 1 || this.dz[i] == 9) && (tg[2] == 2 || tg[2] == 6)){
					res = "ÒÒ¼ºÊóºïÏç";
				}else if((this.dz[i] == 0 || this.dz[i] == 10) && (tg[2] == 3 || tg[2] == 4)){
					res = "±û¶¡Öí¼¦Î»";
				}else if((this.dz[i] == 4 || this.dz[i] == 6) && (tg[2] == 0 || tg[2] == 9)){
					res = "ÈÉ¹ïÍÃÉß²Ø";
				}else if((this.dz[i] == 3 || this.dz[i] == 7) && (tg[2] == 7 || tg[2] == 8)){
					res = "¸ýÐÁ·ê»¢Âí";
				}
			}
		}
		return res;
	}
}
