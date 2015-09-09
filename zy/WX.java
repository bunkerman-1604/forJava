package backage;

public class WX{
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
