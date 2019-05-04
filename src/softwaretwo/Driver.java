/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaretwo;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Driver {
    
        static String driver = "com.mysql.cj.jdbc.Driver";
        static String db = "U05mdB";
        static String url = "jdbc:mysql://52.206.157.109/" + db;
        static String user = "U05mdB";
        static String pass = "53688546380";
        static private String currentAdminName;
        static Connection myConn;
        
        static Customer carryCustomer;

    public static Customer getCarryCustomer() {
        return carryCustomer;
    }

    public static void setCarryCustomer(Customer carryCustomer) {
        Driver.carryCustomer = carryCustomer;
    }
    
    public static void nullOutCarryCustomer(){
        Driver.carryCustomer = null;
    }
    
    public static void getConnection() throws SQLException, ClassNotFoundException{
            Class.forName(driver);
            myConn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database: " + db);
    }

    
    public static void main(String[] args){
        
        
         
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            myConn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database: " + db);
            
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Query
            ResultSet myRs = myStmt.executeQuery("select * from user");
            
            //4. Process the result
           System.out.println("what we got in return");
            while(myRs.next()){
                System.out.println(myRs.getString("userName"));
            }
            
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    
    public static ResultSet queryAndReturn(String query){
        //System.out.println("Query And Return with Query: " + query);
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            if(myConn == null){
                 Connection myConn = DriverManager.getConnection(url,user,pass);
                 System.out.println("Reconnected to database: " + db);
            }
                        
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Query
            ResultSet myRs = myStmt.executeQuery(query);
            
            //4. Return the result
           //System.out.println("what we got in return");
           return myRs; 
            
        }
        catch(Exception exc){
            exc.printStackTrace();
            return null;
        }
        
    }
    
    
    public static void queryNoReturn(String query){
        System.out.println("Query No Return: " + query);
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            if(myConn == null){
                 Connection myConn = DriverManager.getConnection(url,user,pass);
                 System.out.println("Reconnected to database: " + db);
            }
            
            
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Statment
            //myStmt.executeQuery(query);
            myStmt.execute(query);   
        }
        catch(Exception exc){
            exc.printStackTrace();

        }
        
    }
    
    //Used to check if a value is present for given column
    public static boolean queryCheckIfExists(String query, String stringToCheck, String colToCheck) throws SQLException{
        System.out.println("Query check if Exits value: " + stringToCheck);
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            if(myConn == null){
                 Connection myConn = DriverManager.getConnection(url,user,pass);
                 System.out.println("Reconnected to database: " + db);
            }
           
           
            
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Query
            ResultSet myRs = myStmt.executeQuery(query);
            
            //4. Return the result
            
            
           
           while(myRs.next()){
               if(myRs.getString(colToCheck).equals(stringToCheck)){
                   return true;
               }
           }
        }
        catch(Exception exc){
            exc.printStackTrace();
            
        }
        return false;
    }
    
     public static int queryCheckIfExistsWithValue(String query, String stringToCheck, String colToCheck) throws SQLException{
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            if(myConn == null){
                 Connection myConn = DriverManager.getConnection(url,user,pass);
                 System.out.println("Reconnected to database: " + db);
            }
           
           
            
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Query
            ResultSet myRs = myStmt.executeQuery(query);
            
            //4. Return the result
            
            
           
           while(myRs.next()){
               if(myRs.getString(colToCheck).equals(stringToCheck)){
                   return myRs.getInt(colToCheck);
               }
           }
        }
        catch(Exception exc){
            exc.printStackTrace();
            
        }
        return Integer.MAX_VALUE;
    }
    
    public static int getIdOfValue(String query, String colToCheck){
        try{
            //1.Get a connection to the database
            
            Class.forName(driver);
            if(myConn == null){
                 Connection myConn = DriverManager.getConnection(url,user,pass);
                 System.out.println("Reconnected to database: " + db);
            }
            
            
            //2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            //3. Execute SQL Query
            ResultSet myRs = myStmt.executeQuery(query);
            
            //4. Return the result
           
           while(myRs.next()){
               if(myRs.getString(colToCheck) != null){
                   return myRs.getInt(colToCheck);
               }
           }
        }
        catch(Exception exc){
            exc.printStackTrace();
            
        }
        return 0;
    }
    
    
    
    
   
    
    public static void setCurrentAdmin(String name){
        System.out.println("Current Admin User is: " + name);
        currentAdminName = name;
    }
    
    public static String getCurrentAdmin(){
        return currentAdminName;
    }
    
}
