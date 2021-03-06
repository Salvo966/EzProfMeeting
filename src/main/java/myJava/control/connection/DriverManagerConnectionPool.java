package myJava.control.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe per caricamento statico delle connessioni al database
 */
public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnections;

  /**
   * Caricamento driver
   */
  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
    }
  }

  /**
   * Crea la stringa di connessione
   *
   * @return Una stringa di connessione
   * @throws SQLException in caso di errori con il db
   */
  private static synchronized Connection createDBConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "ezprofmeeting";
    String username = "root";
    String password = "root";

    newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);

    newConnection.setAutoCommit(false);
    return newConnection;
  }

  /**
   * Crea una connessione con il database
   *
   * @return Una connessione
   * @throws SQLException in caso di errori con il db
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDBConnection();
    }
    return connection;
  }

  /**
   * Disconnette dal database una certa connessione
   *
   * @param connection la connessione da liberare
   * @throws SQLException in caso di errori con il db
   */
  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }
}




