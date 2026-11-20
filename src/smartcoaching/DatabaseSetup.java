
package smartcoaching;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author roqayahzaki
 */
public class DatabaseSetup {
    public static void createExerciseTable(){
        String sql =
        """
        CREATE TABLE EXERCISE(
        exercise_id SERIAL PRIMARY KEY,
        exercise_name VARCHAR(100) NOT NULL,
        description TEXT,
        image_url TEXT,
        video_url TEXT
        )
        """;
        
        try(Connection con = DBConnection.getConnection();
        Statement st = con.createStatement()){
            st.executeUpdate(sql);
            System.out.println("EXERCISE table created!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        
        }
    
    public static void insertExcercise(){
        String sql = """
                     INSERT INTO EXERCISE(exercise_name, description, image_url, video_url)
                     VALUES (?, ?, ?, ?)
                     
                     """;
        try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, "Lat Pulldown");
            ps.setString(2, "LPull the bar down towards your chest.");
            ps.setString(3, "https://www.google.com/url?sa=t&source=web&rct=j&url=https%3A%2F%2Fwww.onetoonefit.com%2Fen%2Fthe-lat-pulldown-exercise-essential-for-shoulder-stability&ved=0CBcQjRxqFwoTCMCoxJv2lJgDFQAAAAAdAAAAABBU&opi=89978449");
            ps.setString(4, "https://www.youtube.com/shorts/bNmvKpJSWKM");
            
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void printExercise(){
        String sql = """
                     SELECT * FROM EXERCISE;
                     """;
        
        try(Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)){
            
            while(rs.next()){
                int id = rs.getInt("exercise_id");
                String name = rs.getString("exercise_name");
                String description = rs.getString("description");
                
                System.out.println(id + " | " + name + " | " + description);
                
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateExercise(){
        String sql = """
                     UPDATE EXERCISE
                     SET description = ?
                     WHERE exercise_id= ?      
                     """;
        
        try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, "Pull the bar towards your upper chest while leaning a little backward.");
            ps.setInt(2, 1);
            
            int rows = ps.executeUpdate();
            
            System.out.println(rows + "exercise updated!!");
            
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteExercise(int id){
        String sql = """
                     DELETE FROM EXERCISE WHERE exercise_id = ?
                     """;
        
        try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            
            int rows = ps.executeUpdate();
            System.out.println(rows + " Exercises deleted!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    }

