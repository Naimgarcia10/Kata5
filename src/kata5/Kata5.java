 package kata5; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Kata5 {

    public static void main(String[] args) {
        createNewTable();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:kata5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if (conn == null) conn.close();
            }catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";

        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t" +
                rs.getString("Nombre")             + "\t" +
                rs.getString("Apellido")           + "\t" +
                rs.getString("Departamento"));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        String url = "jdbc:sqlite:kata5.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
            + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
            + " Mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
    }
}
