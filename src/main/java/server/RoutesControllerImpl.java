package server;

import data.provider.DataProvider;
import data.provider.NotFoundDataForGivenParameterException;
import database.NotifiableDatabaseConfiguration;
import database.DatabaseNotifyUnsuccessfulException;
import org.eclipse.jetty.util.log.Log;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.halt;

/**
 * This class provides route definitions for service requests with error handling behaviour.
 */
public class RoutesControllerImpl implements RoutesController{
    private static final org.eclipse.jetty.util.log.Logger log = Log.getLogger(RoutesControllerImpl.class);
    private final String parameterName;
    private NotifiableDatabaseConfiguration notifiableDatabaseConfiguration;
    private DataProvider dataProvider;
    private Route serve;

    public RoutesControllerImpl(DataProvider dataProvider, NotifiableDatabaseConfiguration notifiableDatabaseConfiguration, String parameterName) {
        this.dataProvider = dataProvider;
        this.notifiableDatabaseConfiguration = notifiableDatabaseConfiguration;
        this.parameterName = parameterName;
        initializeRoute();
    }

    protected void initializeRoute(){
        this.serve = (Request request, Response response) -> {
            String parameterValue = request.params(parameterName);
            notifyDatabaseAndHandleExceptions(parameterValue);
            Object responseData = getDataFromDataProviderAndHandleExceptions(parameterValue, response);
            log.info("Valid response returned");
            return responseData;
        };
    }

    protected void notifyDatabaseAndHandleExceptions(String parameterValue) {
        try {
            notifiableDatabaseConfiguration.notify(parameterValue);
        } catch(DatabaseNotifyUnsuccessfulException ex) {
            logProblem(ex);
        }
    }

    protected Object getDataFromDataProviderAndHandleExceptions(String parameterValue, Response response) {
        try {
            return dataProvider.getData(parameterValue);
        } catch(NotFoundDataForGivenParameterException ex) {
            haltRequestAndLogProblem(response, ex);
        }
        // Should never happen. Halt should stop execution.
        return null;
    }

    protected void logProblem(Exception ex) {
        log.info(ex.getMessage());
    }

    protected void haltRequestAndLogProblem(Response response, Exception ex){
        logProblem(ex);
        response.type("application/json");
        halt(404, "{\"message\":\"Requested data not found\"}");
    }

    public Route getServe(){
        return serve;
    }

}
