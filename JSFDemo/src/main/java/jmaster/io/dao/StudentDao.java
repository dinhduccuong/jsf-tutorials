package jmaster.io.dao;

import java.util.List;

import org.hibernate.Session;

import jmaster.io.entity.Student;
import jmaster.io.utils.HibernateUtil;

public class StudentDao {

	public void create(Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}

	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(getId(id));
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Student> search(String name) {
		String hql = "SELECT c FROM Student c WHERE c.name LIKE :name";
		return HibernateUtil.getSessionFactory().openSession().createQuery(hql).setParameter("name", "%" + name + "%")
				.list();
	}

	public void update(Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}

	public Student getId(int id) {
		return (Student) HibernateUtil.getSessionFactory().openSession().get(Student.class, id);
	}

}
