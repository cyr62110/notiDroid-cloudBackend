package fr.cvlaminck.notidroid.cloud.data.entities.users;

/**
 * A permission is what is required by an administrator to do a given action.
 * For ex. the permission LIST_ALL_USERS is required if an admin want to consult
 * the list of all users in the database.
 */
public enum PermissionEntity {
    /**
     * Access to the administration api and the console that consumes it.
     * This permission determine if the user is an administrator or not.
     */
    ACCESS_ADMIN_API("admin-api"),
    /**
     * List all users
     */
    LIST_ALL_USERS("admin-api.users.list");

    /**
     * OAuth2 scope in Spring Security that is associated with this
     * permission.
     */
    private String scope;

    private PermissionEntity(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
