package fr.cvlaminck.notidroid.cloud.client.api.medias;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The media is enclosed as a link that the client should follow to retrieve the
 * media data.
 *
 * @since 0.3
 */
@JsonTypeName(value = UrlMediaResource.NAME)
public class UrlMediaResource
    extends MediaResource {
    public static final String NAME = "link";


}
