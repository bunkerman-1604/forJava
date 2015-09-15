package mybackage;

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
    	int res = 0;
    	int temp1 = Integer.parseInt(y1.split("\\D")[0]);
    	int temp2 = Integer.parseInt(y2.split("\\D")[0]);
    	int temp3 = Integer.parseInt(y1.split("\\D")[1]);
    	int temp4 = Integer.parseInt(y2.split("\\D")[1]);
    	int temp5 = Integer.parseInt(y1.split("\\D")[2]);
    	int temp6 = Integer.parseInt(y2.split("\\D")[2]);
    	for (int i = 0;i<1+temp1-temp2;i++)//所有整年的总天数天数
        {
            if((temp2+i)%100 == 0){
            	if((temp2+i)%400 == 0){
            		res = RN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}else{
            		res = FRN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}
            }else{
            	if((temp2+i)%4 == 0){
            		res = RN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
            	}else{
            		res = FRN(i,temp1,temp2,temp3,temp4,temp5,temp6,res);
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
	public static int JQ(int parseInt, int i) {
		int firstDay = 0;
		if(i == 1){
			firstDay = (int) (((parseInt-1)*0.2422+4.475)-((parseInt-1)/4-15));
			if((parseInt-1)%4 == 0){
				for(int j = 2;j < 13;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- ry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- ry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- ry[j-1];
					}else{
						firstDay = firstDay + 304/10- ry[j-1];
					}
				}
			}else{
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- fry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- fry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- fry[j-1];
					}else{
						firstDay = firstDay + 304/10- fry[j-1];
					}
				}
			}
		}else{
			firstDay = (int) ((parseInt*0.2422+4.475)-(parseInt/4-15));
			if(parseInt%4 == 0){
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- ry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- ry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- ry[j-1];
					}else{
						firstDay = firstDay + 304/10- ry[j-1];
					}
				}
			}else{
				for(int j = 2;j < i;j++){
					if(j == 5){
						firstDay = firstDay + 320/10- fry[j-1];
					}else if(j>5 && j<8){
						firstDay = firstDay + 319/10- fry[j-1];
					}else if(j == 8){
						firstDay = firstDay + 320/10- fry[j-1];
					}else{
						firstDay = firstDay + 304/10- fry[j-1];
					}
				}
			}
		}
		return firstDay;
	}
}
