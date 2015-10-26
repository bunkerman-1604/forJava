package forAPP;

public class WWG{
	private BG[] a;
	public BG[] getA() {
		return a;
	}
	public void setA(BG[] a) {
		this.a = a;
	}
	private int[] bd;
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
	public void changeYao(String yangYao,String yinYao){
		for(int i = 0;i < this.a.length;i++) {
			this.a[i].changeYao(yangYao, yinYao);
		}
	}
	public String getExpression(){
		String res = "";
		for(int i = 0;i < 6;i++){
			res = res + getExpression(this.a[0+i/3],i);
			res = res + getExpression(this.a[2+i/3],i);
			res = res + getExpression(this.a[4+i/3],i);
			res = res + getExpression(this.a[6+i/3],i);
			res = res + getExpression(this.a[8+i/3],i);
			res = res + "\r\n";
		}
		return res;
	}
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
	private String getExpression(BG b,int i) {
		return b.getExpression(2-i%3);
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
		YY[]	yy0	=	new YY[3]	,	yy1	=	new YY[3];
		YY[]	y0	=	bg1.getYy()	,	y1	=	bg2.getYy();
		bg[0] = new BG(0);
		bg[1] = new BG(1);
		for(int i = 0;i < 3;i++) {
			yy0[i]	=	new YY(i);
			yy1[i]	=	new YY(i);
		}
		yy0[2].setYao(y0[1].getYao());
		yy0[1].setYao(y0[0].getYao());
		yy0[0].setYao(y1[2].getYao());
		yy1[2].setYao(y0[0].getYao());
		yy1[1].setYao(y1[2].getYao());
		yy1[0].setYao(y1[1].getYao());
		bg[0].setYy(yy0);
		bg[1].setYy(yy1);
		
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
class BG{
	private YY[] yy = new YY[3];
	private int BGNum;
	public YY[] getYy() {
		return yy;
	}
	public void setYy(YY[] yy) {
		this.yy = yy;
	}
	public BG(int aa){//下中上
		int	a	=	Math.abs(aa);
		for(int i = 0;i < 3;i++){
			this.yy[i]	=	new YY(0);
		}
		this.yy[0].setYao(((8-a%8)/4)%2);
		this.yy[1].setYao(((a%8+1)/2)%2);
		this.yy[2].setYao((a%8)%2);
	}
	public BG(int aa,String yangYao,String yinYao){
		int	a	=	Math.abs(aa);
		for(int i = 0;i < 3;i++){
			this.yy[i]	=	new YY(0,yangYao,yinYao);
		}
		this.yy[0].setYao(((8-a%8)/4)%2);
		this.yy[1].setYao(((a%8+1)/2)%2);
		this.yy[2].setYao((a%8)%2);
	}
	public void changeYao(String yangYao,String yinYao){
		for(int i = 0;i < 3;i++) {
			this.yy[i].setYangYao(yangYao);
			this.yy[i].setYinYao(yinYao);
		}
	}
	public int getBGNum(){
		if(this.yy[0].getYao()%2 == 0){
			if(this.yy[1].getYao()%2 == 0){
				if(this.yy[2].getYao()%2 == 0){
					this.BGNum = 8;
				}else{
					this.BGNum = 7;
				}
			}else{
				if(this.yy[2].getYao()%2 == 0){
					this.BGNum = 6;
				}else{
					this.BGNum = 5;
				}
			}
		}else{
			if(this.yy[1].getYao()%2 == 0){
				if(this.yy[2].getYao()%2 == 0){
					this.BGNum = 4;
				}else{
					this.BGNum = 3;
				}
			}else{
				if(this.yy[2].getYao()%2 == 0){
					this.BGNum = 2;
				}else{
					this.BGNum = 1;
				}
			}
		}
		return this.BGNum;
	}
	public void BYY(int i){
		if(i >=0 && i<=2){
			this.yy[i].BY();
		}else{
			System.out.println("BBG失败参数"+i);
		}
	}
	public String getExpression(int b){
		return this.yy[b%3].getExpression();
	}
}
class YY{
	private int yao;
	private	String	yangYao="一一一 |",yinYao="一丶一 |";
	public int getYao() {
		return yao;
	}
	public String getYangYao() {
		return yangYao;
	}
	public void setYangYao(String yangYao) {
		this.yangYao = yangYao;
	}
	public String getYinYao() {
		return yinYao;
	}
	public void setYinYao(String yinYao) {
		this.yinYao = yinYao;
	}
	public void setYao(int yao) {
		this.yao = Math.abs(yao)%2;
	}
	public YY(int a){
		setYao(Math.abs(a)%2);
	}
	public YY(int a,String yangYao,String yinYao){
		setYao(Math.abs(a)%2);
		this.yangYao	=	yangYao;
		this.yinYao		=	yinYao;
	}
	public void BY(){
		setYao(getYao()+1);
	}
	public String getExpression(){
		if(this.yao%2 == 1) {
			return	this.yangYao;
		}else{
			return	this.yinYao;
		}
	}
}