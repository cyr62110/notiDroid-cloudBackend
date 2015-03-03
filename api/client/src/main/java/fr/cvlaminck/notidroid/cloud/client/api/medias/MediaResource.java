package fr.cvlaminck.notidroid.cloud.client.api.medias;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A media is an image, a video, a music that is associated with another resource
 * like a notification of an application.
 *
 * @since 0.3
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = UrlMediaResource.class),
        @JsonSubTypes.Type(value = Base64MediaResource.class),
})
public abstract class MediaResource {

    /**
     * Id of the media.
     *
     * @since 0.3
     */
    private String id;

    /**
     * Mime-type describing the type of media.
     *
     * @since 0.3
     */
    private String mimeType;

    /**
     * Hash of the media data.
     * SHA-1 algorithm is used to compute the hash.
     *
     * @since 0.3
     */
    private String hash;

}
