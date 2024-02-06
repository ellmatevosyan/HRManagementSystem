# HR Management System
## Project Overview
This project is a Java-based Human Resources (HR) Management System implemented using Hibernate for Object-Relational Mapping (ORM). The system emphasizes demonstrating 
Hibernate’s advanced features such as inheritance, many-to-many relationships, and second-level caching. It includes a Command-Line Interface (CLI) for user interaction with the application.

## Prerequisites
Before running the application, ensure you have the following:
Java Development Kit (JDK) installed
PostgreSQL database installed and running
JDBC driver for PostgreSQL 

## Setup
### Set up the database:
Create a PostgreSQL database named management_system.
### Update the database connection details:
Open hibernate.cfg.xml
Modify the URL, USERNAME, and PASSWORD fields with your PostgreSQL credentials.

## Technical Specifications
### Entities and Fields
Employee:
Basic Fields: employeeId, name, email, phoneNumber, hireDate, jobTitle
Manager (Inherits from Employee):
Additional Fields: managedDepartment, managementLevel (enum: TOP_LEVEL, MID_LEVEL, FIRST_LINE)
Department:
Basic Fields: departmentId, departmentName, location, departmentHead
Project:
Basic Fields: projectId, projectName, startDate, endDate, budget
### Entity Relationships
Department-Employee: One-to-Many
Employee-Project: Many-to-Many (Employees can work on multiple projects and projects can have multiple employees)
Manager-managedDepartment: One-to-One
### Functionalities
Department Management:
Create, update, and delete departments.
Assign and reassign employees to departments.
Project Management:
Create, update, and delete projects.
Assign and reassign employees to projects.
Managerial Oversight:
Assign and reassign managers to departments.
Manage managerial details like subordinates and management level.
