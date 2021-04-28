package data.provider;

/**
 * This exception type is meant to be thrown, when DataProvider cannot receive data for given request.
 */
public class NotFoundDataForGivenParameterException extends Exception{
    public NotFoundDataForGivenParameterException(String errorMessage) {
        super(errorMessage);
    }
}
