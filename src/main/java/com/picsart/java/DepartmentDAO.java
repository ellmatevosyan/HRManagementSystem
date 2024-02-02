package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DepartmentDAO {
    //the method creates a new department
    public static void createDepartment(Session session){
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try{
            transaction = session.beginTransaction();
            System.out.println("Enter the department name ");
            String departmentName = scanner.nextLine();
            System.out.println("Enter location of department ");
            String location = scanner.nextLine();
            Department department = new Department(departmentName,location,null,null);
            System.out.println("New department was created successfully!");
            session.persist(department);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    //the method deletes a department by ID
    public static void deleteDepartment(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            try {
                System.out.println("Enter department Id which you want to delete ");
                Long departmentId = scanner.nextLong();
                Department department = session.get(Department.class,departmentId);
                if(department != null){
                    session.remove(department);
                    System.out.println("Department was deleted successfully!");
                    exit = true;
                }else{
                    System.out.println("Department Id is invalid");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        transaction.commit();
    }

    //the method updates department's name and location
    public static  void updateDepartment(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Department department = null;
        while(!exit){
            try{
                System.out.println("Enter the department Id which you want to update.");
                Long departmentId = scanner.nextLong();
                department = session.get(Department.class,departmentId);
                if(department != null){
                    exit = true;
                }else {
                    System.out.println("Department Id is invalid, try again!");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter new name for department");
        String newDepartmentName = scanner.nextLine();
        System.out.println("Enter new location for department");
        String newLocation = scanner.nextLine();
        department.setDepartmentName(newDepartmentName);
        department.setLocation(newLocation);
        session.persist(department);
        transaction.commit();
        System.out.println("The department was updated successfully!");
    }

    public static void assignEmployeesToDepartment(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the department Id to assign employees: ");
            Long departmentId = scanner.nextLong();

            Department department = session.get(Department.class,departmentId);
            if(department != null){
                //Display current employees in the department
                List<Employee> currentEmployees = department.getEmployees();
                System.out.println("Current employees in department: ");
                for(Employee employee : currentEmployees){
                    System.out.println("Employee ID: " + employee.getEmployeeId() + ", Name: " + employee.getName());
                }

                System.out.println("Enter employee IDs to assign (comma-separated):");
                String employeeIdsInput = scanner.next();
                String[] employeeIdsArray = employeeIdsInput.split(",");

                //Assign employees to the department
                for(String employeeIdStr : employeeIdsArray){
                    Long employeeId = Long.parseLong(employeeIdStr.trim());
                    Employee employee = session.get(Employee.class,employeeId);
                    if(employeeId != null){
                        employee.getDepartments().add(department);
                        System.out.println("Employee with Id " + employeeId + " is assigned to the department");
                    }else {
                        System.out.println("Employee not found with Id: " + employeeId);
                    }
                }
                transaction.commit();
            }else{
                System.out.println("Department not found with ID: " + departmentId);
            }
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
