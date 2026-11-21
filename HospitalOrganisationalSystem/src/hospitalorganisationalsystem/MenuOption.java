
package hospitalorganisationalsystem;

/**
 * Defines the available operations in the hospital system.
 * Each option has a menu number and a display description.
 */
public enum MenuOption {

    SORT_EMPLOYEES(1, "Sort Employees Alphabetically"),
    SEARCH_EMPLOYEE(2, "Search Employee"),
    ADD_EMPLOYEE(3, "Add Employee"),
    DISPLAY_ORGANISATIONAL_HIERARCHY(4, "Display Organisational Hierarchy"),
    EXIT(5, "Exit");

    private final int menuNumber;
    private final String description;

    /**
     * Assigns a number and description to each menu option.
     */
    MenuOption(int menuNumber, String description) {
        this.menuNumber = menuNumber;
        this.description = description;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public String getDescription() {
        return description;
    }
}
