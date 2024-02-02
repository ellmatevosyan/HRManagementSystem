package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProjectDAO {
    //the method creates new project
    public static void createProject(Session session){
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();

            System.out.println("Enter project name:");
            String projectName = scanner.nextLine();

            System.out.println("Enter project start date (yyyy-MM-dd):");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Enter project end date (yyyy-MM-dd):");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Enter project budget:");
            Long budget = Long.parseLong(scanner.nextLine());

            Project newProject = new Project(projectName, startDate, endDate, budget, null);

            session.persist(newProject);

            transaction.commit();
            System.out.println("Project created successfully!");
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    //the method deletes the project
    public static void deleteProject(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            try{
                System.out.println("Enter the project Id which you want to delete ");
                Long projectId = scanner.nextLong();
                Project project = session.get(Project.class,projectId);
                if(projectId != null){
                    session.remove(project);
                    System.out.println("The project is deleted successflly!");
                    exit = true;
                }else {
                    System.out.println("Invalid project Id. Please try again!");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        transaction.commit();
    }

    //the method updates the project name, endDate and budget
    public static void updateProject(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;
        Project project = null;
        while (!exit){
            try{
                System.out.println("Enter the project Id which you want to update ");
                Long projectId = scanner.nextLong();
                project = session.get(Project.class,projectId);
                if(projectId != null){
                    exit = true;
                }else {
                    System.out.println("Invalid project Id. Please try again!");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter the new name for the project: ");
        String newName = scanner.nextLine();
        System.out.println("Enter the updated end date (yyyy-MM-dd): ");
        LocalDate newEndDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter the updated project budget: ");
        Long newBudget = scanner.nextLong();
        project.setProjectName(newName);
        project.setEndDate(newEndDate);
        project.setBudget(newBudget);
        session.persist(project);
        transaction.commit();
        System.out.println("The project was updated successfully!");
    }

    //the method assigns the employee to the project
    public static void assignEmployeeToProject(Session session){
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try{
            transaction = session.beginTransaction();

            //Display a list of available employees
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            System.out.println("Available employees: ");
            for (Employee employee : employees){
                System.out.println("Employee Id: " + employee.getEmployeeId() + ", Name: " + employee.getName());
            }

            System.out.println("Enter the Employee Id to assign to project: ");
            Long employeeId = scanner.nextLong();
            Employee selectedEmployee = session.get(Employee.class, employeeId);

            //Display a list of available projects
            List<Project> projects = session.createQuery("FROM Project", Project.class).list();
            System.out.println("Available Projects:");
            for (Project project : projects) {
                System.out.println("Project Id: " + project.getProjectId() + ", Name: " + project.getProjectName());
            }

            System.out.println("Enter the Project ID to assign/reassign:");
            Long projectId = Long.parseLong(scanner.nextLine());
            Project selectedProject = session.get(Project.class, projectId);

            if (selectedEmployee != null && selectedProject != null) {
                selectedEmployee.getProjects().add(selectedProject);
                selectedProject.getEmployees().add(selectedEmployee);
                System.out.println("Employee assigned to the project successfully!");
            } else {
                System.out.println("Invalid Employee or Project ID. Assignment failed.");
            }

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
