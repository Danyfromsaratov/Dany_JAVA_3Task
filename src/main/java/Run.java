import javax.print.attribute.standard.OrientationRequested;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Daniil on 07.12.2015.
 */
public class Run {
    public static Connection conection;

    public Run(Connection conection){
        this.conection = conection;
    }


    public  void CreateDataBase() {
        Statement statement = null;
        try {
            statement = conection.createStatement();
            statement.execute("create table if not exists [Customers] ([idCostumer] INT PRIMARY KEY, [name] NVARCHAR(50));");
            statement.execute("create table if not exists [Orders] ([idOrders] INT PRIMARY KEY, [idCostumer] INT, FOREIGN KEY(C_ID) REFERENCES Customers(idCostumer));");
            statement.execute("create table if not exists [Products] ([idProducts] INT PRIMARY KEY, [name] NVARCHAR(50));");
            statement.execute("create table if not exists [Orders_Products] ([idOrders_Products] INT PRIMARY KEY, [idOrders] INT, [idProducts] INT, FOREIGN KEY(idOrders) REFERENCES Orders(idOrders), FOREIGN KEY(idProducts) REFERENCES Products(idProducts));");
        }
        catch (SQLException e) {
            System.out.println("Error: fail creating DB");
        }
        System.out.println("Good: success creating DB.");
    }




    public  void CreateMenu() {
        System.out.println("Enter  1 to Create new Costumer");
        System.out.println("Enter  2 to Create new Product");
        System.out.println("Enter  3 to Create new Order");
        System.out.println("Enter  4 to Create new Product_n_n_Order");
        System.out.println("Enter  5 to Show All Costumers");
        System.out.println("Enter  6 to Show All Products");
        System.out.println("Enter  7 to Show All Orders");
        System.out.println("Enter 8 to Show All Product_n_n_Order");
        System.out.println("Enter 9 to Edit Costumer");
        System.out.println("Enter 10 to Edit Product");
        System.out.println("Enter 11 to Edit Order");
        System.out.println("Enter 12 to Edit Product_n_n_Order ");
        System.out.println("Enter 13 to Remove Costumer");
        System.out.println("Enter 14 to Remove Product");
        System.out.println("Enter 15 to Remove Order");
        System.out.println("Enter 16 to Remove Product_n_n_Order");
    }

    public void CreateCostumer(String id, String name){
        Customer c = new Customer(conection);
        String string_command = "Customers create " + id + " " + name;
        String[] parser = string_command.split("\\s+");
        try {
            c.Customers_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditCostumer(String id, String name){
        Customer c = new Customer(conection);
        String string_command = "Customers update " + id + " " + name;
        String[] parser = string_command.split("\\s+");
        try {
            c.Customers_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RemoveCostumer(String id){
        Customer c = new Customer(conection);
        String string_command = "Customers delete " + id;
        String[] parser = string_command.split("\\s+");
        try {
            c.Customers_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void  ShowAllCostumers(){
        Customer c = new Customer(conection);
        String string_command = "Customers read";
        String[] parser = string_command.split("\\s+");
        try {
            c.Customers_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void CreateProduct(String id, String name){
        Product p = new Product(conection);
        String string_command = "Products create " + id + " " + name;
        String[] parser = string_command.split("\\s+");
        try {
            p.Product_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void EditProduct(String id, String name){
        Product p = new Product(conection);
        String string_command = "Products update " + id + " " + name;
        String[] parser = string_command.split("\\s+");
        try {
            p.Product_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RemoveProduct(String id){
        Product p = new Product(conection);
        String string_command = "Products delete " + id;
        String[] parser = string_command.split("\\s+");
        try {
            p.Product_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void  ShowAllProducts() {
        Product p = new Product(conection);
        String string_command = "Products read";
        String[] parser = string_command.split("\\s+");
        try {
            p.Product_Execute(parser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void CreateOrder(String idOrder, String idCostumer){
        Order o = new Order(conection);
        String string_command = "Orders create " + idOrder + " " + idCostumer;
        String[] parser = string_command.split("\\s+");
        try {
            o.Order_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void EditOrder(String idOrder, String idCostumer){
        Order o = new Order(conection);
        String string_command = "Orders update " + idOrder + " " + idCostumer;
        String[] parser = string_command.split("\\s+");
        try {
            o.Order_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RemoveOrder(String idOrder){
        Order o = new Order(conection);
        String string_command = "Orders delete " + idOrder;
        String[] parser = string_command.split("\\s+");
        try {
            o.Order_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void  ShowAllOrders(){
        Order o = new Order(conection);
        String string_command = "Orders read";
        String[] parser = string_command.split("\\s+");
        try {
            o.Order_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void Product_n_n_Order(String id, String idOreder, String idProduct){
        Orders_Products op = new Orders_Products(conection);
        String string_command = "OrdersProducts create " + id + " " + idOreder + " " + idProduct;
        String[] parser = string_command.split("\\s+");
        try {
            op.Orders_Products_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit_Product_n_n_Order(String id, String idOreder, String idProduct){
        Orders_Products op = new Orders_Products(conection);
        String string_command = "OrdersProducts update " + id + " " + idOreder + " " + idProduct;
        String[] parser = string_command.split("\\s+");
        try {
            op.Orders_Products_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Remove_Product_n_n_Order(String id){
        Orders_Products op = new Orders_Products(conection);
        String string_command = "OrdersProducts delete " + id;
        String[] parser = string_command.split("\\s+");
        try {
            op.Orders_Products_Execute(parser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void  ShowAllProduct_n_n_Order() {
        Orders_Products op = new Orders_Products(conection);
        String string_command = "OrdersProducts read";
        String[] parser = string_command.split("\\s+");
        try {
            op.Orders_Products_Execute(parser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
