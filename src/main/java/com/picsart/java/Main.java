package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        DataInitializer.initializeData(sessionFactory);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Create new department.");
            System.out.println("2. Delete a department.");
            System.out.println("3. Update the department");
            System.out.println("4. Assign and reassign employees to departments.");
            System.out.println("5. Create new project.");
            System.out.println("6. Delete a project.");
            System.out.println("7. Update the project");
            System.out.println("8. Assign and reassign employees to projects.");
            System.out.println("9. Assign and reassign managers to departments.");
            System.out.println("10. Manage managerial details.");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> DepartmentDAO.createDepartment(session);
                case 2 -> DepartmentDAO.deleteDepartment(session);
                case 3 -> DepartmentDAO.updateDepartment(session);
                case 4 -> DepartmentDAO.assignEmployeesToDepartment(session);
                case 5 -> ProjectDAO.createProject(session);
                case 6 -> ProjectDAO.deleteProject(session);
                case 7 -> ProjectDAO.updateProject(session);
                case 8 -> ProjectDAO.assignEmployeeToProject(session);
                case 9 -> ManagerDAO.assignManagerToDepartment(session);
                case 11 -> exit = true;
                default -> System.out.println("Invalid choice. You can select the numbers from 1 to 11. Please try again!");
            }
        }
        System.out.println("Exiting the application. Goodbye!");
        sessionFactory.close();

    }
}