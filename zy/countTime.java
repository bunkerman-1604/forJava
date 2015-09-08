package backage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class countTime{
    private int[] ry={31,29,31,30,31,30,31,31,30,31,30,31};
    private int[] fry={31,28,31,30,31,30,31,31,30,31,30,31};
    private String t1,t2;
    private Pattern p = Pattern.compile("\\d{4}(\\D\\d{1,2}){2,3}$");
    public countTime(String d1,String d2){
		Matcher m1 = p.matcher(d1);
		Matcher m2 = p.matcher(d2);
		if(m1.find() && m2.find()){
			this.t1 = m1.group();
			this.t2 = m2.group();
		}else{
			System.out.print("����countTimeʧ�ܣ������޷�ƥ�䣡d1="+d1+"d2="+d2);
			System.exit(0);
		}
    }
    public countTime(String d1){
		Matcher m = p.matcher(d1);
		if(m.find()){
			this.t1 = m.group();
			this.t2 = "1001-1-1";
		}else{
			System.out.print("����countTimeʧ�ܣ������޷�ƥ�䣡d1="+d1);
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
    	for (int i = 0;i<1+temp1-temp2;i++)//�������������������
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
    	if(i == 0){//ȥ��firstYeard���
    		for(int j = 0;j < temp4-1;j++){
    			res = res - this.ry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//ȥ��lastYear���
        	for(int j = temp3-1;j < 12;j++){
        		res = res - this.ry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
	private int FRN(int i,int temp1, int temp2, int temp3, int temp4, int temp5,
			int temp6,int res) {
		res = res + 365;
    	if(i == 0){//ȥ��firstYeard���
    		for(int j = 0;j < temp4-1;j++){
    			res = res - this.fry[j];
    		}
    		res = res - temp6;
    	}
    	if(temp2+i == temp1){//ȥ��lastYear���
        	for(int j = temp3-1;j < 12;j++){
        		res = res - this.fry[j];
        	}
        	res = res + temp5;
        }
		return res;
	}
}
