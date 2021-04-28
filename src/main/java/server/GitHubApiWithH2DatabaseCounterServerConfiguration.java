package server;

import data.provider.GitHubClient;
import data.provider.NotFoundDataForGivenParameterException;
import database.DatabaseNotifyUnsuccessfulException;
import database.H2DBConfiguration;
import processing.FollowersAndPublicReposCalculationsResponseTransformer;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

/**
 * This class provides concrete server configuration for working with GitHub API and H2 database.
 */
public class GitHubApiWithH2DatabaseCounterServerConfiguration extends ServerConfiguration{
    private static final String parameterName = "login";

    public GitHubApiWithH2DatabaseCounterServerConfiguration()
            throws DatabaseNotifyUnsuccessfulException, NotFoundDataForGivenParameterException {
        super(new GitHubClient(), new H2DBConfiguration("jdbc:h2:file:~/h2db"), 8080);
    }

    // Constructor for testing purposes only.
    protected GitHubApiWithH2DatabaseCounterServerConfiguration(GitHubClient gitHubClient, H2DBConfiguration h2DBConfiguration)
            throws DatabaseNotifyUnsuccessfulException, NotFoundDataForGivenParameterException {
        super(gitHubClient, h2DBConfiguration, 8080);
    }

    @Override
    protected RoutesController initializeRoutesController() throws DatabaseNotifyUnsuccessfulException, NotFoundDataForGivenParameterException {
        return new RoutesControllerImpl(dataProvider, notifiableDatabaseConfiguration, parameterName);
    }

    @Override
    protected void initializePath() {
        /* /users/:login */
        path("/users/:login", () -> {
            before("", GitHubApiWithH2DatabaseCounterServerConfiguration::filterWrongMethodOrLogRequest);
            get("", routesController.getServe(), new FollowersAndPublicReposCalculationsResponseTransformer());
        });
    }

    protected static void filterWrongMethodOrLogRequest(Request request, Response response) {
        if(request.requestMethod().equals("GET") || request.requestMethod().equals("HEAD")) {
            log.info("Received api call from: " + request.ip() + " for login: " + request.params(parameterName));
        }
        else {
            response.type("application/json");
            halt(405, "{\"message\":\"Method Not Allowed\"}");
        }
    }
}
