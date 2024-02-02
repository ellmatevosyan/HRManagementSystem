package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static void initializeData(SessionFactory sessionFactory){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            //Inserting data for the Employee class
            List<Project> employeeProjects = new ArrayList<>();
            employeeProjects.add(new Project("ProjectA",LocalDate.of(2022,2,1),LocalDate.of(2022,5,1),10000L, null));
            employeeProjects.add(new Project("ProjectB", LocalDate.of(2023,6,18),LocalDate.of(2023,9,20),450000L,null));


            Employee employee1 = new Employee("John Doe", "john.doe@example.com",
                    "123-456-7890",
                    LocalDate.of(2022, 1, 1),
                    "Software Engineer",
                    employeeProjects);
            session.persist(employee1);

            Employee employee2 = new Employee("Jane Smith", "jane.smith@example.com", "987-654-3210", LocalDate.of(2023, 10, 13), "Designer", employeeProjects);
            session.persist(employee2);

            //Inserting data for the Manager class
            List<Project> managerProjects = new ArrayList<>();
            managerProjects.add(new Project("ProjectC",LocalDate.of(2023, 1, 1), LocalDate.of(2023, 6, 1), 15000L, null));
            Department managerDepartment = new Department("Engineering", "LocationA", null, null);

            Manager manager = new Manager("Jack Brown",
                    "jane.smith@example.com", "345-654-3210",
                    LocalDate.of(2023, 10, 12),
                    "Engineering Manager",
                    managerProjects,
                    managerDepartment);

            manager.setManagementLevel(ManagementLevel.MID_LEVEL);
            managerDepartment.setManager(manager);
            session.persist(manager);

            transaction.commit();
        }
    }
}
