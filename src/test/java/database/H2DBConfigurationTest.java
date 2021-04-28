package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.*;

public class H2DBConfigurationTest {
    private static String dbUrl = "jdbc:h2:mem:testdb";
    private static H2DBConfiguration dbConfig;
    private static String login = "shrek";

    @Before
    public void setUp() throws Exception {
        dbConfig = new H2DBConfiguration(dbUrl, "");
    }

    @After
    public void tearDown() throws Exception {
        dbConfig.h2Connection.close();
    }

    @Test
    public void checkIfRequestCountsIncrementsAfterNotify() throws Exception{
        dbConfig.notify(login);
        assertThat(getRequestCountForUser(login)).isEqualTo(1);
        dbConfig.notify(login);
        assertThat(getRequestCountForUser(login)).isEqualTo(2);
    }

    @Test
    public void checkIfAfterIfAfterLoginInsertionCheckWorks() throws Exception{
        assertThat(dbConfig.checkIfLoginExists(login)).isFalse();
        dbConfig.insertLogin(login);
        assertThat(dbConfig.checkIfLoginExists(login)).isTrue();
        assertThat(getRequestCountForUser(login)).isEqualTo(1);
    }

    @Test
    public void checkIfRequestCountIncrementationWorksAfterTwoCalls() throws Exception{
        dbConfig.insertLogin(login);
        assertThat(getRequestCountForUser(login)).isEqualTo(1);
        dbConfig.incrementRequestCounter(login);
        assertThat(getRequestCountForUser(login)).isEqualTo(2);
        dbConfig.incrementRequestCounter(login);
        assertThat(getRequestCountForUser(login)).isEqualTo(3);
    }

    private int getRequestCountForUser(String login) throws Exception{
        String sql = "SELECT REQUEST_COUNT FROM USERS WHERE LOGIN = ?;";
        PreparedStatement statement = dbConfig.h2Connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet result = statement.executeQuery();
        if(result.next())
            return result.getInt(1);
        else
            return 0;
    }

}