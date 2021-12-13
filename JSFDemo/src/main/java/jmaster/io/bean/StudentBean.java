package jmaster.io.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jmaster.io.dao.StudentDao;
import jmaster.io.entity.Student;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	public String createStudent() {
		Student student = new Student(id, name, age, homeAddress);
		studentDao.create(student);
		return "/student/students.xhtml?faces-redirect=true";
	}

	public void deleteStudent(int id) {
		studentDao.delete(id);
	}

	public String updateStudent(Student student) {
		studentDao.update(student);

		return "/student/students.xhtml?faces-redirect=true";
	}

	public List<Student> getStudents() {
		// tim kiem
		return studentDao.search(name == null ? "" : name);
	}

	public String edit(int id) {
		Student student = studentDao.getId(id);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editStudent", student);
		return "/student/edit-student.xhtml?faces-redirect=true";
	}

	public void downloadReport() {
		try {
			HashMap<String, Object> mapParams = new HashMap<>();
			mapParams.put("name", "Test report");

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(getStudents());
			JasperPrint jasperPrint = JasperFillManager.fillReport(getFileFromResourceAsStream("report/student.jasper"),
					mapParams, beanCollectionDataSource);

			FacesContext facesContext = FacesContext.getCurrentInstance(); // Get the context ONCE
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		    ServletOutputStream servletOutputStream = response.getOutputStream();
			response.addHeader("Content-disposition", "attachment; filename=report.pdf");
			facesContext.responseComplete();

			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

			servletOutputStream.flush();
		    servletOutputStream.close();
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}

	// get a file from the resources folder
	// works everywhere, IDEA, unit test and JAR file.
	private InputStream getFileFromResourceAsStream(String fileName) {
		// The class loader that loaded the class
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}

	}
}
