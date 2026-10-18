package org.jsp.manytomanybi;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindBatchByStudentPhoneAndName {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select s.batch from Student s where s.phone = ?1 and s.name = ?2");
		System.out.println("Enter Student Phone : ");
		q.setParameter(1, sc.nextLong());
		System.out.println("Enter Student Name : ");
		q.setParameter(2, sc.next());
		List<Batch> bl = q.getResultList();
		if(!bl.isEmpty()) {
			bl.forEach(System.out::println);
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
