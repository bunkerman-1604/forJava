package backage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class countTime{
    private static int[] ry={31,29,31,30,31,30,31,31,30,31,30,31};
    private static int[] fry={31,28,31,30,31,30,31,31,30,31,30,31};
    private String t1,t2;
    private Pattern p = Pattern.compile("\\d{4}(\\D\\d{1,2}){2,3}$");
    public countTime(String d1,String d2){
		Matcher m1 = p.matcher(d1);
		Matcher m2 = p.matcher(d2);
		if(m1.find() && m2.find()){
			this.t1 = m1.group();
			this.t2 = m2.group();
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1+"d2="+d2);
			System.exit(0);
		}
    }
    public countTime(String d1){
		Matcher m = p.matcher(d1);
		if(m.find()){
			this.t1 = m.group();
			this.t2 = "1001-1-1";
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1);
			System.exit(0);
		}
    }
    public int getDiff(){
    	if(Integer.parseInt(this.t1.split("\\D")[0])-Integer.parseInt(this.t2.split("\\D")[0]) > 0){
    		return Diff(this.t1,this.t2);
    	}else{
    		return Diff(this.t2,this.t1);
    	}
    }
    private int Diff(String y1,String y2) {
    	int		res = 0;
    	int[]	tmp = new int[6];
    	String[]	fory1 = y1.split("\\D"),fory2 = y2.split("\\D");
    	for (int i = 0;i < 3;i++){
    		tmp[i*2]   = Integer.parseInt(fory1[i]);
    		tmp[i*2+1] = Integer.parseInt(fory2[i]);
    	}
    	for (int i = 0;i<1+tmp[0]-tmp[1];i++)//所有整年的总天数天数
        {
            if((tmp[1]+i)%100 == 0){
            	if((tmp[1]+i)%400 == 0){
            		res = RN(i,tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],res);
            	}else{
            		res = FRN(i,tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],res);
            	}
            }else{
            	if((tmp[1]+i)%4 == 0){
            		res = RN(i,tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],res);
            	}else{
            		res = FRN(i,tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],res);
            	}
            }
        }
	return res;
    }
	private int RN(int i,int temp1, int temp2, int temp3, int temp4, int temp5,
			int temp6,int res) {
		res = res + 366;
    	if(i == 0){//去除firstYeard多的
    		for(int j = 0;j < temp4-1;j++){
    			res = res - ry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//去除lastYear多的
        	for(int j = temp3-1;j < 12;j++){
        		res = res - ry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
	private int FRN(int i,int temp1, int temp2, int temp3, int temp4, int temp5,
			int temp6,int res) {
		res = res + 365;
    	if(i == 0){//去除firstYeard多的
    		for(int j = 0;j < temp4-1;j++){
    			res = res - fry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//去除lastYear多的
        	for(int j = temp3-1;j < 12;j++){
        		res = res - fry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
	public static int[] JQ(int parseInt, int i) {
		int			Y	= parseInt%100;
		int[]		JQDay 	= {0,0};
		double		D	= 0.2422;
		double[][]	Ce	= {
				{4.63,19.46,6.38,21.416,5.59,20.888,6.318,21.86,6.500,22.20,7.928,23.65,8.35,23.95,8.440,23.822,9.098,24.218,8.218,23.08,7.90,22.60,6.1100,20.84},
				{3.87,18.74,5.63,20.646,4.81,20.100,5.520,21.04,5.678,21.37,7.108,22.83,7.50,23.13,7.646,23.042,8.318,23.438,7.438,22.36,7.18,21.94,5.4055,20.12},
				{4.15,18.73,5.63,20.646,5.59,20.888,6.318,21.86,6.500,22.20,7.928,23.65,8.35,23.95,8.440,23.822,9.098,24.218,8.218,23.08,7.90,22.60,6.1100,20.84}};
		if (parseInt/100 >= 19){
			if(i == 1 || i == 2){
				if(Y == 0){
					JQDay[0]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+20)%24]);
					JQDay[1]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+21)%24]);
				}else{
					JQDay[0]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+20)%24]-(Y-1)/4);
					JQDay[1]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+21)%24]-(Y-1)/4);
				}
			}else{
				JQDay[0]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+20)%24]-Y/4);
				JQDay[1]	= (int) (Y*D+Ce[parseInt/100-19][(i*2+21)%24]-Y/4);
			}
		}else{
			if(i == 1 || i == 2){
				if(Y == 0){
					JQDay[0]	= (int) (Y*D+Ce[1][(i*2+20)%24]);
					JQDay[1]	= (int) (Y*D+Ce[1][(i*2+21)%24]);
				}else{
					JQDay[0]	= (int) (Y*D+Ce[1][(i*2+20)%24]-(Y-1)/4);
					JQDay[1]	= (int) (Y*D+Ce[1][(i*2+21)%24]-(Y-1)/4);
				}
			}else{
				JQDay[0]	= (int) (Y*D+Ce[1][(i*2+20)%24]-Y/4);
				JQDay[1]	= (int) (Y*D+Ce[1][(i*2+21)%24]-Y/4);
			}
		}
		System.out.println(parseInt+"-"+i+":"+JQDay[0]+"/"+JQDay[1]);
		return JQDay;
	}
}
