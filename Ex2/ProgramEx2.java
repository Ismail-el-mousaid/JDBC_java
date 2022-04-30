package Ex2;

public class ProgramEx2 {

	*
    * ------------------------------------------------
    * Correction de l'exercice 02
    * ------------------------------------------------
    */

   /* 1.	Écrire une requête paramétrée qui permet de faire une insertion dans la table employees */
   public static void createEmployee(
           int emp_no,
           Date birth_date,
           String first_name,
           String last_name,
           String gender,
           Date hire_date
   ) {
       try {
           // 1.	Charger le driver JDBC pour MySQL
           Class.forName("com.mysql.cj.jdbc.Driver");

           // 2.	Établir la connexion à la base de données MySQL
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");

           // 3.	Mise à jour des salaires
           PreparedStatement ps = con.prepareStatement("INSERT INTO employees.employees(emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES (?, ?, ?, ?, ?, ?)");

           ps.setInt(1, emp_no);
           ps.setDate(2, birth_date);
           ps.setString(3, first_name);
           ps.setString(4, last_name);
           ps.setString(5, gender);
           ps.setDate(6, hire_date);

           con.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Impossible de charger le connecteur JDBC");
       } catch (SQLException throwables) {
           System.out.println("Une erreur s'est produite pendant la connexion a la BDD");
       }
   }

   /* 2.	Écrire une requête paramétrée qui permet de faire une mise à jour des salaires des employés selon le numéro d’employés */
   public static void updateSalary(
           int emp_no,
           Double salary
   ) {
       try {
           // 1.	Charger le driver JDBC pour MySQL
           Class.forName("com.mysql.cj.jdbc.Driver");

           // 2.	Établir la connexion à la base de données MySQL
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");

           // 3.	Mise à jour des salaires
           PreparedStatement ps = con.prepareStatement("UPDATE salaries SET salary = ? WHERE emp_no = ?");

           ps.setDouble(1, salary);
           ps.setInt(2, emp_no);

           ps.executeUpdate();

           con.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Impossible de charger le connecteur JDBC");
       } catch (SQLException throwables) {
           System.out.println("Une erreur s'est produite pendant la connexion a la BDD");
       }
   }

   /* 3.	Écrire une requête qui affiche les employés selon leur emploi */
   public static void showEmployeesByDept(
           String dept_no
   ) {
       try {
           // 1.	Charger le driver JDBC pour MySQL
           Class.forName("com.mysql.cj.jdbc.Driver");

           // 2.	Établir la connexion à la base de données MySQL
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");

           // 3.	Mise à jour des salaires
           PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE emp_no IN (SELECT DISTINCT emp_no FROM dept_emp WHERE dept_no = ?)");

           ps.setString(1, dept_no);
           ResultSet rs = ps.executeQuery();

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

   public static void main(String[] args) {

   }

}
