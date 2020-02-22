package me.uquark.barrymore.internal;

import org.h2.tools.Server;

import java.sql.*;

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

    public static String rawQuery(String query) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/.barrymore/database", "barrymore", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        StringBuilder rawResponse = new StringBuilder();
        while (resultSet.next())
            rawResponse.append(resultSet.getString(1)).append(" | ").append(resultSet.getString(2)).append("\n");
        connection.close();
        return rawResponse.toString();
    }
}
