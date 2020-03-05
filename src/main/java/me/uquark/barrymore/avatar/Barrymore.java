package me.uquark.barrymore.avatar;

import me.uquark.barrymore.Application;
import me.uquark.barrymore.internal.DatabaseProvider;
import me.uquark.barrymore.ui.IUserInterface;
import me.uquark.barrymore.ui.UserOrder;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Barrymore implements IAvatar {
    private IUserInterface ui;
    private static final int ID = 1;
    private final String name;

    public Barrymore(IUserInterface ui) throws SQLException {
        this.ui = ui;
        CachedRowSet crs = DatabaseProvider.query(String.format("SELECT avatarName FROM avatars WHERE avatarId = %d", ID));
        crs.next();
        name = crs.getString(1);
    }

    @Override
    public void userOrder(UserOrder uo) {
        if (uo.message.equals("/exit")) {
            halt();
            return;
        }
        ui.putMessage(uo.message);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void halt() {
        ui.close();
        Application.halt();
    }

    @Override
    public void run() {
        ui.setAvatar(this);
        ui.open();
    }
}
