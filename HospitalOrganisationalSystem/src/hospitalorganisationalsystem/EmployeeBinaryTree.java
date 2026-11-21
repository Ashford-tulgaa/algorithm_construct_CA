
package hospitalorganisationalsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Creates and manages a binary search tree of hospital employees.
 * Each node stores an employee's name, designation and department.
 */
public class EmployeeBinaryTree {

    /**
     * Represents one employee node in the binary tree.
     */
    private class Node {

        private String name;
        private String designation;
        private String department;

        private Node left;
        private Node right;

        /**
         * Creates a node containing employee information.
         */
        public Node(Employee employee) {
            this.name = employee.getFullName();
            this.designation = employee.getJobTitle();
            this.department = employee.getDepartment();
        }
    }

    // The first node in the tree.
    private Node root;

    // Counts how many employees are stored in the tree.
    private int totalNodes;

    /**
     * Adds an employee to the binary search tree.
     */
    public void insert(Employee employee) {

        root = insertNode(root, employee);
    }

    /**
     * Recursively finds the correct position for a new employee.
     */
    private Node insertNode(Node current, Employee employee) {

        // Create a new node when an empty position is found.
        if (current == null) {
            totalNodes++;
            return new Node(employee);
        }

        // Compare employee names alphabetically.
        int comparison = employee.getFullName()
                .compareToIgnoreCase(current.name);

        // Insert alphabetically before the current node.
        if (comparison < 0) {
            current.left = insertNode(current.left, employee);
        } else {
            // Insert after the current node, including duplicate names.
            current.right = insertNode(current.right, employee);
        }

        return current;
    }

    /**
     * Displays the tree using level-order traversal.
     * Employees are displayed one level at a time.
     */
    public void displayLevelOrder() {

        if (root == null) {
            System.out.println("The binary tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();

        // Start with the root node.
        queue.add(root);

        int level = 1;

        while (!queue.isEmpty()) {

            int nodesAtLevel = queue.size();

            System.out.println();
            System.out.println("Level " + level + ":");

            // Process every node at the current level.
            for (int i = 0; i < nodesAtLevel; i++) {

                Node current = queue.remove();

                System.out.println("  Name: " + current.name
                        + " | Designation: " + current.designation
                        + " | Department: " + current.department);

                // Add the left child if it exists.
                if (current.left != null) {
                    queue.add(current.left);
                }

                // Add the right child if it exists.
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            level++;
        }
    }

    /**
     * Returns the total number of levels in the tree.
     */
    public int getHeight() {

        return calculateHeight(root);
    }

    /**
     * Calculates tree height recursively.
     */
    private int calculateHeight(Node current) {

        if (current == null) {
            return 0;
        }

        int leftHeight = calculateHeight(current.left);
        int rightHeight = calculateHeight(current.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the total number of employee nodes.
     */
    public int getTotalNodes() {

        return totalNodes;
    }
}