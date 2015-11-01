package forAPP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class countTime{
    private static int[]	ry	= {31,29,31,30,31,30,31,31,30,31,30,31};
    private static int[]	fry	= {31,28,31,30,31,30,31,31,30,31,30,31};
    private	int[]	temp;
    private Pattern	p = Pattern.compile("\\d{4}(\\D\\d{1,2}){2,3}$");
    public countTime(String d1,String d2){
    	String[]	tmp1,tmp2;
		Matcher m1 = p.matcher(d1);
		Matcher m2 = p.matcher(d2);
		if(m1.find() && m2.find()){
			tmp1	=	m1.group().split("\\D");
			tmp2	=	m2.group().split("\\D");
			if (tmp1.length	==	tmp2.length){
				this.temp	=	new int[tmp1.length*2];
				if (Integer.parseInt(tmp1[0]) > Integer.parseInt(tmp2[0])) {
					for (int i = 0;i < tmp1.length;i++){
						this.temp[i*2]		=	Integer.parseInt(tmp1[i]);
						this.temp[i*2+1]	=	Integer.parseInt(tmp2[i]);
					}
				}else{
					for (int i = 0;i < tmp1.length;i++){
						this.temp[i*2]		=	Integer.parseInt(tmp2[i]);
						this.temp[i*2+1]	=	Integer.parseInt(tmp1[i]);
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "it`s different length between the tow date String given !", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1+"d2="+d2);
			System.exit(0);
		}
    }
    public countTime(String d1){
		Matcher m = p.matcher(d1);
		String[]	tmp;
		if(m.find()){
			tmp	=	m.group().split("\\D");
			this.temp	=	new int[tmp.length*2];
			for (int i = 0;i < tmp.length;i++){
				this.temp[2*i]		=	Integer.parseInt(tmp[i]);
			}
			this.temp[1]	=	1001;this.temp[3]	=	1;this.temp[5]	=	1;
		}else{
			System.out.print("构造countTime失败，参数无法匹配！d1="+d1);
			System.exit(0);
		}
    }
    public int getDiff(){
    	int	res = 0;
    	for (int i = 0;i<1+this.temp[0]-this.temp[1];i++)//所有整年的总天数天数
        {
            if((this.temp[1]+i)%100 == 0){
            	if((this.temp[1]+i)%400 == 0){
            		res = yearDays(true, i, this.temp, res);
            	}else{
            		res = yearDays(false, i, this.temp, res);
            	}
            }else{
            	if((this.temp[1]+i)%4 == 0){
            		res = yearDays(true, i, this.temp, res);
            	}else{
            		res = yearDays(false, i, this.temp, res);
            	}
            }
        }
		return Math.abs(res);
    }
    public String getDate(int delta){
    	int		tmp1 = 0, tmp2 = delta;
    	int[]	tmp = new int[this.temp.length],monthDay;
    	for (int j = 0;j < tmp.length/2;j++){
    		tmp[2*j]	= this.temp[2*j];
    		tmp[2*j+1]	= this.temp[2*j];
    	}
    	while(tmp2 != 0) {
    		if((tmp[1])%100 == 0){
            	if((tmp[1])%400 == 0){
            		monthDay = ry;
            	}else{
            		monthDay = fry;
            	}
            }else{
            	if((tmp[1])%4 == 0){
            		monthDay = ry;
            	}else{
            		monthDay = fry;
            	}
            }
    		if (tmp2 > 0){
    			if (tmp[5]+tmp2 <= monthDay[tmp[3]-1]){
    				return tmp[1]+"-"+tmp[3]+"-"+(tmp[5]+tmp2);
    			}else{
    				if (tmp1++ == 0){
    					tmp2 = tmp2 - monthDay[tmp[3]-1] + tmp[5] - 1;
    					tmp[5] = 1;
    				}else{
    					tmp2 = tmp2 - monthDay[tmp[3]-1];
    				}
    				if(++tmp[3] > 12){
    					tmp[1]++;tmp[3] = 1;
    				}
    			}
    		}else{
    			if (tmp[5]+tmp2 >= 1){
    				return tmp[1]+"-"+tmp[3]+"-"+(tmp[5]+tmp2);
    			}else{
    				if (tmp1++ == 0){
    					tmp2 = tmp2 + tmp[5];
    				}else{
    					tmp2 = tmp2 + monthDay[tmp[3]-1];
    				}
    				if(--tmp[3] < 1){
    					tmp[1]--;tmp[3] = 12;
    				}
    				tmp[5] = monthDay[tmp[3]-1];
    			}
    		}
    	}
		return tmp[1]+"-"+tmp[3]+"-"+tmp[5];
    }
    public String getYL(){
    	int[]		mon = new int[12];
    	String[]	res = {"", "1900-1-1", this.temp[0]+"-"+this.temp[2]+"-"+this.temp[4], "", ""};
    	String[][]	mName = {
    			{"正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","冬月","腊月"},
    			{"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十",
    			 "十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",
    			 "廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十",}}; 
    	if (this.temp[0] > 1900){
    		mon[0] = (int) ((new countTime(res[1], res[2]).getDiff()+0.4799)/29.530);//获得阴历的阴历月总数
    		mon[1] = (int) ((new countTime(res[1], res[2]).getDiff()+0.4799)%29.530);//获得阴历的日子
    		if (mon[1] == 0){
    			mon[0]--;
    			mon[1] = 1 + (int) ((new countTime(new countTime(res[2]).getDate(-1), res[1]).getDiff()+0.4799)%29.530);
    		}
    		res[3] = new countTime(res[1]).getDate((int) (29.530*mon[0] + 2));//获得阴历当月的第一天
    		res[4] = new countTime(res[1]).getDate((int) (29.530*(mon[0] + 1)));//获得阴历当月的最后一天
    		if (this.temp[2] == 1){
    			mon[2] = new countTime((this.temp[0]-1)+"-12-"+JQ(this.temp[0]-1, 12)[1]).getDiff();
    			mon[4] = new countTime(this.temp[0]+"-2-"+JQ(this.temp[0], 2)[1]).getDiff();
    			mon[7] = this.temp[0]-1;
    			mon[8] = 12;
    			mon[9] = this.temp[0];
    			mon[10]= 2;
    		}else if (this.temp[2] == 12){
    			mon[2] = new countTime(this.temp[0]+"-11-"+JQ(this.temp[0], 11)).getDiff();
    			mon[4] = new countTime((this.temp[0]+1)+"-1-"+JQ(this.temp[0]+1, 1)).getDiff();
    			mon[7] = this.temp[0];
    			mon[8] = 11;
    			mon[9] = this.temp[0] + 1;
    			mon[10]= 1;
    		}else{
    			mon[2] = new countTime(this.temp[0]+"-"+(this.temp[2]-1)+"-"+JQ(this.temp[0], this.temp[2] - 1)[1]).getDiff();//获得上月的中气
    			mon[4] = new countTime(this.temp[0]+"-"+(this.temp[2]+1)+"-"+JQ(this.temp[0], this.temp[2] + 1)[1]).getDiff();//获得下月的中气
    			mon[7] = this.temp[0];
    			mon[8] = this.temp[2] - 1;
    			mon[9] = this.temp[0];
    			mon[10]= this.temp[2] + 1;
    		}
    		mon[3] = new countTime(this.temp[0]+"-"+this.temp[2]+"-"+JQ(this.temp[0], this.temp[2])[1]).getDiff();//获得当月的中气
    		mon[5] = new countTime(res[3]).getDiff();
    		mon[6] = new countTime(res[4]).getDiff();
    		if (mon[5] <= mon[2] && mon[6] < mon[3]){
    			res[0] = mName[0][(mon[8]+10)%12]+mName[1][mon[1]-1];
    		}else if (mon[5] > mon[2] && mon[6] < mon[3]){
    			res[0] = "润"+mName[0][(mon[8]+10)%12]+mName[1][mon[1]-1];
    		}else if (mon[5] <= mon[3] && mon[6] < mon[4]){
    			res[0] = mName[0][(this.temp[2]+10)%12]+mName[1][mon[1]-1];
    		}else if (mon[5] > mon[3] && mon[6] < mon[4]){
    			res[0] = "润"+mName[0][(this.temp[2]+10)%12]+mName[1][mon[1]-1];
    		}else if (mon[5] <= mon[4] && mon[4] < mon[6]){
    			res[0] = mName[0][(mon[10]+10)%12]+mName[1][mon[1]-1];
    		}
    	}else{
    		return "输入年份必须在1901年以后！";
    	}
		return res[0];
    }
    private int yearDays(boolean type,int i,int[] temp,int res){
    	int[]	monthDays;
    	if (type == true){
    		res	= res + 366;
    		monthDays	= ry;
    	}else{
    		res	= res + 365;
    		monthDays	= fry;
    	}
    	if(i == 0){//去除firstYeard多的
    		for(int j = 0;j < temp[3]-1;j++){
    			res = res - monthDays[j];
    		}
    		res = res - temp[5];
    	}
    	if(temp[1]+i == temp[0]){//去除lastYear多的
        	for(int j = temp[2]-1;j < 12;j++){
        		res = res - monthDays[j];
        	}
        	res = res + temp[4];
        }
		return res;
    }
	public static int[] JQ(int parseInt, int i) {
		int			Y	= parseInt%100;
		int[]		JQDay 	= {0,0,0,0};
		double		D	= 0.2422;
		String[]	res = new String[6];
		double[][]	Ce	= {
				{4.63,19.46,6.38,21.416,5.59,20.888,6.318,21.86,6.500,22.20,7.928,23.65,8.35,23.95,8.440,23.822,9.098,24.218,8.218,23.08,7.90,22.60,6.1100,20.84},
				{3.87,18.74,5.63,20.646,4.81,20.100,5.520,21.04,5.678,21.37,7.108,22.83,7.50,23.13,7.646,23.042,8.318,23.438,7.438,22.36,7.18,21.94,5.4055,20.12},
				{4.15,18.73,5.63,20.646,5.59,20.888,6.318,21.86,6.500,22.20,7.928,23.65,8.35,23.95,8.440,23.822,9.098,24.218,8.218,23.08,7.90,22.60,6.1100,20.84}
				};
		if(i == 1 || i == 2){
			if(Y == 0){
				res[0] = ""+Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+20)%24];
				res[1] = ""+Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+21)%24];
			}else{
				res[0] = ""+(Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+20)%24]-(Y-1)/4);
				res[1] = ""+(Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+21)%24]-(Y-1)/4);
			}
		}else{
			res[0] = ""+(Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+20)%24]-Y/4);
			res[1] = ""+(Y*D+Ce[Math.abs(parseInt/100-19)%3][(i*2+21)%24]-Y/4);
		}
		res[2] = res[0].split("\\D")[0];
		res[3] = res[1].split("\\D")[0];
		res[4] = res[0].split("\\D")[1];
		res[5] = res[1].split("\\D")[1];
		for (int j = 0;j < 2;j++){
			if (res[3+j*2].length() > 2){
				res[3+j*2] = res[3+j].substring(0,2);
			}
		}
		for (int j = 0;j < JQDay.length;j++){
			JQDay[j] = Integer.parseInt(res[2+j]);
		}
		return JQDay;
	}
}
