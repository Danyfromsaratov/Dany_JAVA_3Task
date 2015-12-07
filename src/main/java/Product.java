import java.sql.*;

/**
 * Created by Daniil on 07.12.2015.
 */
public class Product {

    public  static  Connection conection;

    public Product(Connection conection){
        this.conection = conection;
    }


    public static void Product_Execute(String[] args) throws SQLException {
        switch (args[1]) {
            case "create":
                Product_Create(Integer.parseInt(args[2]), args[3]);
                break;
            case "update":
                Product_Update(Integer.parseInt(args[2]), args[3]);
                break;
            case "delete":
                Product_Remove(Integer.parseInt(args[2]));
                break;
            case "read":
                Products_ReadAll();
                break;
            default:
                break;
        }
    }

    public static void Product_Create(int id, String name) throws SQLException {
        PreparedStatement s = conection.prepareStatement("insert into Products values (?,?)");
        s.setInt(1,id);
        s.setString(2, name);
        s.execute();
        System.out.println("Product was create");
    }

    public static void Product_Update(int id, String name) throws SQLException {
        PreparedStatement s = conection.prepareStatement("update Products set ProductsName = ? where id = ?");
        s.setString(1, name);
        s.setInt(2, id);
        System.out.println("Product was update");
    }

    public static void Product_Remove(int id) throws SQLException {
        PreparedStatement s = conection.prepareStatement("delete from Products where id = ?");
        s.setInt(1, id);
        s.execute();
        System.out.println("Product was remove");
    }

    public static void Products_ReadAll() throws SQLException {
        Statement s = conection.createStatement();
        ResultSet results = s.executeQuery("select * from Products");
        ResultSetMetaData metaData = results.getMetaData();
        int noCols = metaData.getColumnCount();
        System.out.printf("%-10s\t", "id");
        System.out.printf("%-10s\t", "name");
        System.out.println();
        while (results.next()) {
            for (int i = 1; i <= noCols; i++) {
                System.out.printf("%-10s\t", results.getObject(i));
            }
            System.out.println();
        }
    }
}
