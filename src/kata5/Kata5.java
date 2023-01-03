 package kata5; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;


public class Kata5 {

    private Connection conn;
    public static void main(String[] args) {
        Kata5 kata5 = new Kata5();
        kata5.connect();
        kata5.createNewTable();
        MailListReader mlr = new MailListReader();
        List <String> items = mlr.read("email.txt");
        for (String line : items) {
            kata5.insert(line);
        }
    }

    private Connection connect() {
        this.conn = null;
        try {
            String url = "jdbc:sqlite:kata5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if (this.conn == null) conn.close();
            }catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return this.conn;
    }
    
    public void selectAll(){
        try {
            String sql = "SELECT * FROM PEOPLE";
            if (this.conn == null) conn.close();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t" +
                        rs.getString("Nombre")             + "\t" +
                        rs.getString("Apellido")           + "\t" +
                        rs.getString("Departamento"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
        
    }
    
    public void createNewTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                    + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + " Mail text NOT NULL);";
            if (this.conn == null) conn.close();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void insert(String email) {
        try {
            String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
            if (this.conn == null) conn.close();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
