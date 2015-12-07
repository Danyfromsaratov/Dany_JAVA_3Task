import java.sql.*;

/**
 * Created by Daniil on 07.12.2015.
 */
public class Customer {
    public static Connection conection;

    public Customer(Connection conection){
        this.conection = conection;
    }


    public static void Customers_Execute(String[] args) throws SQLException {
        switch (args[1]) {
            case "create":
                Customers_Create(Integer.parseInt(args[2]), args[3]);
                break;
            case "update":
                Customers_Update(Integer.parseInt(args[2]), args[3]);
                break;
            case "delete":
                Customers_Remove(Integer.parseInt(args[2]));
                break;
            case "read":
                Customers_ReadAll();
                break;
            default:
                break;
        }
    }

    public static void Customers_Create(int id, String name) {
        try {
            PreparedStatement s = conection.prepareStatement("insert into Customers values (?,?)");
            s.setInt(1, id);
            s.setString(2, name);
            s.execute();
        }
        catch (SQLException e) {
            System.out.println("Error: create");
        }
        System.out.println("Customers was create");
    }

    public static void Customers_Update(int id, String name) {
        try {
            PreparedStatement s = conection.prepareStatement("update Customers set name = ? where id = ?");
            s.setString(1, name);
            s.setInt(2, id);
            s.execute();
        }
        catch (SQLException e) {
            System.out.println("Error: update");
        }
        System.out.println("Customers was update");
    }

    public static void Customers_Remove(int id) {
        try {
            PreparedStatement s = conection.prepareStatement("delete from Customers where id = ?");
            s.setInt(1, id);
            s.execute();
        }
        catch (SQLException e) {
            System.out.println("Error: remove");
        }
        System.out.println("Customers was remove");

    }

    public static void Customers_ReadAll() {
        try {
            Statement statement = conection.createStatement();
            ResultSet result = statement.executeQuery("select * from Customers");
            ResultSetMetaData metaData = result.getMetaData();
            int noCols = metaData.getColumnCount();

            System.out.printf("%-10s\t", "id");
            System.out.printf("%-10s\t", "name");
            System.out.println();
            while (result.next()) {
                for (int i = 1; i <= noCols; i++) {
                    System.out.printf("%-10s\t", result.getObject(i));
                }
                System.out.println();
            }
        }
        catch (SQLException e) {
            System.out.println("Error: Read");
        }
    }
}
