
package hospitalorganisationalsystem;

import java.util.ArrayList;

/**
 * Creates and manages the hospital organisational hierarchy.
 * Employees are grouped by management rank and department.
 */
public class OrganisationalHierarchy {

    private class Node {

        private Employee employee;
        private ArrayList<Node> children;

        public Node(Employee employee) {
            this.employee = employee;
            this.children = new ArrayList<>();
        }
    }

    private Node root = new Node(null);
    private int totalNodes;

    /**
     * Builds the organisational hierarchy.
     */
    public void buildHierarchy(ArrayList<Employee> employees) {

        root = new Node(null);
        totalNodes = 0;

        ArrayList<Node> employeeNodes = new ArrayList<>();

        // Create a node for every employee.
        for (Employee employee : employees) {
            employeeNodes.add(new Node(employee));
            totalNodes++;
        }

        // Assign employees to higher-ranking employees
        // within their own department.
        for (Node current : employeeNodes) {

            Node parent = findParent(current, employeeNodes);

            parent.children.add(current);
        }
    }

    /**
     * Finds the nearest higher-ranking employee
     * in the same department.
     */
    private Node findParent(Node current, ArrayList<Node> nodes) {

        int currentRank = getManagementRank(
                current.employee.getJobTitle());

        Node bestParent = null;
        int bestRank = -1;

        for (Node candidate : nodes) {

            if (candidate == current) {
                continue;
            }

            int candidateRank = getManagementRank(
                    candidate.employee.getJobTitle());

            boolean sameDepartment =
                    candidate.employee.getDepartment().equalsIgnoreCase(
                            current.employee.getDepartment());

            if (sameDepartment
                    && candidateRank < currentRank
                    && candidateRank > bestRank) {

                bestParent = candidate;
                bestRank = candidateRank;
            }
        }

        // Do not assign employees to managers
        // from another department.
        if (bestParent == null) {
            return root;
        }

        return bestParent;
    }

    /**
     * Assigns a management rank to each job title.
     * Lower numbers represent higher positions.
     */
    private int getManagementRank(String jobTitle) {

        if (jobTitle == null) {
            return 7;
        }

        String title = jobTitle.toLowerCase().trim();

        if (title.contains("head") || title.contains("director")) {
            return 1;
        }

        if (title.contains("senior manager")) {
            return 2;
        }

        if (title.contains("assistant manager")) {
            return 4;
        }

        if (title.contains("manager")) {
            return 3;
        }

        if (title.contains("team lead")
                || title.contains("supervisor")) {
            return 5;
        }

        if (title.contains("coordinator")
                || title.contains("specialist")
                || title.contains("analyst")) {
            return 6;
        }

        return 7;
    }

    /**
     * Displays employees grouped by their management rank.
     * Empty management levels are retained.
     */
    public void displayHierarchy() {

        if (root.children.isEmpty()) {
            System.out.println("The organisational hierarchy is empty.");
            return;
        }

        for (int level = 1; level <= 7; level++) {

            System.out.println();
            System.out.println("Level " + level + ":");

            boolean foundEmployees =
                    displayEmployeesAtRank(root, level);

            if (!foundEmployees) {
                System.out.println("  No employees at this level.");
            }
        }
    }

    /**
     * Recursively displays employees belonging to a specific rank.
     */
    private boolean displayEmployeesAtRank(Node current, int rank) {

        boolean found = false;

        for (Node child : current.children) {

            if (getManagementRank(
                    child.employee.getJobTitle()) == rank) {

                Employee employee = child.employee;

                System.out.println(
                        "  Name: " + employee.getFullName()
                        + " | Designation: " + employee.getJobTitle()
                        + " | Department: " + employee.getDepartment()
                );

                found = true;
            }

            if (displayEmployeesAtRank(child, rank)) {
                found = true;
            }
        }

        return found;
    }

    /**
     * Returns the total number of employees in the hierarchy.
     */
    public int getTotalNodes() {
        return totalNodes;
    }
}
