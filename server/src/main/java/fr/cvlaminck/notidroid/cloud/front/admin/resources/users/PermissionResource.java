package fr.cvlaminck.notidroid.cloud.front.admin.resources.users;

import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.PermissionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Representation of a permission that can be given to an administrator.
 * This representation is meant to be used on admin controllers.
 */
public class PermissionResource {

    /**
     * Name of this permission
     */
    private String displayName;

    /**
     * Description of this resource.
     * I18N message read from messages.*.properties
     */
    private String description;

    /**
     * Indicate whether or not the modified administrator already has the permission.
     */
    private boolean isAttributed;

    /**
     * Only an administrator having the permission can give this permission
     * to another. Otherwise the permission cannot be attributed by the
     * current administrator.
     */
    private boolean canBeAttributed;

    /**
     * Underlying permission
     */
    private PermissionEntity permission;

    public PermissionResource(MessageSource messageSource, PermissionEntity permission, AdministratorEntity administrator, AdministratorEntity currentAdministrator) {
        this.permission = permission;
        this.displayName = permission.name();
        this.description = getDescription(messageSource, permission);
        this.isAttributed = administrator.hasPermission(permission);
        this.canBeAttributed = currentAdministrator.hasPermission(permission);
    }

    /**
     * Retrieve the internationalized description from the messages using the current local.
     * If there is no description associated with this permission, return a blank string.
     */
    private static String getDescription(MessageSource messageSource, PermissionEntity permission) {
        final Locale locale = LocaleContextHolder.getLocale();
        return (!StringUtils.isBlank(permission.getDescriptionMessageRef())) ?
                messageSource.getMessage(permission.getDescriptionMessageRef(), null, locale) : "";
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAttributed() {
        return isAttributed;
    }

    public boolean canBeAttributed() {
        return canBeAttributed;
    }

    public PermissionEntity getPermission() {
        return permission;
    }
}
