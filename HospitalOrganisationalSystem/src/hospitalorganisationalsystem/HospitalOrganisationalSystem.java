
package hospitalorganisationalsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class of the Hospital Organisational System.
 * Starts the application and loads employee data.
 */
public class HospitalOrganisationalSystem {

    public static void main(String[] args) {

        // Scanner allows the user to enter information in the console.
        Scanner scanner = new Scanner(System.in);

        // Welcoming command line screen
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
        System.out.println("       Employee records database requires initialisation.");
        System.out.println("       Enter the source data file to proceed.");
        System.out.println();
        System.out.println("  ------------------------------------------------------------------");
        System.out.println();

        // Ask the user for the sample data filename.
        System.out.print("Enter the employee data filename: ");
        String fileName = scanner.nextLine().trim();

        try {

            // Load employee records using the filename entered by the user.
            ArrayList<Employee> employees =
                    EmployeeFileReader.loadEmployees(fileName);

            // Extract unique departments from the employee records.
            ArrayList<Department> departments =
                    HospitalDataExtractor.extractDepartments(employees);

            // Extract management designations from the employee records.
            ArrayList<Manager> managers =
                    HospitalDataExtractor.extractManagers(employees);
            
            // Sort employee records alphabetically by full name.
            ArrayList<Employee> sortedEmployees =
            EmployeeMergeSort.sort(employees);

            // Display the database loading report.
            System.out.println();
            System.out.println("============================================================");
            System.out.println("                 DATABASE LOADING REPORT");
            System.out.println("============================================================");
            System.out.println();

            System.out.println("Employee records loaded : " + employees.size());
            System.out.println("Departments identified : " + departments.size());
            System.out.println("Management types found : " + managers.size());

            // Display all identified departments.
            System.out.println();
            System.out.println("AVAILABLE DEPARTMENTS");
            System.out.println("---------------------");

            for (Department department : departments) {
                System.out.println("- " + department.getDepartmentName());
            }

            // Display all identified management types.
            System.out.println();
            System.out.println("MANAGEMENT TYPES");
            System.out.println("----------------");

            for (Manager manager : managers) {
                System.out.println("- " + manager.getManagerType());
            }

            System.out.println();
            System.out.println("============================================================");
            System.out.println("              EMPLOYEE DATABASE READY");
            System.out.println("============================================================");

        } catch (IOException e) {

            // Display an error if the file cannot be read.
            System.out.println("\nError loading employee data: "
                    + e.getMessage());
        }

        scanner.close();
    }
}
