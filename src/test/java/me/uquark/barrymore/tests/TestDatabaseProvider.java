package me.uquark.barrymore.tests;

import me.uquark.barrymore.internal.DatabaseProvider;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class TestDatabaseProvider {
    private boolean startServer() {
        boolean ok = true;
        try {
            DatabaseProvider.startServer();
        } catch (SQLException e) {
            ok = false;
        }
        return ok;
    }

    private void stopServer() {
        DatabaseProvider.stopServer();
    }

    @Test
    public void testStartServer() {
        Assert.assertTrue("Failed to start the server", startServer());
        stopServer();
    }

    @Test
    public void SQLRequestException() {
        Assume.assumeTrue(startServer());

        boolean ok = true;
        try {
            DatabaseProvider.query("SELECT * FROM test");
        } catch (SQLException e) {
            ok = false;
        }

        Assert.assertTrue("SQL request exception", ok);
        stopServer();
    }

    @Test
    public void SQLResponseValidity() throws SQLException {
        Assume.assumeTrue(startServer());

        CachedRowSet crs = DatabaseProvider.query("SELECT * FROM test");
        crs.next();
        Assert.assertEquals("SQL response is invalid", crs.getString(1), "val");

        stopServer();
    }
}
