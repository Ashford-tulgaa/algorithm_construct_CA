package hospitalorganisationalsystem;

/**
 * Represents a management type in the hospital organisation.
 * Examples include Head Manager and Assistant Manager.
 */
public class Manager {

    // Stores the management type.
    private String managerType;

    /**
     * Creates a Manager object.
     *
     * @param managerType The management designation.
     */
    public Manager(String managerType) {
        this.managerType = managerType;
    }

    /**
     * Returns the management type.
     *
     * @return Management designation.
     */
    public String getManagerType() {
        return managerType;
    }

    /**
     * Returns the management type as readable text.
     *
     * @return Management designation.
     */
    @Override
    public String toString() {
        return managerType;
    }
}
