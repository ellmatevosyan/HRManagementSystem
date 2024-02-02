package com.picsart.java;

import jakarta.persistence.Cacheable;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue("Manager")
public class Manager extends Employee {
    @OneToOne
    private Department managedDepartment;
    private ManagementLevel managementLevel;

    public Manager(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public Manager(String name, String email, String phoneNumber, LocalDate hireDate, String jobTitle, List<Project> projects, Department managedDepartment) {
        super(name, email, phoneNumber, hireDate, jobTitle, projects);
        this.managedDepartment = managedDepartment;
    }

    public Department getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public ManagementLevel getManagementLevel() {
        return managementLevel;
    }

    public void setManagementLevel(ManagementLevel managementLevel) {
        this.managementLevel = managementLevel;
    }
}



