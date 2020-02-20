package me.uquark.barrymore.tests;

import me.uquark.barrymore.internal.DatabaseProvider;
import org.junit.*;

import java.sql.SQLException;

public class TestDatabaseProvider {
    @Test
    public void startServer() {
        boolean ok = true;
        try {
            DatabaseProvider.startServer();
        } catch (SQLException e) {
            ok = false;
        }

        Assert.assertTrue("Start server exception", ok);

        stopServer();
    }

    @Test
    public void SQLRequestException() {
        startServer();

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
        startServer();

        Assert.assertEquals("SQL response is invalid", DatabaseProvider.rawQuery("SELECT * FROM test"),
                "foo1 | bar1\nfoo2 | bar2\n");

        stopServer();
    }

    public void stopServer() {
        DatabaseProvider.stopServer();
    }
}
