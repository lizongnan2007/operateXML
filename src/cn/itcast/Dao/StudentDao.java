package cn.itcast.Dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.itcast.domain.Student;
import cn.itcast.exception.StudentNotExitException;
import cn.itcast.utils.XmlUtils;

public class StudentDao {

	public void add(Student s) {

		try {
			Document document = XmlUtils.getDocument();

			// 创建出封装学生信息的标签
			Element student_tag = document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());

			// 创建用于封装学生姓名、所在地和成绩的标签
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");

			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(String.valueOf(s.getGrade()));

			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);

			// 把封装了信息的学生标签，挂到文档上
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);

			// 更新内存
			XmlUtils.write2Xml(document);

		} catch (Exception e) {
			throw new RuntimeException(e); // 抛出运行时异常，告知上一层该异常e
		}
	}

	public Student find(String examid) {
		try {
			Document document = XmlUtils.getDocument();
			NodeList list = document.getElementsByTagName("student");

			for (int i = 0; i < list.getLength(); i++) {
				Element student_tag = (Element) list.item(i);
				if (student_tag.getAttribute("examid").equals(examid)) {
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String name) throws StudentNotExitException {
		try {
			Document document = XmlUtils.getDocument();
			NodeList list = document.getElementsByTagName("name");
			for (int i = 0; i < list.getLength(); i++) {
				if (list.item(i).getTextContent().equals(name)) {
					list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					
					// 更新内存
					XmlUtils.write2Xml(document);
					return;
				}
			}
			throw new StudentNotExitException(name + " is not exit!");
		} catch (StudentNotExitException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
