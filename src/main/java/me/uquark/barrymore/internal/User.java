package me.uquark.barrymore.internal;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class User {
    public final int id;
    public final Group group;
    public final String name;
    public Location location = Location.undefined;

    public enum Group {
        Unknown,
        Guest,
        Member,
        Owner,
    }

    public static final int ROOT_UID = 1, UNKNOWN_UID = 2;

    public User(int id, String name, Group group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public static User getUserById(int userId) throws SQLException {
        String query = String.format("SELECT userName, groupId FROM users WHERE userId = %d", userId);
        CachedRowSet crs = DatabaseProvider.query(query);
        crs.next();
        String userName = crs.getString(1);
        int groupId = crs.getInt(2);
        return new User(userId, userName, Group.values()[groupId - 1]);
    }
}
