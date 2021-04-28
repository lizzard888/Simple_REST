package database;

/**
 * An interface describing notifiable database configuration.
 */
public interface NotifiableDatabaseConfiguration {
    void notify(String parameter) throws DatabaseNotifyUnsuccessfulException;
}
