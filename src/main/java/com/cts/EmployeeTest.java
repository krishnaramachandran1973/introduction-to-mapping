package com.cts;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cts.entities.Department;
import com.cts.entities.Employee;
import com.cts.entities.ParkingSpace;
import com.cts.entities.Phone;
import com.cts.entities.Project;

public class EmployeeTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Department it = Department.builder().name("IT").build();

		ParkingSpace ps1 = ParkingSpace.builder().location("A-BLOCK").slot("1A").build();
		ParkingSpace ps2 = ParkingSpace.builder().location("A-BLOCK").slot("2A").build();

		Project banking = Project.builder().name("Banking").build();
		Project hc = Project.builder().name("Healthcare").build();

		Phone mobile = Phone.builder().number("87687687").build();
		Phone home = Phone.builder().number("3245324532").build();

		Employee krishna = Employee.builder().name("Krishna").salary(1000L).comments("Architect").department(it)
				.parkingSpace(ps1).projects(Arrays.asList(banking, hc)).phones(Arrays.asList(mobile, home)).build();

		Employee nishant = Employee.builder().name("Nishant").salary(1000L).comments("PL").department(it)
				.projects(Arrays.asList(banking, hc)).parkingSpace(ps2).build();

		banking.getEmployees().addAll(Arrays.asList(krishna, nishant));
		hc.getEmployees().addAll(Arrays.asList(krishna, nishant));

		ps1.setEmployee(krishna);
		ps2.setEmployee(nishant);

		it.getEmployees().addAll(Arrays.asList(krishna, nishant));

		em.persist(it);

		em.persist(ps1);
		em.persist(ps2);

		em.persist(banking);
		em.persist(hc);
		em.persist(mobile);
		em.persist(home);
		em.persist(krishna);
		em.persist(nishant);

		 transaction.commit();

		List<Employee> resultList = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		em.close();

		resultList.forEach(emp -> {
			System.out.println(emp);
			Department department = emp.getDepartment();
			System.out.println(department);
			emp.getProjects().forEach(proj -> System.out.println(proj));
		});

		emf.close();
	}

}
