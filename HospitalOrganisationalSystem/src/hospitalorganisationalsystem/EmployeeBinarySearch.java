
package hospitalorganisationalsystem;

import java.util.ArrayList;

/**
 * Searches for employees using a recursive Binary Search algorithm.
 * The employee list must already be sorted alphabetically.
 */
public class EmployeeBinarySearch {

    /**
     * Starts the recursive binary search.
     *
     * @param employees Alphabetically sorted employee list.
     * @param searchName Full name of the employee to find.
     * @return The matching Employee object, or null if not found.
     */
    public static Employee search(
            ArrayList<Employee> employees, String searchName) {

        // Start searching from the first to the last employee.
        return binarySearch(
                employees, searchName.trim(), 0, employees.size() - 1);
    }

    /**
     * Recursively searches for an employee by full name.
     *
     * @param employees Sorted employee list.
     * @param searchName Name being searched for.
     * @param left Starting index.
     * @param right Ending index.
     * @return Matching Employee, or null if not found.
     */
    private static Employee binarySearch(
            ArrayList<Employee> employees,
            String searchName,
            int left,
            int right) {

        // The employee was not found if no search range remains.
        if (left > right) {
            return null;
        }

        // Find the middle position of the current search range.
        int middle = left + (right - left) / 2;

        // Get the employee at the middle position.
        Employee middleEmployee = employees.get(middle);

        // Compare the middle employee's name with the search name.
        int comparison = middleEmployee.getFullName()
                .compareToIgnoreCase(searchName);

        // Return the employee if the names match.
        if (comparison == 0) {
            return middleEmployee;
        }

        // Search the left half if the search name comes first.
        if (comparison > 0) {
            return binarySearch(
                    employees, searchName, left, middle - 1);
        }

        // Otherwise, search the right half.
        return binarySearch(
                employees, searchName, middle + 1, right);
    }
}
