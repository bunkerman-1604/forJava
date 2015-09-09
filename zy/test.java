package backage;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WWG		w1	=	new WWG(12,2,3);
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 3;j++){
				w1.getA()[i].getYy()[j].setIOyang("ÑôÑôÑô	");
				w1.getA()[i].getYy()[j].setIOyin("ÒõÒ»Òõ	");
			}
		}
		String	tmp	=	w1.Print();
//		String	tmp	=	w1.IO();
//		Calendar c1 = Calendar.getInstance();
		System.out.println(tmp);

	}

}
