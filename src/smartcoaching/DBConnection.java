
package smartcoaching;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author roqayahzaki
 */
public class DBConnection {
    private static final String URL =
    "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    
    private static final String USER =
            "postgres.fplpyguhecxiguhyagxh";
    
    private static final String PASSWORD =
            "Smarty@67$67";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
