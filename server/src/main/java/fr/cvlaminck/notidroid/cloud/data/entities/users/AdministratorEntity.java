package fr.cvlaminck.notidroid.cloud.data.entities.users;

import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Administrator are another kind of user that has right to access
 * the admin console. Since they are also user of Notidroid, they
 * can use their administrator account for the user side (Android
 * app, Web app, etc.)
 */
@NodeEntity
public class AdministratorEntity
        extends UserEntity {

    /**
     * Permissions determine which kind of information can be viewed/modified by
     * this administrator.
     */
    public Permission[] permissions;

    public AdministratorEntity() {
        super();
        permissions = new Permission[]{};
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission[] permissions) {
        this.permissions = permissions;
    }
}
