package com.lab.orm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.lab.orm.models.Address;
import com.lab.orm.models.AddressId;
import com.lab.orm.models.AnnualLeave;
import com.lab.orm.models.Benefit;
import com.lab.orm.models.Employee;
import com.lab.orm.models.LeaveType;
import com.lab.orm.models.Name;
import com.lab.orm.models.SickLeave;
import com.lab.orm.models.User;
import com.lab.orm.repo.EmployeeRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrmApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	EmployeeRepo eRepo;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	 void testCache() throws Exception {
	 	System.out.println("----Not loaded, require query---");
		loadEntity(1);
	}

	@Test
	@Transactional // will handle session automatically
	public void testFetch() throws Exception {
		int id = 1;

		System.out.println("-- Loading entities --");
		Employee employee = em.find(Employee.class, id);
		String name = employee.getName().getFname();
		
		// Address is lazy load, so will not be queried unless its info is needed
		System.out.println("-- Loading addresses --");
		String city = employee.getAddresses().iterator().next().getId().getCity();
		// Benefits are lazy load
		System.out.println("-- Loading benefits --");
		String title = employee.getBenefits().iterator().next().getTitle();
		// User is lazy load
		System.out.println("-- Loading user --");
		String username = employee.getUser().getUsername();

		System.out.println("Employee loaded: " + name);
		Assertions.assertEquals("Chaklam", name);

		System.out.println("City Address loaded: " + city);
		Assertions.assertEquals("Bangkok", city);

		System.out.println("Benefits loaded: " + title);
		Assertions.assertEquals("Benefit Free Coffee", title);

		System.out.println("User loaded: " + username);
		Assertions.assertEquals("chaklam", username);

	}


	@Test
	@Transactional
	void testCascadePersist() throws Exception {
		Employee employee = new Employee();
		Name name = new Name("Peter", "Shawn", "");
		employee.setName(name);
		employee.setAge(35);

		// add user
		User u = new User();
		u.setUsername("secondpeter");

		u.setEmp(employee);

		// add address
		Address add = new Address();
		AddressId addId = new AddressId();
		addId.setCity("Bangkok");
		addId.setHouseNo("33/9");
		addId.setStreetAddress("Fashion Island");
		addId.setZipcode("10304");
		add.setId(addId);
		add.setEmp(employee);
		List<Address> adds = new ArrayList<Address>();
		adds.add(add);
		employee.setAddresses(adds);

		// add benefits
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee);
		Benefit benefit = new Benefit("Free Lunch", employees);
		Set<Benefit> benefits = new HashSet<Benefit>();
		benefits.add(benefit);
		employee.setBenefits(benefits);

		em.persist(employee); // this means that em will track all changes once trans finish
		
		// not empty addresses and benefits	because persisted
		Assertions.assertNotNull(em.find(Address.class, addId));
		Assertions.assertNotNull(em.find(Benefit.class, 3));
	}

	@Test
	@Transactional
	 void testCascadeRemove() throws Exception {	
		int id = 1;
		Employee employee = em.find(Employee.class, id);
		List<Address> adds = employee.getAddresses();

		System.out.println("Employee loaded: " + employee.getName().getFname());
		em.remove(employee);

		Assertions.assertNull(em.find(Employee.class, id));

		// Addresses also null because cascade deleted
		for (Address a : adds) {
			Assertions.assertNull(em.find(Address.class, a.getId()));
		}
	}

	@Test
	@Transactional
	void testCreateLeave() throws Exception{
		int id = 1;
		LeaveType type = LeaveType.SICK;

		LocalDate start = LocalDate.of(2018, 2, 13);
		LocalDate end = LocalDate.of(2018, 2, 15);
		Employee emp = em.find(Employee.class, id);
		switch (type) {
		case SICK:
			SickLeave sl = new SickLeave(emp, false, "Flu", start, end);
			em.persist(sl);
			break;
		case ANNUAL:
			AnnualLeave al = new AnnualLeave(emp, false, "Kids affairs", start, end);
			em.persist(al);
			break;

		}

		Assertions.assertEquals(em.find(SickLeave.class, 1).getRemarks(), "Flu");
		Assertions.assertEquals(em.find(SickLeave.class, 1).getEmp().getId(), em.find(Employee.class, id).getId());
	}

	private void loadEntity(int id) {
		System.out.println("-- Loading entities --");
		Employee employee = em.find(Employee.class, id);
		System.out.println("Employee loaded: " + employee.getName().getFname());
	}

}
