import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionExample {

    private static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";

    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void transferMoney(
            int fromAccount,
            int toAccount,
            double amount) {

        Connection con = null;

        try {

            con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            con.setAutoCommit(false);

            String debitQuery =
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?";

            PreparedStatement debitStmt =
                    con.prepareStatement(debitQuery);

            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccount);

            debitStmt.executeUpdate();

            String creditQuery =
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?";

            PreparedStatement creditStmt =
                    con.prepareStatement(creditQuery);

            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccount);

            creditStmt.executeUpdate();

            con.commit();

            System.out.println("Transaction Successful");

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Transaction Failed");

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        transferMoney(1, 2, 500);
    }
}