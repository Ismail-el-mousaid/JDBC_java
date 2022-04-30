package Ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramEx1 {

	try {
        // 1.	Charger le driver JDBC pour MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.	Établir la connexion à la base de données MySQL
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees_javajdbc", "root", "");

        // 3.	Mise à jour des salaires
        Statement stmt = con.createStatement();
        int nbrLignes = stmt.executeUpdate("UPDATE salaries SET salary = 50000 WHERE emp_no IN (SELECT e.emp_no FROM employees e WHERE e.first_name = 'Arif' AND e.last_name = 'Merlo')");
        System.out.println("1. Nombre de lignes mises a jour est " + nbrLignes);

        nbrLignes = stmt.executeUpdate("UPDATE salaries SET salary = 52000 WHERE emp_no IN (SELECT e.emp_no FROM employees e WHERE e.first_name = 'Basil' AND e.last_name = 'Tramer');");
        System.out.println("2. Nombre de lignes mises a jour est " + nbrLignes);

        // 4.	Afficher les noms, prénom et les salaires de tous les employés
        ResultSet rs = stmt.executeQuery("SELECT e.last_name, e.first_name, s.salary FROM employees.employees e, salaries s WHERE e.emp_no = s.emp_no LIMIT 100");

        while (rs.next()) {
            System.out.println(rs.getString("last_name") + " " + rs.getString("first_name") + " => " + rs.getLong("salary"));
        }
        con.close();
    } catch (ClassNotFoundException e) {
        System.out.println("Impossible de charger le connecteur JDBC");
    } catch (SQLException throwables) {
        System.out.println("Une erreur s'est produite pendant la connexion a la BDD");
    }
}
	
	
}
