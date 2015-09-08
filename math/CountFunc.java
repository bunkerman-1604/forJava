package PrimaryCount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountFunc {
	private	int			status	=	0;
	private	String		expr1	=	"\\d+?([+-¡Á¡Â]\\d{1,}){1,}";
	private	String		expr2	=	"(\\d{1,}[¡Á*/¡Â]\\d{1,})+?";
	private	String		expr3	=	"(\\d{1,}[+-]\\d{1,})+?";
	private String		expr4	=	"^-(.)+?";
	private	Pattern		p1		=	Pattern.compile(expr1);
	private	Pattern		p2		=	Pattern.compile(expr2);
	private	Pattern		p3		=	Pattern.compile(expr3);
	private	Pattern		p4		=	Pattern.compile(expr4);
	private String		express	;
	private String[]	funcs;
	private int[]		nums;
	private	int			arrow	=	1;

	public CountFunc(String question) {
		// TODO Auto-generated constructor stub
		Matcher	m1	=	p1.matcher(question);
		if (m1.find()) {
			this.express		=	question;
			String[]	tmp1	=	m1.group().split("\\D");
			String		tmp2	=	m1.group().replaceAll("\\d", "");
			this.nums			=	new int[tmp1.length];
			this.funcs			=	new String[tmp2.length()];
			for (int i =0;i < this.nums.length;i++) {
				this.nums[i]	=	Integer.parseInt(tmp1[i]);
				if (i < this.nums.length - 1) {
					this.funcs[i]	=	tmp2.charAt(i)+"";
				}
			}
		}else {
			this.status	=	-1;
		}
	}

	private int func(int nums1, int nums2, String symbl) {
		// TODO Auto-generated method stub
		if (symbl.charAt(0) == '+') {
			return nums1 + nums2;
		}else if (symbl.charAt(0) == '-') {
			return nums1 - nums2;
		}else if (symbl.charAt(0) == '¡Á' | symbl.charAt(0) == '*') {
			return nums1 * nums2;
		}else if (symbl.charAt(0) == '¡Â' | symbl.charAt(0) == '/') {
			if (nums2 == 0) {
				return 0;
			}else{
				return nums1 / nums2;
			}
		}else{
			return 0;
		}
	}

	public String getRes() {
		if (this.status	==	-1) {
			return null;
		}else{
			boolean	bm2	=	true;
			boolean bm3	=	true;
			while (bm2) {
				Matcher	m2		=	p2.matcher(this.express);
				bm2				=	m2.find();
				if (bm2 == true) {
					String[] nums	=	m2.group().split("\\D");
					String	simbl	=	m2.group().replaceAll("\\d", "");
					this.express	=	this.express.replaceFirst(expr2, ""+func(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), simbl));
				}
			}
			while (bm3) {
				Matcher	m3		=	p3.matcher(this.express);
				bm3				=	m3.find();
				if (bm3 == true) {
					String[] nums	=	m3.group().split("\\D");
					String	simbl	=	m3.group().replaceAll("\\d", "");
					this.express	=	this.express.replaceAll(expr3, ""+func(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), simbl));
					Matcher	m4		=	p4.matcher(this.express);
					if (m4.find()) {
						this.express	=	this.express.replaceAll("\\+", "plus");
						this.express	=	this.express.replaceFirst("-", "");
						this.express	=	this.express.replaceAll("-", "+");
						this.express	=	this.express.replaceAll("plus", "-");
						this.arrow		=	-1*this.arrow;
					}
				}
			}
			if (this.arrow == -1) {
				if (this.express.charAt(0) == '-') {
					return this.express.replaceAll("-", "");
				} else {
					return "-"+this.express;
				}
			} else{
				return this.express;
			}
		}
	}
}
