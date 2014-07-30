package fr.cvlaminck.notidroid.cloud.config.runtime;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DebugRuntimeConfiguration {

    /**
     * Does the server output the full stack trace of errors and sent it back
     * to the client. Useful when working on server code.
     */
    public boolean outputFullStackTraceOnError() {
        return false;
    }

}
