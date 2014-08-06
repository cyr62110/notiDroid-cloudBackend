package fr.cvlaminck.notidroid.activemq.utils;

/**
 * Utility class for manipulating URL
 */
public class UrlUtils {

    public static String appendPathFragmentToUrl(String url, String pathFragment) {
        if (pathFragment.startsWith("/"))
            pathFragment = pathFragment.substring(1);
        return (url.endsWith("/") ? url : (url + "/")) + pathFragment;
    }

}
