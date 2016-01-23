import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Cedric on 30.12.2015.
 */
public class DataGatherer {

    Connection conn = null;

    public DataGatherer() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                            "user=root&password=1234");

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


    }

    public String[] getPizzas() {
        ArrayList<String> names = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT name FROM pizzas;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] samplePizzas = {"Maggarita", "Salami", "Hawai"};
        String[] namesArr = new String[names.size()];
        namesArr = names.toArray(namesArr);

        return namesArr;
    }

    public double getPrice(String pizza) {
        double price = 0;
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT price FROM pizzas WHERE name='" + pizza + "'";
            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            price = rs.getDouble("price");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

}
