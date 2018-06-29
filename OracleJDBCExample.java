import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
import java.sql.ResultSet;

public class OracleJDBCExample{

    public static void main(String[] argv) {

        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@mydbinstance.ceag9vgxjz5n.ap-southeast-2.rds.amazonaws.com:1521:ORCL", "dongaws", "Lg123456");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
			System.out.println("You made it, take control your database now!");

		try{
			 Statement stmtObj = connection.createStatement();
//			 String createTableSQL = "CREATE TABLE DBUSER("
//			    + "USER_ID NUMBER(5) NOT NULL, "
//			    + "USERNAME VARCHAR(20) NOT NULL"
//			    + ")";
//			 stmtObj.execute(createTableSQL);
//			 String insertValues="insert into DBUSER values (11111,'dongaws')";
//			 stmtObj.executeUpdate(insertValues);


			String queryString="select * from DBUSER";
			ResultSet resObj = stmtObj.executeQuery(queryString);
			while (resObj.next()) {
				int id = resObj.getInt("USER_ID");
				String name = resObj.getString("USERNAME");
				System.out.println(name+":"+id);
			}
		} catch (SQLException e) {

            e.printStackTrace();
            return;

        }
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}
