package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Temp {
	public static void main(String[] args) throws IOException {
		FileInputStream fileinput= null;
		FileOutputStream fileoutput= null;
		
		File temp1 = new File("e:/D_Other/Tulips.jpg");
		fileinput = new FileInputStream(temp1);
		fileoutput = new FileOutputStream(new File("e:/D_Other/복사본_Tulips.jpg"));
		int c;
		while((c = fileinput.read()) != -1) {
			//읽어온 자료 출력하기
			
		}
		fileoutput.close();
		fileinput.close();
	}
}
