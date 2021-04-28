package data.provider;

/**
 * An interface describing class which can return data needed fo further processing request.
 */
public interface DataProvider {
    Object getData(String parameter) throws NotFoundDataForGivenParameterException;
}
