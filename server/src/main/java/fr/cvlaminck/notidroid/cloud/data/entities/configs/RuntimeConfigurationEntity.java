package fr.cvlaminck.notidroid.cloud.data.entities.configs;

/**
 *
 */
public class RuntimeConfigurationEntity {

    private Long id;

    /**
     * Define if new users can create their accounts.
     */
    private RegistrationMode registrationMode = RegistrationMode.CLOSED;

    public RuntimeConfigurationEntity() {

    }

    public Long getId() {
        return id;
    }

    public RegistrationMode getRegistrationMode() {
        return registrationMode;
    }

    public void setRegistrationMode(RegistrationMode registrationMode) {
        this.registrationMode = registrationMode;
    }
}
