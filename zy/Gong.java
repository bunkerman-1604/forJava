package forAPP;

public class Gong{
	private int GClass,GS;//0-归；7-游；6-本
	public void setGClass(int gClass) {
		GClass = gClass;
	}
	public void setGS(int gS) {
		GS = gS;
	}
	public int getGClass(){
		return this.GClass;
	}
	public int getGS(){
		return this.GS;
	}
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
		}
		setGClass(b);
		setGS(-1);
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
}
