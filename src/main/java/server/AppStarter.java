package server;

import kong.unirest.Unirest;
import org.eclipse.jetty.util.log.Log;
import static spark.Spark.stop;

/**
Class responsible for starting App and handling initialization errors.
 */
public class AppStarter {
    private static final org.eclipse.jetty.util.log.Logger log = Log.getLogger(AppStarter.class);
    private static ServerConfiguration serverConfiguration;

    public static void main(String[] args) {
        try {
            serverConfiguration = new GitHubApiWithH2DatabaseCounterServerConfiguration();
        } catch(Exception ex){
            logErrorAndQuit("RoutesController initialization unsuccessful: " + ex.getMessage());
        }
        serverConfiguration.start();
    }

    private static void logErrorAndQuit(String message) {
        log.info(message);
        stop();
        Unirest.shutDown();
        System.exit(1);
    }
}
