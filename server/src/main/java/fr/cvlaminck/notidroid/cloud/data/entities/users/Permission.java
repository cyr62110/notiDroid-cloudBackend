package fr.cvlaminck.notidroid.cloud.data.entities.users;

/**
 * A permission is what is required by an administrator to do given action.
 * For ex., the permission ACCESS_USERS is required if an admin want to consult
 * the list of all users of this notidroid backend.
 */
public enum Permission {
    /**
     * List all users and consult their profile
     */
    ACCESS_USERS,
    /**
     * Create a new administrator or convert a regular user into an administrator
     */
    CREATE_ADMIN

}
