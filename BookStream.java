package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BookStream {
	private Scanner scan;
	private Map<String, Phone> phoneBookMap;
	
	public BookStream() {			//초기화 작업
		scan = new Scanner(System.in);
		phoneBookMap =new HashMap<>();		
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneBookStart() {
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : insert();		// 등록
					break;
				case 2 : update();		// 수정
					break;
				case 3 : delete();		// 삭제
					break;
				case 4 : search();		// 검색
					break;
				case 5 : displayAll();	// 전체 출력
					break;
				case 0 :
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	/**
	 * 전체 자료를 출력하는 메서드
	 */
	private void displayAll() {
		Set<String> keySet = phoneBookMap.keySet();
		System.out.println("====================================================");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("====================================================");
		
		if(keySet.size() ==0) {
			System.out.println("등록된 전화번호 정보가 존재하지 않습니다.");
		}else {
			Iterator<String> it =keySet.iterator();
			int cnt =0;
			while(it.hasNext()) {
				cnt++;
				String name = it.next();		//키값을 얻는다.
				Phone p =phoneBookMap.get(name);	//value값을 얻는다.
				
				System.out.println(" " + cnt + "\t" + p.getName() + " \t" + p.getTel() + " \t"+ p.getAddr());
			}
		}
		System.out.println("====================================================");
		System.out.println("출력 완료...");
		
	}
	
	/**
	 * 이름을 이용한 전화번호 정보를 검색하는 메서드
	 */
	
	private void search()  {
		FileReader file;
		Object obj = null;
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.print("이름 >>");
		String name =scan.next();
		
		try {
		 ObjectInputStream	ois=
		new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream("e:\\D_Other\\phone\\"+ phoneBookMap.get("e:/D_Other/phone/"+ name +".ser"))));
				System.out.println("쓰기작업완료!");
				obj =ois.readObject();
				phoneBookMap.put(name, (Phone) obj);
				System.out.println(((Map<String, Phone>) obj).size());
				System.out.println();
				System.out.println("이름 : " + phoneBookMap.get(name).getName());
				System.out.println("전화번호: " + phoneBookMap.get(name).getTel());
				System.out.println("주소 : " + phoneBookMap.get(name).getAddr());
		ois.close();
		
		
		}catch(ClassNotFoundException e) {
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
		}catch(IOException e) {
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
		}
		//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
		
		
		
	}
	
		
//		
//		Phone p =phoneBookMap.get(name);
//		if(p ==null) {
//			System.out.println(name + "씨 전화번호 정보가 없습니다.");
//			return;
//		}else {
//			System.out.println(name + "씨의 전화번호 정보");
//			System.out.println("이	름  : " + p.getName());
//			System.out.println("전화번호  : " + p.getTel());
//			System.out.println("주	소  : " + p.getAddr());
//		}
//		System.out.println("검색 작업 완료.");
	/**
	 * 전화번호 정보를 삭제하는 메서드
	 */
	
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.println("이름 >> ");
		String name =scan.next();
		
		File file = new File("e:\\D_Other\\phone\\"+ name +".ser");
		if(file.exists()) {
			if(file.delete()){
				System.out.println("파일삭제 성공");
			}else{
				System.out.println("파일삭제 실패");
			}
		}else{
			System.out.println("파일이 존재하지 않습니다.");
		}
		
	}
		
		// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면
		//					null을 반환한다.
//		if(phoneBookMap.remove(name) == null) {
//			System.out.println(name + "씨는 등록된 사람이 아닙니다.");
//			return;
//		}else {
//			System.out.println(name + "씨 정보를 삭제했습니다.");
//		}
//		System.out.println("삭제 작업 완료 ...");
		

	/**
	 * 전화번호 정보를 수정하는 메서드
	 */
	private void update() {
		ObjectOutputStream oos;
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요");
		System.out.print("이름>> ");
		String name = scan.next();
		try {
//		oos = new ObjectOutputStream(
//				new BufferedOutputStream(
//						new FileOutputStream("e:/D_Other/phone/"+ name +".ser")));
//		//쓰기 작업
//		oos.writeObject(phoneBookMap);	//직렬화
//		File file = oos.;
		if(phoneBookMap.get(name) == null) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		System.out.print("전화번호>> ");
		String tel =scan.next();
		
		System.out.println("주소>> ");
		scan.nextLine();	//버퍼에 남아있을지 모를 엔터키값 제거
		
		String addr =scan.nextLine();
		Phone p = new Phone(name, tel, addr);
		phoneBookMap.put(name, p);
//		
//		
//		
//		System.out.println("쓰기 작업 완료");
//		oos.close();
//		} catch (IOException e) {
		} catch(NullPointerException e) {
			System.out.println("찾는 값없습니다.");
		}
		
		//값은 key값에 데이터를 저장하면 value값이 변경된다.
		System.out.println(name + "씨 정보 수정 완료...");
		
	}
	/**
	 * 새로운 전화번호 정보를 등록하는 메서드
	 * 이미 등록된 사람은 등록되지 않는다.
	 */
	
	private void insert()  {
		
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
		System.out.print("이름 >> ");
		String name =scan.next();
		
		// 이미 등록된 사람인지 검사 
		// get()메서드로 값을 가져올때 가져올 자료가 없으면 null을 반환한다.
		
		if(phoneBookMap.get(name) != null) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return; 											//메서드 종료
		}
		System.out.println("전화번호>> ");	
		String tel =scan.next();
		
		// 입력버퍼에 남아 있는 엔터키까지 읽어와버리는 역할을 수행함.
		 // next() 호출 후 nextLine()호출시 혹시 남아있을지 모를 쓰레기 값을 위해
		 //	한번 호출한다.
		System.out.println("주소>> ");
		scan.nextLine();	 
		
		String addr =scan.nextLine();
		
		phoneBookMap.put(name, new Phone(name, tel, addr));		// Phone 객체를 만들어 값을 집어 넣는다.
		System.out.println(name + "씨 등록 완료...");
		FileOutputStream file;
		try {
			file = new FileOutputStream("e:/D_Other/phone/"+ name +".ser");
		ObjectOutputStream obInput = new ObjectOutputStream(file);
		
		obInput.writeObject(phoneBookMap);
		
		ObjectInputStream ois =
				new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream("e:/D_Other/phone/"+ name +".ser")));
		obInput.close();
		System.out.println("쓰기작업완료!");
	Object obj = null;
	
		obj =ois.readObject();
		//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
		
		 Map<String, Phone> mem = (Map<String, Phone>) obj;
		System.out.println("이름 : " + mem.get(name).getName());
		System.out.println("전화번호: " + mem.get(name).getTel());
		System.out.println("주소 : " + mem.get(name).getAddr());
		
	
	ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e ) {
			
		}
		
	}
	
	public static void main(String[] args) {
		new BookStream().phoneBookStart();
	}
	
}
class Phone implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5422429127921791343L;
	private String name; //이름
	private String tel;	 // 전화번호
	private String addr; //주소
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Phone [name = " + name + ", tel = " + tel + ", addr = " +addr + "]";
	}
}
