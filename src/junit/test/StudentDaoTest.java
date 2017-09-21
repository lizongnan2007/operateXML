package junit.test;

import org.junit.Test;

import cn.itcast.Dao.StudentDao;
import cn.itcast.domain.Student;
import cn.itcast.exception.StudentNotExitException;

public class StudentDaoTest {

	@Test
	public void testAdd(){
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExamid("121");
		s.setGrade(89);
		s.setIdcard("121");
		s.setLocation("Beijing");
		s.setName("aa");
		dao.add(s);
	}
	
	@Test
	public void testFind(){
		StudentDao dao = new StudentDao();
		Student s = dao.find("121");
		System.out.println(s.getLocation());
	}
	
	@Test
	public void testDelete(){
		StudentDao dao = new StudentDao();
		try {
			dao.delete("aa");
		} catch (StudentNotExitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
