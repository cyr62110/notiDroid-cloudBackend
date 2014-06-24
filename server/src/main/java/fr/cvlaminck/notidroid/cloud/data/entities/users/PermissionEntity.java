package fr.cvlaminck.notidroid.cloud.data.entities.users;

/**
 * A permission is what is required by an administrator to do given action.
 * For ex. the permission ACCESS_USERS is required if an admin want to consult
 * the list of all users of this notidroid cloud backend.
 */
public enum PermissionEntity {
    /**
     * List all users
     */
    LIST_ALL_USERS("ROLE_LIST_ALL_USERS", "permission.listAllUsers"),
    /**
     * Create a new administrator
     */
    CREATE_ADMIN("ROLE_CREATE_ADMIN", "permission.createAdmin");

    /**
     * Role in Spring Security that is associated with this
     * permission
     */
    private String role;

    /**
     * Reference pointing to the internationalized description of the permission.
     */
    private String descriptionMessageRef;

    private PermissionEntity(String role, String descriptionMessageRef) {
        this.role = role;
        this.descriptionMessageRef = descriptionMessageRef;
    }

    public String getRole() {
        return role;
    }

    public String getDescriptionMessageRef() {
        return descriptionMessageRef;
    }
}
