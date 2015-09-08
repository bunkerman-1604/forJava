package PrimaryCount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriCount {
	private	int	level	=	0;
	private	int[] nums	;
	private String[] symbl	=	{"+","-","¡Á","¡Â"};
	private String[] func	;
	public PriCount(int level) {
		if (level == 1) {
			this.level	= 1;
			this.nums	= new int[2];
			this.func	= new String[this.nums.length-1];
		}else if (level == 2) {
			this.level	= 2;
			this.nums	= new int[2];
			this.func	= new String[this.nums.length-1];
		}else if (level == 3) {
			this.level	= 2;
			this.nums	= new int[2];
			this.func	= new String[this.nums.length-1];
		}else if (level == 4) {
			this.level	= 1;
			this.nums	= new int[3];
			this.func	= new String[this.nums.length-1];
		}else if (level == 5) {
			this.level	= 2;
			this.nums	= new int[3];
			this.func	= new String[this.nums.length-1];
		}else if (level == 6) {
			this.level	= 2;
			this.nums	= new int[3];
			this.func	= new String[this.nums.length-1];
		}else {
			this.level	= 1;
			this.nums	= new int[2];
			this.func	= new String[this.nums.length-1];
		}
		for (int i = 0 ;i < this.nums.length;i++) {
			this.nums[i]	=	(int) (Math.random()*Math.pow(10, this.level));
			if (i < this.nums.length - 1) {
				if (level == 1) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%2];
				}else if (level == 2) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%2];
				}else if (level == 3) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%4];
				}else if (level == 4) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%2];
				}else if (level == 5) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%2];
				}else if (level == 6) {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%4];
				}else {
					this.func[i]	=	this.symbl[((int) (Math.random()*10))%4];
				}
			}
		}
	}
	public PriCount(String express) {
		String		expr1	=	"\\d+?([+-¡Á*¡Â/]\\d{1,}){1,}";
		Pattern		p		=	Pattern.compile(expr1);
		Matcher		m		=	p.matcher(express);
		if (m.find()) {
			String[] nums	=	m.group().split("\\D");
			String	funs	=	m.group().replaceAll("\\d", "");
			this.nums		=	new int[nums.length];
			this.func		=	new String[funs.length()];
			for (int i = 0;i < this.nums.length;i++) {
				this.nums[i]	=	Integer.parseInt(nums[i]);
				if (i < nums.length - 1) {
					this.func[i]	=	""+funs.charAt(i);
				}
			}
		}
	}
	public String getQuestion() {
		String res = "";
		for (int i = 0;i < this.nums.length;i++) {
			res	=	res	+ this.nums[i];
			if (i < this.nums.length -1) {
				res = res + this.func[i];
			}
		}
		return res;
	}
	public int getAnswer() {
		int res = 0;
		res = Integer.parseInt(new CountFunc(getQuestion()).getRes());
		return res;
	}
}
