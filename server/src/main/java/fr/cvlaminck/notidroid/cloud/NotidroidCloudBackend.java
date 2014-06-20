package fr.cvlaminck.notidroid.cloud;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * Class starting the cloud backend in an embedded Tomcat.
 */
public class NotidroidCloudBackend {
    private static Logger LOG = LoggerFactory.getLogger(NotidroidCloudBackend.class);

    public static void main(String[] args) throws LifecycleException, ServletException, IOException {
        //TODO : add command-line variables to configure the server

        //
        final int port = 8080;
        final String baseDirectory = createTempBaseDirectory(port);

        LOG.debug("Base directory : " + baseDirectory);

        //We create our embedded Tomcat
        final Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        final Context context = tomcat.addWebapp("/", baseDirectory);

        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Create a temporary base directory that is required to
     * run tomcat in embedded mode.
     */
    private static String createTempBaseDirectory(int port) throws IOException {
        final File baseDirectory = new File("tomcat-baseDir-" + port);

        if (!baseDirectory.exists() && !baseDirectory.mkdir())
            throw new IOException("Cannot create temporary base directory to run Tomcat.");

        return baseDirectory.getAbsolutePath();
    }

}
