import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class DatafileTest{
	public static void main(String[] args){
		String	dbName ="ORCL",Temp;int Style=0;//0—代表查询；1—代表增;2—代表删;3—代表改；
//		String	JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";	//创建SQLServer——JDBC驱动程序连接字符串地址；
//		String	JDriver="com.mysql.jdbc.Driver";						//创建mysql——JDBC驱动程序连接字符串地址；
		String	JDriver="oracle.jdbc.driver.OracleDriver";				//创建Oracle——JDBC驱动程序连接字符串地址；
//		String	url	="jdbc:sqlserver://218.12.10.30:35716;DatabaseName="+dbName;String user="sa";String password="hljwa";	//创建SQLServer的必要的连接字符串；
//		String	url	="jdbc:mysql://192.168.0.199:3306/"+dbName; String user="root";String password="123456";			//创建mysql的必要的连接字符串；
		String	url	="jdbc:oracle:thin:@218.12.10.30:31521:"+dbName; String user="bossadmin";String password="orclboss";				//创建Oracle的必要的连接字符串；
		//按设备号查找
//		String ORDER = "and td.c_returntime between to_date('2015-11-25 16:35:56','yyyy-mm-dd hh24:mi:ss') and to_date('2015-12-30 16:35:56','yyyy-mm-dd hh24:mi:ss')";
//		String	query1="select  td.c_wardshipsid,td.c_deviceid,tm.c_custname,td.c_releasetime,td.c_returntime from twb_devicecustodyserver td left join TMCloud_CustMInfo tm on td.c_custcode =tm.c_custcode where td.c_deviceid like '13125%' "+ORDER+"  order by c_releasetime desc";							//创建SQL语句——针对查询；
		//按合同号查找
//		String	query1="select  td.c_wardshipsid,td.c_deviceid,tm.c_custname,td.c_releasetime,td.c_returntime from twb_devicecustodyserver td left join TMCloud_CustMInfo tm on td.c_custcode =tm.c_custcode where td.c_wardshipsid like 'aa4c9389-bb61-4df3-8635-cb1c55413ade%' ";
		//查找设备表
//		String	query1="select * from TMCloud_Resource a where a.c_physicalflag like '1312%'";
		//查找未佩戴设备的患者
//		String	query1="select * from v_nouse_name";//PatientName
		//查找未归还设备的——设备ID、设备号、合同号、发放时间
//		String	query1="select a.C_ID,a.c_physicalflag,b.c_wardshipsid,b.c_releasetime from TMCloud_Resource a,twb_devicecustodyserver b where a.c_physicalflag like '13%' and a.c_physicalflag = b.c_deviceid and b.c_realityreturntime is null order by a.c_physicalflag asc";
		//查找预分析任务列表
		String	query1="select * from tmcloud_preanalyserecord"; //--0 未分析 1 分析中  2分析完成 3取消分析
		//查找工单表
//		String	query1="select * from tmcloud_hoterworkorderinfo";
		//查找订单表
//		String	query1="select * from tmcloud_hoteranreportorderinfo";
		if(Style==0){//查
			sqlquery ad1 = new sqlquery(JDriver,url,user,password,Style,query1);//创建查询SQL的对象;	
			Iterator<String> itt=ad1.RS.iterator();
			String Outfile	= "d:\\SQLResult.xls";
			try{
				OutputStream	ops = new FileOutputStream(new File(Outfile));
				HSSFWorkbook	wb	= new HSSFWorkbook();
				HSSFSheet		st	= wb.createSheet("SQLRes");
				HSSFRow			sr	= st.createRow(0);
				int i = 0;
				while(itt.hasNext()==true){
					if (i%ad1.ColCount==0 && i!=0){ 
						sr	= st.createRow(i/ad1.ColCount);
					}
					if (i/ad1.ColCount==0){
						sr.createCell(i%ad1.ColCount).setCellValue(ad1.ColName[i]);
					}else{
						sr.createCell(i%ad1.ColCount).setCellValue(itt.next());
					}
					i++;
					if(i>65534){
						wb.write(ops);
						ops.close();
						wb = new HSSFWorkbook();
						st = wb.createSheet();
						ops = new FileOutputStream(Outfile.split("\\W")[0]+":\\"+Outfile.split("\\W")[2]+(i/65535)+"."+Outfile.split("\\W")[3]);
						i = 0;
					}
				}
				wb.write(ops);
				ops.close(); 
			}catch(IOException e){
				e.printStackTrace();	
				System.out.println("将查询结果写入文件时报错");
			}
		}
		else if(Style==1){//增
			try{
				InputStreamReader	ips1	=new InputStreamReader(new FileInputStream(new File("d:\\test1.txt")));
				BufferedReader		br1		=new BufferedReader(ips1);
				while((Temp=br1.readLine())!=null){
					new sqlquery(JDriver,url,user,password,Style,Temp);
				}
				br1.close();
				ips1.close();
			}catch(IOException e){
				e.printStackTrace();	
				System.out.println("将文件写入数据库时报错");
			}
		}
		else if(Style==2){//删
			new sqlquery(JDriver,url,user,password,Style,query1);
		}
		else if(Style==3){//改
			new sqlquery(JDriver,url,user,password,Style,query1);
		}
	}
}
class sqlquery{//查增删改操作；
	String JDriver,url,user,password,query;
	int ColCount,i;
	String[] ColName;
	ArrayList<String> RS=new ArrayList<String>();
	sqlquery(String JDriver,String url,String user,String password,int i,String query){
		this.JDriver	= JDriver;
		this.url		= url;
		this.user		= user;
		this.password	= password;
		this.query		= query;
		this.i			= i;
		try{
			Class.forName(this.JDriver);//
			Connection			conn	=DriverManager.getConnection(this.url,this.user,this.password);			
			Statement			stmt	=conn.createStatement();
			if (this.i==0){
				ResultSet		rs		=stmt.executeQuery(this.query);
				ResultSetMetaData	rsmd=rs.getMetaData();
				this.ColCount			=rsmd.getColumnCount();
				this.ColName			=new String[this.ColCount];
				for(int k = 0;k<ColCount;k++){//遍历查询结果科目
					this.ColName[k] = rsmd.getColumnName(k+1);
				}
				while(rs.next()){
					for(int j=0;j<ColCount;j++){//遍历每行、每列的查询结果
						RS.add(rs.getString(rsmd.getColumnName(j+1)));
					}
				}
				rs.close();
			}
			else{
				stmt.executeUpdate(this.query);
				System.out.println("命令执行完成");
			}
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
}