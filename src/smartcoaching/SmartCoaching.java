
package smartcoaching;
import smartcoaching.frames.WelcomeFrame;
import smartcoaching.frames.AccountTypeFrame;
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
        WelcomeFrame w = new WelcomeFrame();
//        w.show();
AccountTypeFrame a = new AccountTypeFrame();
a.show();
        //DatabaseSetup.createExerciseTable();
        //DatabaseSetup.insertExcercise();
        //DatabaseSetup.printExercise();
        //DatabaseSetup.updateExercise();
        //DatabaseSetup.deleteExercise(1);
        
        
        
    }
    
}
