import java.sql.*;

/**
 * Created by Daniil on 07.12.2015.
 */
public class Order {
    public static Connection conection;

    public Order(Connection conection){
        this.conection = conection;

    }
    public static void Order_Execute(String[] args) throws SQLException {
        switch (args[1]) {
            case "create":
                Order_Create(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                break;
            case "update":
                Order_Update(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                break;
            case "delete":
                Order_Remove(Integer.parseInt(args[2]));
                break;
            case "read":
                Order_ReadAll();
                break;
            default:
                break;
        }
    }

    public static void Order_Create(int idOrders, int idCostumers) throws SQLException {
        PreparedStatement s = conection.prepareStatement("insert into Orders values (?,?)");
        s.setInt(1, idOrders);
        s.setInt(2, idCostumers);
        s.execute();
        System.out.println("Order was create");
    }

    public static void Order_Update(int idOrders, int idCostumers) throws SQLException {
        PreparedStatement s = conection.prepareStatement("update Orders set idOrders = ? where idCostumers = ?");
        s.setInt(1, idCostumers);
        s.setInt(2, idOrders);
        s.execute();
        System.out.println("Order was update");
    }

    public static void Order_Remove(int id) throws SQLException {
        PreparedStatement s = conection.prepareStatement("delete from Orders where id=?");
        s.setInt(1, id);
        s.execute();
        System.out.println("Order was remove");
    }

    public static void Order_ReadAll() throws SQLException {
        Statement s = conection.createStatement();
        ResultSet result = s.executeQuery("select B.idOrders, A.CustomerName from orders as B inner join Customers as A on A.idCostumers = B.idCostumers");
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.printf("%-10s\t", "idOrders");
        System.out.printf("%-10s\t", "Customer");
        System.out.println();
        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-10s\t", result.getObject(i));
            }
            System.out.println();
        }
    }

}

