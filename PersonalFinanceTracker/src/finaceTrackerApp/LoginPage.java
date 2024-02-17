package finaceTrackerApp;

import java.sql.*;
import java.util.Scanner;

public class LoginPage extends HomePage {


    public void registerOrLoginPage() {
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter your choice /n 1. Register /n 2. Login /n 3.Exit");
        int ch = ss.nextInt();
        if(ch == 1) register();
        else if(ch == 2) login();
        else if(ch == 3){

        }
        else{
            System.out.println("Wrong Input!!! Please give valid input...");
            registerOrLoginPage();
        }
    }

    public void register() {
        Scanner ss = new Scanner(System.in);
        // Here the user going to give username , password and mail id
        System.out.print("Enter the User Name: ");
        String name = ss.next();
        //check whether the username already exits or not
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "select * from Users where userName = '"+name+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            if(rs.next()){
                System.out.println("User Name already Exists!!!");
                register();
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.print("Enter your Mail ID: ");
        String id = ss.next();
        //check whether the MailId already exists
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "select * from Users where mailID = '"+id+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            if(rs.next()){
                System.out.println("Mail ID already Exists!!!");
                register();
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.print("Enter the Password: ");
        String pw = ss.next();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");

            String query = "insert into Users(userName, password, mailID) values('"+ name +"','" +pw+ "','"+id+"')";
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
            System.out.println("Registration completed Successfully :)");
            registerOrLoginPage();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void login() {
        Scanner ss = new Scanner(System.in);
        System.out.print("Enter the User Name: ");
        String name = ss.next();
        System.out.print("Enter the Password: ");
        String pw = ss.next();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FinanceTracker?characterEncoding=latin1", "root", "MySql@1312");
            String query = "select * from Users where userName = '"+name+"' and  password = '" + pw +"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                System.out.println("Logged In Successfully :)");
                homePage(name);
            }
            else{
                System.out.println("Invalid User Name Password!!!");
                registerOrLoginPage();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }


    }

}
