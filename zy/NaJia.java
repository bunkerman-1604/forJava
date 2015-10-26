package forAPP;

public class NaJia{
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