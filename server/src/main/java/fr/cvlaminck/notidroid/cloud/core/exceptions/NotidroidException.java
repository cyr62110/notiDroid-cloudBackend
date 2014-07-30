package fr.cvlaminck.notidroid.cloud.core.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * Created by cyr62110 on 29/05/2014.
 */
public class NotidroidException extends Exception {

    /**
     * Internationalized message id.
     * If null, the message will be used.
     */
    private String i18NMessageId = null;

    /**
     * Status code that should be associated to this exception
     * and returned by a REST front to the client.
     */
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public NotidroidException(String message) {
        super(message);
    }

    public NotidroidException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public NotidroidException(HttpStatus httpStatus, String message, String i18NMessageId) {
        super(message);
        this.httpStatus = httpStatus;
        this.i18NMessageId = i18NMessageId;
    }

    public NotidroidException(String message, String i18NMessageId) {
        super(message);
        this.i18NMessageId = message;
    }

    public NotidroidException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotidroidException(Throwable cause) {
        super(cause);
    }

    public NotidroidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Return a localized version of the message.
     *
     * @param messageSource MessageSource that will be requested to obtain the localized version of the message.
     * @param locale
     * @return a localized version of the exception message.
     */
    public String getLocalizedMessage(MessageSource messageSource, Locale locale) {
        if (i18NMessageId == null)
            return getMessage();
        else
            return messageSource.getMessage(i18NMessageId, null, getMessage(), locale);
    }
}
