
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

            // Display the loading result.
            System.out.println("\nHospital Organisational System");
            System.out.println("--------------------------------");
            System.out.println("Employees loaded: " + employees.size());

            // Display the first employee as a loading test.
            if (!employees.isEmpty()) {
                System.out.println("\nFirst employee loaded:");
                System.out.println(employees.get(0));
            }

        } catch (IOException e) {

            // Display an error if the file cannot be read.
            System.out.println("\nError loading employee data: "
                    + e.getMessage());
        }

        scanner.close();
    }
}
