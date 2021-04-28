package data.provider;

import data.model.UserResponseData;
import data.model.GitHubUserResponseData;
import kong.unirest.GetRequest;
import kong.unirest.HttpRequest;
import kong.unirest.Unirest;

/**
 * This class is responsible for retrieving data from external GitHub API.
 */
public class GitHubClient implements DataProvider{
    protected String url = "https://api.github.com/users/{login}";

    @Override
    public UserResponseData getData(String parameterValue) throws NotFoundDataForGivenParameterException{
        HttpRequest<GetRequest> request = Unirest.get(url)
                .routeParam("login", parameterValue);
        validateRequest(request);
        return request.asObject(GitHubUserResponseData.class)
                .getBody()
                .convert();
    }

    protected static void validateRequest(HttpRequest<GetRequest> request) throws NotFoundDataForGivenParameterException {
        if (request.asJson().getStatus() != 200) {
            throw new NotFoundDataForGivenParameterException("User not found in GitHub. Status code:" + request.asJson().getStatus());
        }
    }
}
