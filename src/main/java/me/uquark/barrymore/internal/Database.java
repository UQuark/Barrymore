package me.uquark.barrymore.internal;

import org.h2.tools.Server;

import java.sql.*;

public class Database {
    private static Server server = null;

    public static void startServer() throws SQLException {
        if (server != null && server.isRunning(false))
            return;
        server = Server.createTcpServer("-tcpPort", "1433");
    }

    public static void stopServer() {
        server.stop();
    }
}
