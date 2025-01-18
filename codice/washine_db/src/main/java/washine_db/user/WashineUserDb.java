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
  public static final int ADMIN_LEVEL = 9;

  public WashineUserDb() {
    /*  this constructor is empty */
  }

  /**
   * Authenticate the user identity on the database
   *
   * @throws SQLException if the query does not go through
   * @param email of the user of whom you want to check the presence on the database
   * @param password of the user you want to authenticate
   * @return true if the user's informations are on the database and the password is correct or
   *     false if the user's informations are not on the database or the password is incorrect
   */
  public boolean authenticateUser(String email, String password) throws SQLException {

    if (!this.alreadyAddedUser(email)) {
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

    return BCrypt.checkpw(password, databaseHashedPassword.getValue(User.USER.PASSWORD));
  }

  /**
   * Add a user's record to the database
   *
   * @throws SQLException if the query does not go through
   * @param id of the user of whom information you want to add to the database
   * @param email of the user of whom information you want to add to the database
   * @param password of the user of whom information you want to add to the database
   * @return true if the user's informations have been successfully added on the database or false
   *     if the user's informations have already been added on the database
   */
  public boolean addUser(String email, String password) throws SQLException {
    if (this.alreadyAddedUser(email)) {
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
   * Check if a user has already been added to the database before
   *
   * @throws SQLException if the query does not go through
   * @param email of the user of whom you want to check the presence on the database
   * @return true if the user's informations have been successfully added on the database or false
   *     if the user's informations have already been added to the database or the insert has not
   *     gone through correctly
   */
  public boolean alreadyAddedUser(String email) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<UserRecord> user = create.selectFrom(User.USER).where(User.USER.EMAIL.eq(email)).fetch();

    return user.isNotEmpty();
  }

  /**
   * Get the user's id through his email
   *
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
    return Math.abs(secureRandom.nextLong());
  }

  /**
   * Update a user's email through his id
   *
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
   * Update a user's password through his id
   *
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
   * Get the user's email through his id
   *
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

  /**
   * Used by an admin to block a user
   *
   * @throws SQLException if the query does not go through
   * @param userId id of the user the admin wants to block
   */
  public void blockUser(String userId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    create.update(User.USER).set(User.USER.BLOCKED, true).where(User.USER.ID.eq(userId)).execute();
  }

  /**
   * Used by an admin to unblock a user
   *
   * @throws SQLException if the query does not go through
   * @param userId id of the user the admin wants to unblock
   */
  public void unblockUser(String userId) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    create.update(User.USER).set(User.USER.BLOCKED, false).where(User.USER.ID.eq(userId)).execute();
  }

  /**
   * Checks if user is an admin through his id
   *
   * @throws SQLException if the query does not go through
   * @param id id of the user you want to check
   * @return true if the user is an admin or false if he's not
   */
  public boolean isAdmin(String id) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    Result<UserRecord> admin =
        create
            .selectFrom(User.USER)
            .where(User.USER.ID.eq(id).and(User.USER.LEVEL.eq(ADMIN_LEVEL)))
            .fetch();
    return admin.isNotEmpty();
  }

  public Result<UserRecord> getBlockedUsers() throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    return create.selectFrom(User.USER).where(User.USER.BLOCKED.eq(true)).fetch();
  }

  /**
   * Checks if a user is blocked through his id
   *
   * @throws SQLException
   * @param id id of the user you want to check
   * @return true if the user is blocked or false if it is not
   */
  public boolean isBlocked(String id) throws SQLException {
    Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<UserRecord> blockedUser =
        create
            .selectFrom(User.USER)
            .where(User.USER.ID.eq(id).and(User.USER.BLOCKED.eq(true)))
            .fetch();

    return blockedUser.isNotEmpty();
  }
}
