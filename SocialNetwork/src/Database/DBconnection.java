package Database;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class DBconnection {



    static Connection cnx;
    static Statement st;
    static ResultSet rst;

    public static Connection connectionDB() {
    	try{		
    		Driver dr=new Driver();
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url="jdbc:mysql://localhost:3306/reseaus";
    	    Connection cnx =DriverManager.getConnection(url,"root","");
    	    System.out.println("connecté avec succés");
    	    return cnx;
    		   }
    	    catch(Exception exp){
    			System.out.println(exp);
    			return null;
    		  }
    		}



    /*public static void main(String[] args) {
        try{
            cnx=connectionDB();
            st=cnx.createStatement();
            rst=st.executeQuery("SELECT * FROM groupe");
            while(rst.next()){
                System.out.print(rst.getString("nomgroupe")+"\t");

            }
        }catch(Exception ex){
            ex.printStackTrace();}*/

    }








