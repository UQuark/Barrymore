package me.uquark.barrymore.internal;

import org.h2.tools.Server;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseProvider {
    private static Server server = null;

    public static void startServer() throws SQLException {
        if (server != null && server.isRunning(false))
            return;
        server = Server.createTcpServer("-tcpPort", "0");
    }

    public static void stopServer() {
        server.stop();
    }

    public static CachedRowSet query(String query) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/.barrymore/database", "barrymore", "");
        Statement statement = connection.createStatement();
        CachedRowSet result = RowSetProvider.newFactory().createCachedRowSet();
        result.populate(statement.executeQuery(query));
        connection.close();
        return result;
    }
}
