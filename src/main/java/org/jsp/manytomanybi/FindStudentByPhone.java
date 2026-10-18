package org.jsp.manytomanybi;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindStudentByPhone {
	public static void main(String[] args) {

		EntityManagerFactory emf  = Persistence.createEntityManagerFactory("M15");
		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);
		Query q = em.createQuery("select s from Student s where s.phone = ?1");
		System.out.println("Enter Student Phone : ");
		q.setParameter(1, sc.nextLong());
		try {
			Student s = (Student) q.getSingleResult();
			System.out.println(s);
		}
		catch(NoResultException e) {
			System.err.println("No Record Found !!!");
		}
		finally {
			sc.close();
			em.clear();
			emf.close();
		}
	
	}
}
