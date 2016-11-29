package takeone;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//[2014-10-16 06:00:00]开始时间：2014-10-16 06:00:00,发送到分布式服务器[http://10.1.1.57:8080/contract/dataTrans/insertEcgDate,设备号：1312506,合同号：06267029-3954-4c6c-a5b9-5b99c0990a41]开始...
public class TakeOne {
	private static  String POST_URL = "http://218.12.10.30:20007/contract/dataTrans/getOneDataPacks";
	private static  String GET_URL = "http://192.168.100.144:8088/doudou/upgrade/doDownLoad.htm?fileId=167";
//	private static  String GET_URL = "http://192.168.100.144:8088/doudou/buzapp/appList.htm?sysTypeId=IS-DD2000&id=21C883353908594E51F052D8EC6AB0F1CF";
	public static void main(String[] args) throws Exception{
//		TakeZero();//将普通16进制的文本转换成byte文件；
//		TakeTwo();//将16进制报文流读取出来并转换成byte数组；
//		TakeThree();//IP地址正则表达式
//		TakeFour();//查找有格式的合同号由5组最少4个字符的的字符串组成的GUID、时间差、每行前面的时间、及数据包所含时间；
//		TakeFive();//操作EXCEL对象——创建文件、sheet、cell并保存写入文件中；
//		TakeSix();//操作EXCEL对象——读取文件、sheet、以及cell的值；
//		TakeSeven();//Java——Logger
//		TakeEight();//将16进制报文流读取出来转换成一行格式,之后对文本进行按目标字符分割并输出Byte二维数组
//		System.out.println(TakeNine(GET_URL, ""));//http_get普通网页
//		TakeTen(GET_URL, GET_URL, GET_URL);
//		TakeEleven();//打开指定路径程序；
//		TakeTwelve();//打开NOTEPAD,并输输入文字;
//		TakeThirteen("我想有个家");//向剪切板写入String内容
//		System.out.println(TakeFourteen());//从剪切板中获取String并返回
//		TakeFifteen("E:\\Pictures\\1.jpeg");//向剪切板写入Image内容
//		TakeSixteen();//从剪切板中获取Image并返回
//		TakeSeventeen();//鼠标操作;
//		TakeEighteen();//显示鼠标的当前坐标值；
//		TakeNighteen();//Socket/ServerSocket
//		String[] tmp = TakeTwenty("侯瑞华");// 获取目标字符串的HEX码(16进制)
// 		System.out.println(TakeTwentyOne(GET_URL, "d:\\httpdownload"));//http下载文件
		System.out.print(TakeTwentyTwo(""+tmp1.charAt(j)));//随机密码生成
	}
	@SuppressWarnings("deprecation")
	public static void TakeZero() {
		DataInputStream dips;
		try {
			dips = new DataInputStream(new FileInputStream(new File("d:\\pd180.txt")));
			DataOutputStream dops = new DataOutputStream(new FileOutputStream(new File("d:\\test1.txt")));
			Pattern p1 = Pattern.compile("\\w{2}\\W");
			int i = 0;
			while(dips.available()>0){
				i++;
				System.out.println(i);
				Matcher m1 = p1.matcher(dips.readLine());
				while(m1.find()){
					dops.writeByte((byte)Integer.parseInt(m1.group().replaceAll("\\W", "").toUpperCase(),16));
				}
			}
			dips.close();
			dops.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void TakeTwo() throws IOException{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\test.txt"));   
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);   
		System.out.println("Available bytes:" + in.available());   
		byte[] temp = new byte[1024];   
		int size = 0;   
		while ((size = in.read(temp)) != -1) {   
			out.write(temp, 0, size);   
		}   
		in.close();   
		byte[] content = out.toByteArray();   
		System.out.println("Readed bytes count:" + content.length);  
		for(int i = 0;i<content.length;i++){
			System.out.println(content[i]);
		}
	}
	public static void TakeThree() throws IOException{
		String SocketAdress = "218.12.10.26:32402";
		Pattern p1 = Pattern.compile("(?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))");
		Pattern p2 = Pattern.compile("\\d{4,5}");
		Matcher m1 = p1.matcher(SocketAdress);
		Matcher m2 = p2.matcher(SocketAdress);
		if(m1.find() && m2.find()){
			System.out.println(m1.group());
			System.out.println(m2.group());
		}
	}
	@SuppressWarnings("deprecation")
	public static void TakeFour() throws IOException{
		DataInputStream dips = new DataInputStream(new FileInputStream("D:\\cmm.log"));
		Pattern p1 = Pattern.compile("^(.){20}");
		Pattern p2 = Pattern.compile("\\w+\\s\\w+\\W+\\w{4}=\\d{1,}");
		Pattern p3 = Pattern.compile("\\w{4,}-\\w{4,}-\\w{4,}-\\w{4,}-\\w{4,}");
		Pattern p4 = Pattern.compile("-{4}\\w{4}-.{18}");
		Pattern p5 = Pattern.compile("-{2}\\d{1,}ms");//cmm-diff
		Pattern p6 = Pattern.compile("-{2}.{18}$");//cmm-buffertime
		String str = null;
		while(dips.available()>0){
			str = dips.readLine();
			Matcher m1 = p1.matcher(str);
			Matcher m2 = p2.matcher(str);
			Matcher m3 = p3.matcher(str);
			Matcher m4 = p4.matcher(str);
			Matcher m5 = p5.matcher(str);
			Matcher m6 = p6.matcher(str);
			while(m1.find()){
				if (m1.group().replaceAll("\\D", "").length()<14){
					System.out.print("日志记录错误行");
				}else{
					System.out.print("P1----"+m1.group().replaceAll("\\=", "")+" ");
				}
			}
			while(m2.find()){
				System.out.print("P2----"+m2.group().replaceAll("\\D", "")+" ");
			}
			while(m3.find()){
				System.out.print("P3----"+m3.group()+" ");
			}
			while(m4.find()){
				System.out.print("P4----"+m4.group()+" ");
			}
			while(m5.find()){
				System.out.print("P5----"+m5.group()+" ");
			}
			while(m6.find()){
				System.out.print("P6----"+m6.group()+" ");
			}
			System.out.println();
		}
		dips.close();
	}
	public static void TakeFive() throws IOException{
		OutputStream myxls = new FileOutputStream("D:\\myxls.xls");
		HSSFWorkbook	wb = new HSSFWorkbook();
		HSSFSheet		st = wb.createSheet();
		st.createRow(0).createCell(0).setCellValue("my another test !");
		st.createRow(1).createCell(1).setCellValue(123);
		st.createRow(2).createCell(2).setCellValue(true);
		wb.write(myxls);
		myxls.close();
	}
	public static void TakeSix() throws IOException {
		InputStream myxls = new FileInputStream("D:\\Script\\songcuiping.xls");
		HSSFWorkbook	wb = new HSSFWorkbook(myxls);
		for(int i = 0 ;i<3;i++){
			if(wb.getSheetAt(0).getRow(i).getCell(i).getCellType() == HSSFCell.CELL_TYPE_STRING){
				System.out.println("String类型:"+wb.getSheetAt(0).getRow(i).getCell(i).getStringCellValue());
			}
			else if(wb.getSheetAt(0).getRow(i).getCell(i).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				System.out.println("Number类型:"+wb.getSheetAt(0).getRow(i).getCell(i).getNumericCellValue());
			}
			else if(wb.getSheetAt(0).getRow(i).getCell(i).getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
				System.out.println("Bolean类型:"+wb.getSheetAt(0).getRow(i).getCell(i).getBooleanCellValue());
			}
		}
	}
	public static void TakeSeven() throws IOException{
		Logger log1 = Logger.getLogger("lo1");
		log1.setLevel(Level.INFO);
		Logger log2 = Logger.getLogger("lo1.l1");
		log2.setLevel(Level.WARNING);
		log1.info("aaa");
		log2.info("bbb");
		log2.info("ccc");
	}
	@SuppressWarnings("deprecation")
	public static void TakeEight() throws IOException{
		DataInputStream 	in = new DataInputStream(new FileInputStream("e:\\pd-180.txt")); 
		OutputStreamWriter   out = new OutputStreamWriter(new FileOutputStream("d:\\test.txt"));
		Pattern p1 = Pattern.compile("\\w{2}\\W");
		while(in.available()>0){
			String lin = in.readLine();
			if(lin.length()>30){
				Matcher m1 = p1.matcher(lin);
				while(m1.find()){
					out.write(" "+m1.group().replaceAll("\\W", ""));
				}
			}
		}
		in.close();
		out.close();
//		
//		DataInputStream   	  bips = new DataInputStream(new FileInputStream("D:\\DataPath\\qb60.txt"));   
//		byte[][] blog = new byte[15][];
//		String[] str = bips.readLine().split("14 08( \\d{2}){4}");
//		Pattern p2 = Pattern.compile("\\s{1}\\w{2}");
//		for(int k = 0 ;k<str.length;k++){
//			ByteArrayOutputStream baos = new ByteArrayOutputStream(99999); 
//			Matcher m2 = p2.matcher(str[k]);
//			while(m2.find()){
//				baos.write(Integer.parseInt(m2.group().replaceAll("\\s", "").toUpperCase(),16));
//			}
//			blog[k] = baos.toByteArray(); 
//		}
//		bips.close();
	}
	public static String TakeNine(String getAddress,String SessionID) throws IOException{//请求地址,SessionID
		String res = "";
//		String getUrl = GET_URL+"?wardshipId="+URLEncoder.encode("b5e2828f-0c47-404e-9a24-a2eee4245870","utf-8")+"&Key=HLJW2014";
		URL	   getURL = new URL(getAddress);//根据拼凑的URL类型，返回不同的URLConnection子类对象（此处针对HTTP，因此实际上返回的HTTP对象）;
		HttpURLConnection connection = (HttpURLConnection) getURL.openConnection();
		if(SessionID.length() > 0){
			connection.setRequestProperty("Cookie", SessionID);
		}
//		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		while((lines = reader.readLine()) != null){
			byte[] bt = lines.getBytes();
			res = res + new String(bt,"utf-8") + "\r\n";
		}
		reader.close();
		connection.disconnect();
		return res;
	}
	public static String[] TakeTen(String request,String content2,String SessionID) throws IOException{//请求地址,请求内容,CookiesID
		String[] res = new String[3];
		URL postURL = new URL(request);
		HttpURLConnection connection = (HttpURLConnection) postURL.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
//		connection.setFollowRedirects(true);//是否自动重定向;
		connection.setInstanceFollowRedirects(true);//成员函数，仅作用于当前函数;
		connection.setRequestProperty("Connect_type", "application/x-www-form-urlencoded");
		if(SessionID.length() > 0){
			connection.setRequestProperty("Cookie", SessionID);
		}
		//配置连接的Connect_type,配置为application/x-www-form-urlencoded;
		//意为正文是urlencodedbianmagudoe form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码;
		//connection.connection();//getOutputStream()方法会隐含的进行调用connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//		String content = "firstname = "+URLEncoder.encode("Fat人","utf-8");
		//DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		out.writeBytes(content2);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		while((lines = reader.readLine()) != null){
			res[0] = res[0] + new String(lines.getBytes(),"utf-8") + "\r\n";
		}
		reader.close();
		res[1] = connection.getHeaderField("Set-Cookie");
		res[2] = connection.getHeaderField("statusCode");
		connection.disconnect();
		return res;
	}
	@SuppressWarnings("deprecation")
	public static void TakeEleven() throws IOException, InterruptedException{
		String TPH = "D:\\Program Files\\临时\\Hwyl.WpfRecg.exe";
		DataOutputStream dops;
		DataInputStream	 dips;
		Process p = Runtime.getRuntime().exec(TPH);
		dips = new DataInputStream(p.getInputStream());
		dops = new DataOutputStream(p.getOutputStream());
		dips.read();
		while(dips.available()>0){
			System.out.println(dips.readLine());
		}
		dops.close();
		dips.close();
		p.waitFor();
		p.destroy();
	}
	public static void TakeTwelve() throws IOException, AWTException{
		Runtime.getRuntime().exec("notepad");
		Robot robot = new Robot();
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_H);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyPress(KeyEvent.VK_Y);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyPress(KeyEvent.VK_U);
	}
	public static void TakeThirteen(String writeMe) throws AWTException, InterruptedException{//向剪切板写入复制String内容
//		System.out.println(System.currentTimeMillis());//获取当前系统时间毫秒数
//		for(int i = 0;i < 10 ;i++){System.out.println(System.nanoTime());Thread.sleep(1000);}//获取当前系统时间的纳秒数
//		System.err.println("this is error");//自定义提示信息；
		
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();//获得系统剪切板；
		Transferable ttxt = new StringSelection(writeMe);
		sysc.setContents(ttxt, null);
	}
	public static String TakeFourteen() throws UnsupportedFlavorException, IOException{//从剪切板获取String并返回
		String ret = "";
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable ttxt = sysc.getContents(null);
		if(ttxt != null){
			if(ttxt.isDataFlavorSupported(DataFlavor.stringFlavor)){//???
				ret = (String) ttxt.getTransferData(DataFlavor.stringFlavor);
			}
		}
		return ret;
	}
	public static void TakeFifteen(final Image image){//向剪切板写入复制Image内容
		Transferable trans = new Transferable(){
			public DataFlavor[] getTransferDataFlavors(){
				return new DataFlavor[] { DataFlavor.imageFlavor};
			}
			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if(isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}
		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	}
	public static Image TakeSixteen() throws UnsupportedFlavorException, IOException{//从剪切板复制图片出来；
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable trans = clip.getContents(null);
		if(trans == null){
			return null;
		}else if(trans.isDataFlavorSupported(DataFlavor.imageFlavor)){
			return (Image) trans.getTransferData(DataFlavor.imageFlavor);
		}else{
			return null;
		}
	}
	public static void TakeSeventeen() throws AWTException {
		Robot rbt = new Robot();
		rbt.mouseMove(600, 300);
		rbt.mousePress(InputEvent.BUTTON1_MASK);
		rbt.delay(300);
		rbt.mouseMove(700, 500);
		rbt.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public static void TakeEighteen() throws InterruptedException {
		while(true){
			Point p = MouseInfo.getPointerInfo().getLocation();
			System.out.println("鼠标坐标x:"+p.x+"y:"+p.y);
			Thread.sleep(500);
		}
		
	}
	@SuppressWarnings("unused")
	public static void TakeNighteen() throws UnknownHostException, IOException {
		Socket s = new Socket("192.168.0.117", 4321);
		ServerSocket ss=new ServerSocket(5566);
		s = ss.accept();
	}
	public static String[] TakeTwenty(String tar) {
		String[] res = new String[tar.getBytes().length];
		byte[] tmp	= tar.getBytes();
		for(int i = 0;i < tmp.length;i++){
			String temp = Integer.toHexString(tmp[i]);
			if(temp.length() > 2){
				res[i] =temp.substring(temp.length()-2,temp.length());
			}else{
				res[i] =temp;
			}
		}
		return res;
	}
	@SuppressWarnings("resource")
	public static boolean TakeTwentyOne(String httpUrl, String saveFile){
		// 下载网络文件  
	       int bytesum = 0;  
	       int byteread = 0;  
	  
	       URL url = null;  
	    try {  
	        url = new URL(httpUrl);  
	    } catch (MalformedURLException e1) {  
	        // TODO Auto-generated catch block  
	        e1.printStackTrace();  
	        return false;  
	    }  
	  
	       try {  
	           URLConnection conn = url.openConnection();  
	           InputStream inStream = conn.getInputStream();  
	           FileOutputStream twentyfs = new FileOutputStream(saveFile);  
	  
	           byte[] buffer = new byte[1204];  
	           while ((byteread = inStream.read(buffer)) != -1) {  
	               bytesum += byteread;  
	               System.out.println(bytesum);  
	               twentyfs.write(buffer, 0, byteread);  
	           }  
	           return true;  
	       } catch (FileNotFoundException e) {  
	           e.printStackTrace();  
	           return false;  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	           return false;  
	       }
	}
	public static String TakeTwentyTwo(String x){
		String[] sybl={"~","!","@","#","$","%","^","&","*","(",")","?",",",".","/","-"};
		String reg1="\\d";
		Pattern		p1	= Pattern.compile(reg1);
		Matcher		m1	= p1.matcher(x);
		int	randomSeed	= Math.abs(new Random().nextInt());
		if (m1.find() == false){
			if (randomSeed%3 == 0){
				return x.toUpperCase();
			}else{
				return x;
			}
		}
		else{
			if (randomSeed%3 == 0){
				return sybl[randomSeed%16];
			}else{
				return x;
			}
		}
	}
}
