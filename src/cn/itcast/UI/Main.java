package cn.itcast.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.itcast.Dao.StudentDao;
import cn.itcast.domain.Student;
import cn.itcast.exception.StudentNotExitException;

public class Main {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			System.out.println("Add(a) Delete(d) Find(f)");
			System.out.print("请输入操作类型：");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String type = br.readLine();
			
			if("a".equals(type)){
				System.out.print("Pls input student examid:");
				String examid = br.readLine();
				
				System.out.print("Pls input student idcard:");
				String idcard = br.readLine();
				
				System.out.print("Pls input student name:");
				String name = br.readLine();
				
				System.out.print("Pls input student location:");
				String location = br.readLine();
				
				System.out.print("Pls input student grade:");
				String grade = br.readLine();
				
				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(s);
				
				System.out.println("Add success!");
				
			}else if("d".equals(type)){
				System.out.println("Pls input student name to delete him/her:");
				String name = br.readLine();
				
				try{
				StudentDao dao = new StudentDao();
				dao.delete(name);
				
				System.out.println("Delete success!");
				}catch(StudentNotExitException e){
					System.out.println("The student isn't exsit!");
				}
			}else if("f".equals(type)){
				
			}else{
				System.out.println("Not supported operation!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sorry, error appears!");
		}
	}

}
