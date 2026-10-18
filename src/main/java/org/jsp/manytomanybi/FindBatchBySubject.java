package org.jsp.manytomanybi;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindBatchBySubject {
	public static void main(String[] args) {


		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select b from Batch b where subject = ?1");
		System.out.println("Enter Subject : ");
		q.setParameter(1, sc.next());
		@SuppressWarnings("unchecked")
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
