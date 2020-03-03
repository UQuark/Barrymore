package me.uquark.barrymore.internal;

import org.h2.tools.Server;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseProvider {
    private static Server server = null;
    private static String url = "";

    public static void startServer() throws SQLException, MalformedURLException {
        url = System.getenv("BARRYMORE_DB_URL");
        if (url.equals("null"))
            throw new MalformedURLException("BARRYMORE_DB_URL is not set");
        if (server != null && server.isRunning(false))
            return;
        server = Server.createTcpServer("-tcpPort", "0");
    }

    public static void stopServer() {
        server.stop();
    }

    public static CachedRowSet query(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "barrymore", "");
        Statement statement = connection.createStatement();
        CachedRowSet result = RowSetProvider.newFactory().createCachedRowSet();
        result.populate(statement.executeQuery(query));
        connection.close();
        return result;
    }
}
