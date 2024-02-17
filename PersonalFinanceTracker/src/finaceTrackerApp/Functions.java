package finaceTrackerApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Functions {
    Scanner ss = new Scanner(System.in);
    
    public void newCategory(String userName){
        Scanner ss = new Scanner(System.in);

        System.out.println("Enter Category name");
        String cName = ss.nextLine();
        System.out.println("Enter your Budget for this Category");
        int budget = ss.nextInt();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "insert into Category(userName, categoryName, budget, balance) values('"+ userName +"','" +cName+ "',"+budget+","+budget+")";
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
            System.out.println("New Category Added :)");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void viewList(String userName){
        Scanner ss = new Scanner(System.in);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "select (categoryName, budget, balance) from Category where userName = '"+userName+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+ rs.getInt(2)+" "+rs.getInt(3));
            }
            System.out.println();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void addTransaction(String userName){
        Scanner ss = new Scanner(System.in);

        System.out.println("Enter the category name:");
        String cname = ss.nextLine();
        System.out.println("Enter the Amount you spent:");
        int amtSpent = ss.nextInt();
        System.out.println("Enter Description:");
        String des = ss.nextLine();
        ss.next();
        System.out.println("Enter the Date(yyyy-mm-dd):");
        String date = ss.nextLine();
        ss.next();
        int bal = 0;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "insert into Transaction(categoryName, spentAmount, description, dateOfSpent) values ('"+cname+"',"+amtSpent+",'"+des+"','"+date+"')";
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "select balance from Category where categoryName = '"+cname+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            if(rs.next()){
                bal = rs.getInt(1);
            }

        }catch(Exception e){
            System.out.println(e);
        }
        int x = bal - amtSpent;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "update Category set balance = "+x+"where categoryName = '"+cname+"'";
            Statement stm = con.createStatement();
            stm.executeUpdate(query);

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void viewTransaction(String userName){
        Scanner ss = new Scanner(System.in);

        System.out.println("1. View All your Transactions /n 2.View Specific category Transactions");
        System.out.println("Enter your choice:");
        int ch = ss.nextInt();
        if(ch == 1){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

                String query = "select Transaction.* from Category inner join Transaction on Category.categoryName = Transaction.categoryName and Category.userName = '"+userName+"'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                    System.out.println(rs.getString(1)+" "+ rs.getInt(2)+" "+rs.getString(3)+" "+ rs.getString(4));
                }
                System.out.println();

            }catch(Exception e){
                System.out.println(e);
            }
        } else if (ch == 2) {
            System.out.println("Enter the category Name");
            String cname = ss.nextLine();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

                String query = "select transaction.* from Category inner join transaction on Category.categoryName = transaction.categoryName and Category.userName = '"+userName+"' nad transaction.categoryName = '"+cname+"'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+ rs.getString(3));
                }
                System.out.println();

            }catch(Exception e){
                System.out.println(e);
            }

        }

    }
}
