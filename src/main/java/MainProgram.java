/**
 * Created by Илья on 28.11.2015.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainProgram {
    enum State {
        work, stop
    }
    public static Connection conection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conection = DriverManager.getConnection("jdbc:sqlite:MyDB.db");

        Run r = new Run(conection);
        r.CreateDataBase();
        System.out.println();
        r.CreateMenu();
        System.out.println();
        System.out.println("Let's command");

        Scanner sc = new Scanner(System.in);
        String userCommand;
        State currentState = State.work;
        while (currentState != State.stop) {
            userCommand = sc.nextLine();
            Scanner scaner = new Scanner(System.in);

            String id;
            String name;

            switch (userCommand) {
                case "1":
                    currentState = State.work;
                    System.out.println("Enter id");
                    id = scaner.nextLine();
                    System.out.println("Enter name");
                    name = scaner.nextLine();
                    r.CreateCostumer(id, name);
                    break;
                case "2":
                    currentState = State.work;
                    System.out.println("Enter id");
                    id = scaner.nextLine();
                    System.out.println("Enter name");
                    name = scaner.nextLine();
                    r.CreateProduct(id, name);
                    break;
                case "3":
                    currentState = State.work;
                    System.out.println("Enter idOrder");
                    id = scaner.nextLine();
                    System.out.println("Enter name");
                    String idCostumer = scaner.nextLine();
                    r.CreateOrder(id, idCostumer);
                case "4":
                    currentState = State.work;
                    System.out.println("Enter Product_n_n_Order");
                    id = scaner.nextLine();
                    System.out.println("Enter idOrder ");
                    String idOrder = scaner.nextLine();
                    System.out.println("Enter idProduct ");
                    String idProduct = scaner.nextLine();
                    r.Product_n_n_Order(id, idOrder, idProduct);
                    break;
                case "5":
                    currentState = State.work;
                    r.ShowAllCostumers();
                    break;
                case "6":
                    currentState = State.work;
                    r.ShowAllProducts();
                    break;
                case "7":
                    currentState = State.work;
                    r.ShowAllOrders();
                    break;
                case "8":
                    currentState = State.work;
                   r.ShowAllProduct_n_n_Order();
                    break;
                case "9":
                    currentState = State.work;
                    System.out.println("Enter idCostumer");
                    id = scaner.nextLine();
                    System.out.println("Edit name ");
                    name = scaner.nextLine();
                   r.EditCostumer(id, name);
                    break;
                case "10":
                    currentState = State.work;
                    System.out.println("Enter idProduct");
                    id = scaner.nextLine();
                    System.out.println("Edit name ");
                    name = scaner.nextLine();
                    r.EditProduct(id, name);
                    break;
                case "11":
                    currentState = State.work;
                    System.out.println("Enter idOrder: ");
                    id = scaner.nextLine();
                    System.out.println("Enter new idCostumer");
                    String isCostumer = scaner.nextLine();
                    r.EditOrder(id, isCostumer);
                    break;
                case "12":
                    currentState = State.work;
                    System.out.println("Enter id Product_n_n_Order ");
                    id = scaner.nextLine();
                    System.out.println("Enter idOrder:");
                    idOrder = scaner.nextLine();
                    System.out.println("Enter Product:");
                    idProduct = scaner.nextLine();
                    r.Edit_Product_n_n_Order(id, idOrder, idProduct);
                    break;
                case "13":
                    currentState = State.work;
                    System.out.println("Enter idCostumer");
                    id = scaner.nextLine();
                    r.RemoveCostumer(id);
                    break;
                case "14":
                    currentState = State.work;
                    System.out.println("Enter idProduct");
                    id = scaner.nextLine();
                    r.RemoveProduct(id);
                    break;
                case "15":
                    currentState = State.work;
                    System.out.println("Enter idOrder");
                    id = scaner.nextLine();
                    r.RemoveOrder(id);
                    break;
                case "16":
                    currentState = State.work;
                    System.out.println("Enter id Product_n_n_Order");
                    id = scaner.nextLine();
                    r.Remove_Product_n_n_Order(id);
                    break;
                default:
                    currentState = State.stop;
                    break;
            }
        }
    }
}

