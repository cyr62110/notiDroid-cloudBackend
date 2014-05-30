package fr.cvlaminck.notidroid.cloud.core.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

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

    public NotidroidException(String message) {
        super(message);
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

    public String getMessage(MessageSource messageSource, Locale locale) {
        if(i18NMessageId == null)
            return getMessage();
        else
            return messageSource.getMessage(i18NMessageId, null, getMessage(), locale);
    }
}
