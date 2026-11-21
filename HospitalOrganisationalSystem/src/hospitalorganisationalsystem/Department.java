
package hospitalorganisationalsystem;

/**
 * Represents a department within the hospital organisation.
 * Each department has a name.
 */
public class Department {

    // Stores the department name.
    private String departmentName;

    /**
     * Creates a Department object.
     *
     * @param departmentName Name of the department.
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Returns the department name.
     *
     * @return Department name.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Returns the department name as readable text.
     *
     * @return Department name.
     */
    @Override
    public String toString() {
        return departmentName;
    }
}
