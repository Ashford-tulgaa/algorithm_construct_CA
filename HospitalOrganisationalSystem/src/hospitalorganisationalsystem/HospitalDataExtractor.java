package hospitalorganisationalsystem;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Extracts departments and management types from loaded employee records.
 */
public class HospitalDataExtractor {

    /**
     * Extracts unique department names from the employee list.
     *
     * @param employees List of loaded employees.
     * @return List of unique departments.
     */
    public static ArrayList<Department> extractDepartments(
            ArrayList<Employee> employees) {

        // LinkedHashSet prevents duplicates and preserves the original order.
        LinkedHashSet<String> uniqueDepartments = new LinkedHashSet<>();

        // Read the department of every employee.
        for (Employee employee : employees) {
            String department = employee.getDepartment().trim();

            // Ignore empty department values.
            if (!department.isEmpty()) {
                uniqueDepartments.add(department);
            }
        }

        // Convert the unique department names into Department objects.
        ArrayList<Department> departments = new ArrayList<>();

        for (String departmentName : uniqueDepartments) {
            departments.add(new Department(departmentName));
        }

        return departments;
    }

    /**
     * Extracts unique management types from employee job titles.
     *
     * @param employees List of loaded employees.
     * @return List of unique management types.
     */
    public static ArrayList<Manager> extractManagers(
            ArrayList<Employee> employees) {

        // LinkedHashSet prevents duplicate management types.
        LinkedHashSet<String> uniqueManagers = new LinkedHashSet<>();

        // Read the job title of every employee.
        for (Employee employee : employees) {
            String jobTitle = employee.getJobTitle().trim();

            // Ignore empty job titles.
            if (!jobTitle.isEmpty()) {
                uniqueManagers.add(jobTitle);
            }
        }

        // Convert the unique job titles into Manager objects.
        ArrayList<Manager> managers = new ArrayList<>();

        for (String managerType : uniqueManagers) {
            managers.add(new Manager(managerType));
        }

        return managers;
    }
}
