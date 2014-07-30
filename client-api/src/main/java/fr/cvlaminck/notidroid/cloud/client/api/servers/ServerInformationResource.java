package fr.cvlaminck.notidroid.cloud.client.api.servers;

/**
 * Information about the notidroid cloud backend that
 * is currently hosted on the server.
 *
 * @since 0.1
 */
public class ServerInformationResource {

    /**
     * Name of the server
     */
    private String publicName = null;

    /**
     * Version of notidroid cloud backend that
     * is currently running
     */
    private String version;

    /**
     *
     */
    private int supportedAPIVersion[] = null;

    //TODO : more to add

    public ServerInformationResource() {
        supportedAPIVersion = new int[]{};
    }

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

    public int[] getSupportedAPIVersion() {
        return supportedAPIVersion;
    }

    public void setSupportedAPIVersion(int[] supportedAPIVersion) {
        this.supportedAPIVersion = supportedAPIVersion;
    }
}
