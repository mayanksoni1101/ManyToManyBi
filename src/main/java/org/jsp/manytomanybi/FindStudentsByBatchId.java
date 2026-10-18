package org.jsp.manytomanybi;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindStudentsByBatchId {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select b.std from Batch b where b.batchCode = ?1");
		System.out.println("Enter Batch Code : ");
		q.setParameter(1, sc.next());
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
