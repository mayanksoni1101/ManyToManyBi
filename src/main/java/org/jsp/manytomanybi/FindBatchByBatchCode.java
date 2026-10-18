package org.jsp.manytomanybi;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindBatchByBatchCode {
	public static void main(String[] args) {
		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select b from Batch b where batchCode = ?1");
		System.out.println("Enter Batch Code : ");
		q.setParameter(1, sc.next());
		try {
			Batch b = (Batch) q.getSingleResult();
			System.out.println(b);
		}
		catch(NoResultException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
			em.clear();
			emf.close();
		}
	}
}
