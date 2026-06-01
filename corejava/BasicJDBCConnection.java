import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicJDBCConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "password";

        try {
            Connection con =
                    DriverManager.getConnection(
                            url, username, password);

            Statement stmt = con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM students");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " "
                        + rs.getString("name"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}