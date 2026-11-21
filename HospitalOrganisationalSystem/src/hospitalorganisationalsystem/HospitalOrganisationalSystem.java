
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

        // Ask for the employee data filename.
        System.out.print("Enter the employee data filename: ");
        String fileName = scanner.nextLine().trim();

        try {

            // Load employee records from the CSV file.
            ArrayList<Employee> employees =
                    EmployeeFileReader.loadEmployees(fileName);

            // Extract departments and management types.
            ArrayList<Department> departments =
                    HospitalDataExtractor.extractDepartments(employees);

            ArrayList<Manager> managers =
                    HospitalDataExtractor.extractManagers(employees);

            // Create the alphabetically sorted employee list.
            ArrayList<Employee> sortedEmployees =
                    EmployeeMergeSort.sort(employees);

            // Create and populate the employee binary tree.
            EmployeeBinaryTree employeeTree = new EmployeeBinaryTree();

            for (Employee employee : employees) {
                employeeTree.insert(employee);
            }

            // Display database loading information.
            System.out.println();
            System.out.println("============================================================");
            System.out.println("                 DATABASE LOADING REPORT");
            System.out.println("============================================================");
            System.out.println("Employee records loaded : " + employees.size());
            System.out.println("Departments identified  : " + departments.size());
            System.out.println("Management types found  : " + managers.size());

            // Administrator menu continues until EXIT is selected.
            boolean running = true;

            while (running) {

                System.out.println();
                System.out.println("============================================================");
                System.out.println("             HOSPITAL ADMINISTRATOR MENU");
                System.out.println("============================================================");
                System.out.println("1. Sort Employees");
                System.out.println("2. Search Employee");
                System.out.println("3. Add New Employee");
                System.out.println("4. Display Employee Binary Tree");
                System.out.println("5. Exit");
                System.out.println("============================================================");

                System.out.print("Select an option: ");
                String input = scanner.nextLine().trim();

                // Convert the user's selection into a menu enum value.
                MenuOption selectedOption;

                switch (input) {
                    case "1":
                        selectedOption = MenuOption.SORT_EMPLOYEES;
                        break;
                    case "2":
                        selectedOption = MenuOption.SEARCH_EMPLOYEE;
                        break;
                    case "3":
                        selectedOption = MenuOption.ADD_EMPLOYEE;
                        break;
                    case "4":
                        selectedOption = MenuOption.DISPLAY_EMPLOYEE_TREE;
                        break;
                    case "5":
                        selectedOption = MenuOption.EXIT;
                        break;
                    default:
                        System.out.println("Invalid selection. Enter a number from 1 to 5.");
                        continue;
                }

                // Run the operation selected by the administrator.
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

                        System.out.println();
                        System.out.println("Add Employee functionality will be connected next.");
                        break;

                    case DISPLAY_EMPLOYEE_TREE:

                        System.out.println();
                        System.out.println("HOSPITAL EMPLOYEE BINARY TREE");

                        employeeTree.displayLevelOrder();

                        System.out.println();
                        System.out.println("TREE STATISTICS");
                        System.out.println("Total employee nodes : "
                                + employeeTree.getTotalNodes());
                        System.out.println("Tree height          : "
                                + employeeTree.getHeight());
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
