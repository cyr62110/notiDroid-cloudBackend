package fr.cvlaminck.notidroid.cloud.data.entities.configs;

/**
 * Enumeration of the registration mode.
 */
public enum RegistrationMode {

    /**
     * No one user can create a new account.
     */
    CLOSED,
    /**
     * Only user with email listed in the whitelist can create a new account.
     */
    WHITELISTED,
    /**
     * Anyone can create a new account.
     */
    OPEN
}
