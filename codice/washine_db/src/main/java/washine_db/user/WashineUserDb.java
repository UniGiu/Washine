package washine_db.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.bcrypt.BCrypt;

import washine_db.jooq.generated.tables.User;
import washine_db.jooq.generated.tables.records.UserRecord;
import washine_db.washine_db.JOOQCodeGeneration;

/** User class used to interact with the database */
public class WashineUserDb implements WashineUserDbIf {

  /**
   * @throws SQLException
   * @param email of the user of whom you want to check the presence on the database
   * @param password of the user you want to authenticate
   * @return true if the user's informations are on the database and the password is correct
   * @return false if the user's informations are not on the database or the password is incorrect
   */
  public boolean authenticateUser(String email, String password) throws SQLException {

    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    if (this.alreadyAddedUser(email) == false) {
      return false;
    }
    Result<Record1<String>> databaseHashedPassword =
        create.select(User.USER.PASSWORD).from(User.USER).where(User.USER.EMAIL.eq(email)).fetch();

    if (BCrypt.checkpw(password, databaseHashedPassword.format())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @throws SQLException
   * @param email of the user of whom information you want to add to the database
   * @param password of the user of whom information you want to add to the database
   * @return true if the user's informations have been successfully added on the database
   * @return false if the user's informations have already been added on the database
   */
  public boolean addUser(String email, String password) throws SQLException {
    if (this.alreadyAddedUser(email) == true) {
      return false;
    } else {
      // TODO insert on the database
      return true;
    }
  }

  /**
   * @throws SQLException
   * @param email of the user of whom you want to check the presence on the database
   * @return true if the user's informations have been successfully added on the database
   * @return false if the user's informations have already been added to the database or the insert
   *     has not gone through correctly
   */
  public boolean alreadyAddedUser(String email) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<UserRecord> user = create.selectFrom(User.USER).where(User.USER.EMAIL.eq(email)).fetch();

    if (user != null) {
      return true;
    } else {
      return false;
    }
  }
}
