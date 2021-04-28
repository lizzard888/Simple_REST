package server;

import data.provider.DataProvider;
import data.provider.NotFoundDataForGivenParameterException;
import database.DatabaseNotifyUnsuccessfulException;
import database.NotifiableDatabaseConfiguration;
import org.eclipse.jetty.util.log.Log;
import static spark.Spark.port;

/**
 * This class is responsible for server initialization with predefined endpoints.
 */
public abstract class ServerConfiguration {
    protected static final org.eclipse.jetty.util.log.Logger log = Log.getLogger(ServerConfiguration.class);
    protected RoutesController routesController;
    protected DataProvider dataProvider;
    protected NotifiableDatabaseConfiguration notifiableDatabaseConfiguration;
    private final int portNumber;

    public ServerConfiguration(DataProvider dataProvider, NotifiableDatabaseConfiguration notifiableDatabaseConfiguration, int portNumber)
            throws DatabaseNotifyUnsuccessfulException, NotFoundDataForGivenParameterException {
        this.dataProvider = dataProvider;
        this.notifiableDatabaseConfiguration = notifiableDatabaseConfiguration;
        this.routesController = initializeRoutesController();
        this.portNumber = portNumber;
    }

    public void start() {
        port(portNumber);
        initializePath();
    }

    protected abstract RoutesController initializeRoutesController() throws DatabaseNotifyUnsuccessfulException,
            NotFoundDataForGivenParameterException;

    protected abstract void initializePath();
}
