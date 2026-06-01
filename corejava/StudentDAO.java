import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";

    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void insertStudent(int id, String name) {

        String query =
                "INSERT INTO students(id, name) VALUES(?, ?)";

        try {
            Connection con =
                    DriverManager.getConnection(
                            URL, USER, PASSWORD);

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);

            int rows = ps.executeUpdate();

            System.out.println(
                    rows + " record inserted.");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(int id, String newName) {

        String query =
                "UPDATE students SET name=? WHERE id=?";

        try {
            Connection con =
                    DriverManager.getConnection(
                            URL, USER, PASSWORD);

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, newName);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(
                    rows + " record updated.");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        dao.insertStudent(3, "Sai");

        dao.updateStudent(3, "Sai Kumar");
    }
}