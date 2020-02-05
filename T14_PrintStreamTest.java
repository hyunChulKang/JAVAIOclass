package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 프린터기능 제공 보조 스트림
 * @author PC-06
 *
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		FileOutputStream fout = new FileOutputStream("E:/D_Other/print.txt");
		FileOutputStream fout2 = new FileOutputStream("E:/D_Other/print2.txt");
		
		PrintStream out = new PrintStream(fout);
		out.print("안녕하세요. PrintStream 입니다. \r\n");
		out.println("안녕하세요. PrintStream 입니다.2");
		out.println("안녕하세요. PrintStream 입니다.3");
		
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8")); 
		writer.print("안녕하세요. PrintWriter 입니다 \r\n");
		writer.println("안녕하세요. PrintWriter 입니다 2");
		writer.println("안녕하세요. PrintWriter 입니다 3");
		
		writer.close();
	}
}
