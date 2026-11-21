
package hospitalorganisationalsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles administrator input when adding a new employee.
 * Validates the employee name, management type and department.
 */
public class EmployeeInputHandler {

    /**
     * Collects and validates information for a new employee.
     *
     * @param scanner Scanner used for administrator input.
     * @param managers Available management types.
     * @param departments Available departments.
     * @return A new Employee object.
     */
    public static Employee createEmployee(
            Scanner scanner,
            ArrayList<Manager> managers,
            ArrayList<Department> departments) {

        System.out.println();
        System.out.println("============================================================");
        System.out.println("                  ADD NEW EMPLOYEE");
        System.out.println("============================================================");

        // Ask for the employee's full name.
        String fullName;

        while (true) {

            System.out.print("Enter employee full name: ");
            fullName = scanner.nextLine().trim();

            // Require at least a first name and a last name.
            if (fullName.matches(
                    "[\\p{L}][\\p{L}'’-]*(\\s+[\\p{L}][\\p{L}'’-]*)+")) {
                break;
            }

            System.out.println(
                    "Invalid name. Enter at least a first name and last name.");
        }

        // Separate the first name and last name.
        // Any additional name parts are kept with the first name.
        int lastSpace = fullName.lastIndexOf(' ');

        String firstName = fullName.substring(0, lastSpace);
        String lastName = fullName.substring(lastSpace + 1);

        // Display the available management types.
        System.out.println();
        System.out.println("AVAILABLE MANAGEMENT TYPES");
        System.out.println("PLEASE SELECT FROM THE LIST BELOW");

        for (int i = 0; i < managers.size(); i++) {
            System.out.println((i + 1) + ". "
                    + managers.get(i).getManagerType());
        }

        // Ask the administrator to select a valid management type.
        int managerChoice = readValidChoice(
                scanner, managers.size(), "Select management type: ");

        String managerType =
                managers.get(managerChoice - 1).getManagerType();

        // Display the available departments.
        System.out.println();
        System.out.println("AVAILABLE DEPARTMENTS");

        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". "
                    + departments.get(i).getDepartmentName());
        }

        // Ask the administrator to select a valid department.
        int departmentChoice = readValidChoice(
                scanner, departments.size(), "Select department: ");

        String departmentName =
                departments.get(departmentChoice - 1).getDepartmentName();

        // Create the new employee record.
        // Fields not requested by the assessment receive default values.
        Employee employee = new Employee(
                firstName,
                lastName,
                "Not provided",
                "Not provided",
                0.0,
                departmentName,
                "",
                managerType,
                "Hospital"
        );

        return employee;
    }

    /**
     * Reads and validates a numbered menu choice.
     *
     * @param scanner Scanner used for input.
     * @param maximum Maximum permitted choice.
     * @param prompt Message displayed to the administrator.
     * @return A valid selection between 1 and maximum.
     */
    private static int readValidChoice(
            Scanner scanner, int maximum, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= maximum) {
                    return choice;
                }

            } catch (NumberFormatException e) {
                // The administrator entered something other than a number.
            }

            System.out.println(
                    "Invalid selection. Enter a number from 1 to " + maximum + ".");
        }
    }
}
