package me.uquark.barrymore.tests;

import me.uquark.barrymore.internal.DatabaseProvider;
import org.junit.*;

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
            DatabaseProvider.rawQuery("SELECT * FROM test");
        } catch (SQLException e) {
            ok = false;
        }

        Assert.assertTrue("SQL request exception", ok);
        stopServer();
    }

    @Test
    public void SQLResponseValidity() throws SQLException {
        Assume.assumeTrue(startServer());

        Assert.assertEquals("SQL response is invalid", DatabaseProvider.rawQuery("SELECT * FROM test"),
                "foo1 | bar1\nfoo2 | bar2\n");
        stopServer();
    }
}
