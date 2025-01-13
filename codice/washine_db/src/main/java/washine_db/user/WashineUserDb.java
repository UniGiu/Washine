package washine_db.user;

import java.security.SecureRandom;
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
	
  public WashineUserDb() {}

  /**
   * @throws SQLException if the query does not go through
   * @param email of the user of whom you want to check the presence on the database
   * @param password of the user you want to authenticate
   * @return true if the user's informations are on the database and the password is correct
   * @return false if the user's informations are not on the database or the password is incorrect
   */
  public boolean authenticateUser(String email, String password) throws SQLException {

    if (this.alreadyAddedUser(email) == false) {
      return false;
    }

    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Record1<String> databaseHashedPassword =
        create
            .select(User.USER.PASSWORD)
            .from(User.USER)
            .where(User.USER.EMAIL.eq(email))
            .fetchOne();

    if (BCrypt.checkpw(password, databaseHashedPassword.getValue(User.USER.PASSWORD))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @throws SQLException if the query does not go through
   * @param id of the user of whom information you want to add to the database
   * @param email of the user of whom information you want to add to the database
   * @param password of the user of whom information you want to add to the database
   * @return true if the user's informations have been successfully added on the database
   * @return false if the user's informations have already been added on the database
   */
  public boolean addUser(String email, String password) throws SQLException {
    if (this.alreadyAddedUser(email) == true) {
      return false;
    } else {
      String id = Long.toString(generateUniqueId());
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

      String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

      create
          .insertInto(User.USER, User.USER.ID, User.USER.EMAIL, User.USER.PASSWORD)
          .values(id, email, hashedPassword)
          .execute();
      return true;
    }
  }

  /**
   * @throws SQLException if the query does not go through
   * @param email of the user of whom you want to check the presence on the database
   * @return true if the user's informations have been successfully added on the database
   * @return false if the user's informations have already been added to the database or the insert
   *     has not gone through correctly
   */
  public boolean alreadyAddedUser(String email) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<UserRecord> user = create.selectFrom(User.USER).where(User.USER.EMAIL.eq(email)).fetch();

    if (user.isNotEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @param email of the user whose id is to retrieve
   * @return the id of the user
   * @throws SQLException if the query does not go through
   */
  public String getUserId(String email) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Record1<String> uid =
        create.select(User.USER.ID).from(User.USER).where(User.USER.EMAIL.eq(email)).fetchOne();

    return uid.getValue(User.USER.ID);
  }

  /**
   * Generates a random number considered unique in our scope, probability of collision 1/2^62
   * Default-constructed SecureRandom instances seed themselves
   *
   * @return a random number
   */
  private long generateUniqueId() {
    SecureRandom secureRandom = new SecureRandom();
    long randomPositiveLong = Math.abs(secureRandom.nextLong());
    return randomPositiveLong;
  }

  /**
   * @throws SQLException if the query does not go through
   * @param userId of the user of whom you want to change the email
   * @param newEmail the new email
   */
  public void updateUserEmail(String userId, String newEmail) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    create
        .update(User.USER)
        .set(User.USER.EMAIL, newEmail)
        .where(User.USER.ID.eq(userId))
        .execute();
  }

  /**
   * @throws SQLException if the query does not go through
   * @param userId of the user of whom you want to change the password
   * @param newPassword the new password
   */
  public void updateUserPassword(String userId, String newPassword) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

    create
        .update(User.USER)
        .set(User.USER.PASSWORD, hashedPassword)
        .where(User.USER.ID.eq(userId))
        .execute();
  }

  /**
   * @throws SQLException if the query does not go through
   * @param id of the user of whom you want to get the email
   * @return the email
   */
  public String getUserEmail(String id) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Record1<String> userEmail =
        create.select(User.USER.EMAIL).from(User.USER).where(User.USER.ID.eq(id)).fetchOne();
    return userEmail.getValue(User.USER.EMAIL);
  }
}
