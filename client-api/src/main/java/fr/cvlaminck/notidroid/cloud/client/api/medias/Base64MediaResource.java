package fr.cvlaminck.notidroid.cloud.client.api.medias;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Media is fully enclosed in the resource. Media data are converted using
 * Base64 algorithm.
 *
 * @since 0.3
 */
@JsonTypeName(value = Base64MediaResource.NAME)
public class Base64MediaResource
    extends MediaResource {
    public static final String NAME = "base64";
}
