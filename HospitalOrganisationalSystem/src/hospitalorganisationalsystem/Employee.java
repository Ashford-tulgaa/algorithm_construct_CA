
package hospitalorganisationalsystem;

/**
 * Represents an employee in the hospital organisational system.
 * Stores the employee information read from the sample data file.
 */
public class Employee {

    // Employee personal information
    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    // Employment information
    private double salary;
    private String department;
    private String position;
    private String jobTitle;
    private String company;

    /**
     * Constructor: creates an Employee object using the supplied information.
     *
     * @param firstName Employee's first name
     * @param lastName Employee's last name
     * @param gender Employee's gender
     * @param email Employee's email address
     * @param salary Employee's salary
     * @param department Employee's department
     * @param position Employee's position level
     * @param jobTitle Employee's job title
     * @param company Employee's company
     */
    public Employee(String firstName, String lastName, String gender,
                    String email, double salary, String department,
                    String position, String jobTitle, String company) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    // Returns the employee's first name.
    public String getFirstName() {
        return firstName;
    }

    // Returns the employee's last name.
    public String getLastName() {
        return lastName;
    }

    // Returns the employee's full name.
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Returns the employee's gender.
    public String getGender() {
        return gender;
    }

    // Returns the employee's email address.
    public String getEmail() {
        return email;
    }

    // Returns the employee's salary.
    public double getSalary() {
        return salary;
    }

    // Returns the employee's department.
    public String getDepartment() {
        return department;
    }

    // Returns the employee's position level.
    public String getPosition() {
        return position;
    }

    // Returns the employee's job title.
    public String getJobTitle() {
        return jobTitle;
    }

    // Returns the employee's company.
    public String getCompany() {
        return company;
    }

    /**
     * Returns the employee information as readable text.
     * Useful when displaying employee records in the console.
     */
    @Override
    public String toString() {
        return "Name: " + getFullName()
                + "\nGender: " + gender
                + "\nEmail: " + email
                + "\nSalary: " + salary
                + "\nDepartment: " + department
                + "\nPosition: " + position
                + "\nJob Title: " + jobTitle
                + "\nCompany: " + company;
    }
}
