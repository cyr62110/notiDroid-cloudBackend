package fr.cvlaminck.notidroid.cloud.data.entities;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;

    /**
     * Email of the user. It is also used as it username in the login procedure
     */
    private String email;

}
