
package hospitalorganisationalsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class of the Hospital Organisational System.
 * Loads employee data and provides an administrator menu.
 */
public class HospitalOrganisationalSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Welcoming command line screen.
        System.out.println();
        System.out.println("  +================================================================+");
        System.out.println("  |                                                                |");
        System.out.println("  |     ____   ___  _   _   ____  _____ ____  _____ _   _  ____     |");
        System.out.println("  |    | __ ) / _ \\| \\ | | / ___|| ____/ ___|| ____| | | |/ ___|    |");
        System.out.println("  |    |  _ \\| | | |  \\| | \\___ \\|  _| \\___ \\|  _| | | | | |        |");
        System.out.println("  |    | |_) | |_| | |\\  |  ___) | |___ ___) | |___| |_| | |___     |");
        System.out.println("  |    |____/ \\___/|_| \\_| |____/|_____|____/|_____|\\___/ \\____|    |");
        System.out.println("  |                                                                |");
        System.out.println("  |              HOSPITAL ORGANISATIONAL SYSTEM                    |");
        System.out.println("  |                                                                |");
        System.out.println("  +================================================================+");
        System.out.println("  |  MEDICAL INFORMATION TECHNOLOGY DIVISION                       |");
        System.out.println("  |  TERMINAL ID       : BS-HOS-001                                 |");
        System.out.println("  |  SYSTEM STATUS     : ONLINE                                     |");
        System.out.println("  |  ACCESS MODE       : ADMINISTRATION                             |");
        System.out.println("  +================================================================+");
        System.out.println();
        System.out.println("       [ SYSTEM INITIALISED ]");
        System.out.println("       Welcome, Hospital Administrator.");
        System.out.println();

        System.out.print("Enter the employee data filename: ");
        String fileName = scanner.nextLine().trim();

        try {

            // Load employee records from the CSV file.
            ArrayList<Employee> employees =
                    EmployeeFileReader.loadEmployees(fileName);

            // Extract unique departments and management types.
            ArrayList<Department> departments =
                    HospitalDataExtractor.extractDepartments(employees);

            ArrayList<Manager> managers =
                    HospitalDataExtractor.extractManagers(employees);

            // Sort employee records alphabetically.
            ArrayList<Employee> sortedEmployees =
                    EmployeeMergeSort.sort(employees);

            // Create and build the organisational hierarchy.
            OrganisationalHierarchy hierarchy =
                    new OrganisationalHierarchy();

            hierarchy.buildHierarchy(employees);

            // Display database loading information.
            System.out.println();
            System.out.println("============================================================");
            System.out.println("                 DATABASE LOADING REPORT");
            System.out.println("============================================================");
            System.out.println("Employee records loaded : " + employees.size());
            System.out.println("Departments identified  : " + departments.size());
            System.out.println("Management types found  : " + managers.size());

            boolean running = true;

            while (running) {

                System.out.println();
                System.out.println("============================================================");
                System.out.println("             HOSPITAL ADMINISTRATOR MENU");
                System.out.println("============================================================");

                // Display menu options.
                for (MenuOption option : MenuOption.values()) {
                    System.out.println(option.getMenuNumber() + ". "
                            + option.getDescription());
                }

                System.out.println("============================================================");
                System.out.print("Select an option: ");

                String input = scanner.nextLine().trim();

                MenuOption selectedOption = null;

                // Convert the entered number into a menu enum value.
                try {
                    int choice = Integer.parseInt(input);

                    for (MenuOption option : MenuOption.values()) {
                        if (option.getMenuNumber() == choice) {
                            selectedOption = option;
                            break;
                        }
                    }

                } catch (NumberFormatException e) {
                    // Invalid numeric input.
                }

                // Reject invalid menu selections.
                if (selectedOption == null) {
                    System.out.println("Invalid selection. Please try again.");
                    continue;
                }

                // Execute the selected menu operation.
                switch (selectedOption) {

                    case SORT_EMPLOYEES:

                        sortedEmployees = EmployeeMergeSort.sort(employees);

                        System.out.println();
                        System.out.println("FIRST 20 EMPLOYEES - A TO Z");
                        System.out.println("---------------------------");

                        int displayLimit =
                                Math.min(20, sortedEmployees.size());

                        for (int i = 0; i < displayLimit; i++) {
                            System.out.println((i + 1) + ". "
                                    + sortedEmployees.get(i).getFullName());
                        }

                        break;

                    case SEARCH_EMPLOYEE:

                        System.out.print("Enter employee full name: ");
                        String searchName = scanner.nextLine().trim();

                        if (searchName.isEmpty()) {
                            System.out.println("Search name cannot be empty.");
                            break;
                        }

                        Employee foundEmployee =
                                EmployeeBinarySearch.search(
                                        sortedEmployees, searchName);

                        if (foundEmployee != null) {

                            System.out.println();
                            System.out.println("EMPLOYEE FOUND");
                            System.out.println("Name        : "
                                    + foundEmployee.getFullName());
                            System.out.println("Designation : "
                                    + foundEmployee.getJobTitle());
                            System.out.println("Department  : "
                                    + foundEmployee.getDepartment());

                        } else {
                            System.out.println("EMPLOYEE NOT FOUND");
                        }

                        break;

                    case ADD_EMPLOYEE:

                        Employee newEmployee =
                                EmployeeInputHandler.createEmployee(
                                        scanner, managers, departments);

                        employees.add(newEmployee);

                        // Update the sorted employee list.
                        sortedEmployees =
                                EmployeeMergeSort.sort(employees);

                        // Rebuild the organisational hierarchy.
                        hierarchy.buildHierarchy(employees);

                        System.out.println();
                        System.out.println("============================================================");
                        System.out.println("             EMPLOYEE SUCCESSFULLY ADDED");
                        System.out.println("============================================================");
                        System.out.println("Name        : "
                                + newEmployee.getFullName());
                        System.out.println("Designation : "
                                + newEmployee.getJobTitle());
                        System.out.println("Department  : "
                                + newEmployee.getDepartment());
                        System.out.println("============================================================");

                        break;

                    case DISPLAY_ORGANISATIONAL_HIERARCHY:

                        System.out.println();
                        System.out.println("============================================================");
                        System.out.println("          HOSPITAL ORGANISATIONAL HIERARCHY");
                        System.out.println("============================================================");

                        hierarchy.displayHierarchy();

                        System.out.println();
                        System.out.println("Total employees in hierarchy: "
                                + hierarchy.getTotalNodes());

                        break;

                    case EXIT:

                        running = false;

                        System.out.println();
                        System.out.println("Hospital system shutting down.");
                        System.out.println("Goodbye, Administrator.");

                        break;
                }
            }

        } catch (IOException e) {

            System.out.println("\nError loading employee data: "
                    + e.getMessage());
        }

        scanner.close();
    }
}
