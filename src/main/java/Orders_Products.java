import java.sql.*;

/**
 * Created by Daniil on 07.12.2015.
 */
public class Orders_Products {


    public  static  Connection conection;

    public Orders_Products(Connection conection){
        this.conection = conection;
    }

    public static void Orders_Products_Execute(String[] args) throws SQLException {
        switch (args[1]) {
            case "create":
                Orders_Products_Create(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                break;
            case "update":
                Orders_Products_Update(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                break;
            case "delete":
                Orders_Products_Remove(Integer.parseInt(args[2]));
                break;
            case "read":
                Orders_Products_ReadAll();
                break;
            default:
                break;
        }
    }

    public static void Orders_Products_Create(int idOrders_Products, int idOrder, int idProducts) throws SQLException {
        PreparedStatement s = conection.prepareStatement("insert into OrdersProducts values (?,?,?)");
        s.setInt(1, idOrders_Products);
        s.setInt(2, idOrder);
        s.setInt(3, idProducts);
        s.execute();
        System.out.println("Orders_Products was Create");
    }

    public static void Orders_Products_Update(int idOrders_Products, int idOrder, int idProducts) throws SQLException {
        PreparedStatement s = conection.prepareStatement("update OrdersProducts set idOrder = ?,  idProducts = ? where idOrders_Products = ?");
        s.setInt(1, idOrder);
        s.setInt(2, idProducts);
        s.setInt(3, idOrders_Products);
        s.execute();
        System.out.println("Orders_Products was Update");
    }

    public static void Orders_Products_Remove(int  idOrders_Products) throws SQLException {
        PreparedStatement s = conection.prepareStatement("delete from OrdersProducts where OP_ID=?");
        s.setInt(1,  idOrders_Products);
        s.execute();
        System.out.println("Orders_Products was Remove");
    }

    public static void Orders_Products_ReadAll() throws SQLException {
        Statement s = conection.createStatement();
        ResultSet results = s.executeQuery("select D.CustomerName, B.ProductsName from OrdersProducts as A inner join products as B on A.P_ID=B.P_ID inner join orders as C on C.O_ID=A.O_ID inner join customers as D on D.C_ID=C.C_ID");
        ResultSetMetaData metaData = results.getMetaData();
        int noCols = metaData.getColumnCount();
        System.out.printf("%-10s\t", "Customer");
        System.out.printf("%-10s\t", "ProductOrdered");
        System.out.println();
        while (results.next()) {
            for (int i = 1; i <= noCols; i++) {
                System.out.printf("%-10s\t", results.getObject(i));
            }
            System.out.println();
        }
    }
}
