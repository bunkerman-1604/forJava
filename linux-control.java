package forLinux;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;

public class forLinux {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String commands = "/home/sam/test.sh";
		writeShell("echo \"I\'m new hand !\"");
        
		try {
			 Process process = Runtime.getRuntime().exec(commands);
	            InputStreamReader ir = new InputStreamReader(process.getInputStream());
	            LineNumberReader input = new LineNumberReader(ir);
	            String line;
	            while((line = input.readLine()) != null)
	                System.out.println(line);
	            input.close();
	            ir.close();
		} catch (IOException e) {e.printStackTrace();
		}

	}

	private static void writeShell(String content) {
		try {
			OutputStreamWriter osw	= new OutputStreamWriter(new FileOutputStream("/home/sam/test1.sh"));
			osw.write(content);
			osw.close();
		} catch (Exception e) {e.printStackTrace();
		}
	}

}
