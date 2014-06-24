package fr.cvlaminck.notidroid.cloud.data.entities.users;

import org.apache.commons.lang3.ArrayUtils;
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
    public PermissionEntity[] permissions;

    public AdministratorEntity() {
        super();
        permissions = new PermissionEntity[]{};
    }

    public PermissionEntity[] getPermissions() {
        return permissions;
    }

    public void setPermissions(PermissionEntity[] permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(PermissionEntity permission) {
        return ArrayUtils.contains(permissions, permission);
    }
}
