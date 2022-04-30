import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees_javajdbc", "root", ""); // etablir la connexion avec la bd
			Statement stmt = con.createStatement();
			
			// modifier
			System.out.println(stmt.executeUpdate("Update employes set prenom= 'ismail2' where id= 1"));	  // executeUpdate() pour update et insert
			
			// Insertion
			System.out.println(stmt.executeUpdate("INSERT INTO employes VALUES(3, 'HH', 'jj') "));
			
			// afficher
			ResultSet rs =  stmt.executeQuery("select * from employes LIMIT 100");    // on utilise executeQuery() car il doit retourner pls information
			while(rs.next()) {
				System.out.println(rs.getString("nom"));  //  on peut donner soit la position ou nom du colonne
			//	con.close(); // fermer la connection
			}
			
			// modifier avec des parametres
			String ln = "aguero2";
			int id = 2;
			System.out.println(stmt.executeUpdate("Update employes set nom= '" + ln + "' where id=  " + id));	
			
			
			// meme chose que precedent (plus lisible)
			PreparedStatement ps = con.prepareStatement("Update employes set nom= ?  where id= ?");
			ps.setString(1, ln);
			ps.setInt(2, id);
			System.out.println(ps.executeUpdate());
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
