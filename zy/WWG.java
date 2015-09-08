package backage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class WWG{
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
	public String PT(BG b,int i){
		return b.BPT(2-i%3);
	}
	public String DY(BG b,int i){
		return b.BDY(2-i%3);
	}
	public String Print(){
		String res = "";
		for(int i = 0;i < 6;i++){
			res = res + PT(this.a[0+i/3],i);
			res = res + PT(this.a[2+i/3],i);
			res = res + PT(this.a[4+i/3],i);
			res = res + PT(this.a[6+i/3],i);
			res = res + PT(this.a[8+i/3],i);
			res = res + "\r\n";
		}
		return res;
	}
	public void IO(String path){
		String	res	=	"";
		for(int i = 0;i < 6;i++){
			res = res + DY(this.a[0+i/3],i);
			res = res + DY(this.a[2+i/3],i);
			res = res + DY(this.a[4+i/3],i);
			res = res + DY(this.a[6+i/3],i);
			res = res + DY(this.a[8+i/3],i);
			res = res + "\r\n";
		}
		try {
			OutputStreamWriter	osw	=	new OutputStreamWriter(new FileOutputStream(path));
			osw.write(res);
			osw.close();
		} catch (IOException e) {e.printStackTrace();
		}
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
	public String BDY(int b){
		return this.yy[b%3].DY();
	}
	public String BPT(int b){
		return this.yy[b%3].PT();
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
			return "阳阳阳	";
		}else{
			return "阴  阴	";
		}
	}
	public String PT(){
		if(this.a%2==1){
			return "阳阳阳	";
		}else{
			return "阴    阴	";
		}
	}
}
