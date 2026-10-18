package org.jsp.manytomanybi;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindStudentWhosePerGreater35 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select s from Student s where marks > 35");
		System.out.println("Student Having More than 35 Percent : ");
		List<Student> sl = q.getResultList();
		if(!sl.isEmpty()) {
			sl.forEach(System.out::println);
		}
		else {
			System.err.println("No Record Found !!!");
		}
		try {}
		finally {
			sc.close();
			em.clear();
			emf.close();
		}
	}
}
