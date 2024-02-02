package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class ManagerDAO {
    public static void assignManagerToDepartment(Session session){
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            System.out.println("Enter manager Id: ");
            Long managerId = scanner.nextLong();
            Manager manager = session.get(Manager.class, managerId);

            if (manager == null) {
                System.out.println("Manager not found.");
                return;
            }

            System.out.println("Enter department ID:");
            Long departmentId = scanner.nextLong();
            Department department = session.get(Department.class, departmentId);

            if (department == null) {
                System.out.println("Department not found.");
                return;
            }

            //Assign manager to department
            department.setManager(manager);
            manager.setManagedDepartment(department);

            session.saveOrUpdate(department);
            session.saveOrUpdate(manager);

            transaction.commit();
            System.out.println("Manager assigned to department successfully.");

        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

}
