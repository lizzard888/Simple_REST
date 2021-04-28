package database;

/**
 * This exception type is meant to be thrown, when DatabaseConfiguration cannot notify database about request.
 */
public class DatabaseNotifyUnsuccessfulException extends Exception{
    public DatabaseNotifyUnsuccessfulException(String errorMessage) {
        super(errorMessage);
    }
}
