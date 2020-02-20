package me.uquark.barrymore;

import me.uquark.barrymore.avatar.Barrymore;
import me.uquark.barrymore.avatar.IAvatar;
import me.uquark.barrymore.internal.DatabaseProvider;
import me.uquark.barrymore.ui.ConsoleUserInterface;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException {
        DatabaseProvider.startServer();
        System.out.println(DatabaseProvider.rawQuery("SELECT * FROM test"));
        DatabaseProvider.stopServer();
        IAvatar barrymore = new Barrymore(new ConsoleUserInterface());
        barrymore.run();
        while (barrymore.isRunning());
    }
}
