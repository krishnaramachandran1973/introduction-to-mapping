package com.cts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cts.entities.Department;
import com.cts.entities.Employee;

public class EmployeeTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Department it = Department.builder().name("IT").build();
		Employee krishna = Employee.builder().name("Krishna").salary(1000L).comments("Architect").department(it)
				.build();
		Employee nishant = Employee.builder().name("Nishant").salary(1000L).comments("PL").department(it).build();

		em.persist(it);
		em.persist(krishna);
		em.persist(nishant);

		transaction.commit();
	}

}
