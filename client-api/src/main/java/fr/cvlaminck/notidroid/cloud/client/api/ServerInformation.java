package fr.cvlaminck.notidroid.cloud.client.api;

/**
 * Information about the notidroid cloud backend that
 * is currently hosted on the server.
 */
public class ServerInformation {

    /**
     * Name of the server
     */
    private String publicName;

    /**
     * Version of notidroid cloud backend that
     * is currently running
     */
    private String version;

    //TODO : more to add

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
