
package smartcoaching;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author roqayahzaki
 */
public class SmartCoaching {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {

        try{
   Connection con = DBConnection.getConnection();
            System.out.println("connected!!!");
            
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        //DatabaseSetup.createExerciseTable();
        //DatabaseSetup.insertExcercise();
        //DatabaseSetup.printExercise();
        //DatabaseSetup.updateExercise();
        //DatabaseSetup.deleteExercise(1);
        
        
        
    }
    
}
