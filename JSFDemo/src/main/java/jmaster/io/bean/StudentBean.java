package jmaster.io.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import jmaster.io.dao.StudentDao;
import jmaster.io.entity.Student;

@ManagedBean
public class StudentBean implements Serializable {
	private int id;
	private String name;
	private int age;
	private String homeAddress;
	StudentDao studentDao = new StudentDao();

	public StudentBean() {
	}

	public StudentBean(int id, String name, int age, String homeAddress) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.homeAddress = homeAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/// method
	public void createStudent() {
		Student student = new Student(id, name, age, homeAddress);
		studentDao.create(student);
	}

	public void deleteStudent() {
		studentDao.delete(id);
	}

	public void updateStudent() {
		Student student = new Student(id, name, age, homeAddress);
		studentDao.update(student);
	}

	public List<Student> getStudents() {
		// tim kiem
		return studentDao.search(name == null ? "" : name);
	}

	public Student getStudentById() {
		return studentDao.getId(id);
	}
}
