package hospitalorganisationalsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads employee information from the supplied CSV sample data file.
 */
public class EmployeeFileReader {

    /**
     * Reads the CSV file and creates an Employee object for each record.
     *
     * @param fileName Name or path of the CSV file.
     * @return A list containing the employees read from the file.
     * @throws IOException If the file cannot be read or contains invalid data.
     */
    public static ArrayList<Employee> loadEmployees(String fileName)
            throws IOException {

        // Stores all employees read from the file.
        ArrayList<Employee> employees = new ArrayList<>();

        // Opens the file for reading.
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileName))) {

            String line;
            int lineNumber = 0;

            // Reads the file one line at a time.
            while ((line = reader.readLine()) != null) {

                lineNumber++;

                // Skip the first line because it contains column headings.
                if (lineNumber == 1) {
                    continue;
                }

                // Preserve empty fields, such as a blank Position.
                String[] fields = line.split(",", -1);

                // Each record must contain nine columns.
                if (fields.length != 9) {
                    throw new IOException(
                            "Invalid number of columns on line "
                            + lineNumber);
                }

                try {
                    // Convert the salary column into a number.
                    double salary = Double.parseDouble(fields[4].trim());

                    // Create an Employee object using the CSV information.
                    Employee employee = new Employee(
                            fields[0].trim(),
                            fields[1].trim(),
                            fields[2].trim(),
                            fields[3].trim(),
                            salary,
                            fields[5].trim(),
                            fields[6].trim(),
                            fields[7].trim(),
                            fields[8].trim()
                    );

                    // Add the employee to the list.
                    employees.add(employee);

                } catch (NumberFormatException e) {
                    throw new IOException(
                            "Invalid salary on line " + lineNumber, e);
                }
            }
        }

        return employees;
    }
}
