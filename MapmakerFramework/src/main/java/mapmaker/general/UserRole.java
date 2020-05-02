package mapmaker.general;

/**
 *
 * @author Simon Norup
 */
public enum UserRole {

    VIEWER, EDITOR;

    private static UserRole currentRole;

    public static UserRole getCurrentRole() {
        return currentRole;
    }

    public static void setCurrentRole(UserRole userRole) {
        currentRole = userRole;
    }
}
