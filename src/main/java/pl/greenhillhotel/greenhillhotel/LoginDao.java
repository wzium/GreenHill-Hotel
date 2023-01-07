package pl.greenhillhotel.greenhillhotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public UserBean login(LoginBean data) {
        UserBean user = new UserBean();
        Connection connection = new Connector().getConnection();
        String sqlQuery = "SELECT * FROM user WHERE email= ? AND password = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, data.getEmail());
            ps.setString(2, data.getPassword());
            ResultSet rs = ps.executeQuery();

            if (!rs.next())
                user.setValid(false);

            else {
                user.setId(rs.getInt("id_registration"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setAdmin(rs.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
