package hospitalorganisationalsystem;

import java.util.ArrayList;

/**
 * Sorts employee records alphabetically using the Merge Sort algorithm.
 */
public class EmployeeMergeSort {

    /**
     * Starts the recursive sorting process.
     *
     * @param employees List of employees to sort.
     * @return A new alphabetically sorted employee list.
     */
    public static ArrayList<Employee> sort(
            ArrayList<Employee> employees) {

        // Create a separate list so the original list remains unchanged.
        ArrayList<Employee> sortedEmployees =
                new ArrayList<>(employees);

        // Start recursive Merge Sort.
        mergeSort(sortedEmployees, 0, sortedEmployees.size() - 1);

        return sortedEmployees;
    }

    /**
     * Divides the employee list into smaller sections recursively.
     *
     * @param employees Employee list being sorted.
     * @param left Starting index.
     * @param right Ending index.
     */
    private static void mergeSort(
            ArrayList<Employee> employees, int left, int right) {

        // Stop when the section contains one or zero employees.
        if (left >= right) {
            return;
        }

        // Find the middle index of the current section.
        int middle = left + (right - left) / 2;

        // Recursively sort the left half.
        mergeSort(employees, left, middle);

        // Recursively sort the right half.
        mergeSort(employees, middle + 1, right);

        // Combine the two sorted halves.
        merge(employees, left, middle, right);
    }

    /**
     * Combines two sorted sections into one sorted section.
     *
     * @param employees Employee list being sorted.
     * @param left Starting index.
     * @param middle Middle index.
     * @param right Ending index.
     */
    private static void merge(
            ArrayList<Employee> employees,
            int left, int middle, int right) {

        // Create temporary lists for the left and right sections.
        ArrayList<Employee> leftList = new ArrayList<>();
        ArrayList<Employee> rightList = new ArrayList<>();

        // Copy employees from the left section.
        for (int i = left; i <= middle; i++) {
            leftList.add(employees.get(i));
        }

        // Copy employees from the right section.
        for (int i = middle + 1; i <= right; i++) {
            rightList.add(employees.get(i));
        }

        int i = 0;
        int j = 0;
        int k = left;

        // Compare names and merge them in alphabetical order.
        while (i < leftList.size() && j < rightList.size()) {

            String leftName =
                    leftList.get(i).getFullName();

            String rightName =
                    rightList.get(j).getFullName();

            if (leftName.compareToIgnoreCase(rightName) <= 0) {
                employees.set(k, leftList.get(i));
                i++;
            } else {
                employees.set(k, rightList.get(j));
                j++;
            }

            k++;
        }

        // Copy any remaining employees from the left section.
        while (i < leftList.size()) {
            employees.set(k, leftList.get(i));
            i++;
            k++;
        }

        // Copy any remaining employees from the right section.
        while (j < rightList.size()) {
            employees.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
