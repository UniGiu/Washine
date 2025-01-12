package interfacce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.jooq.generated.tables.Utente;
import washine_db.jooq.generated.tables.records.UtenteRecord;
import washine_db.washine_db.GenerazioneCodiceJOOQ;

public interface UtenteIf {

  /**
   * @param email email dell'utente che si vuole cercare nel database
   * @return L'utente con l'email specificata
   * @throws SQLException
   */
  public static Result<UtenteRecord> getUtente(String email) throws SQLException {

    Connection conn = DriverManager.getConnection(GenerazioneCodiceJOOQ.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    Result<UtenteRecord> utente =
        create.selectFrom(Utente.UTENTE).where(Utente.UTENTE.EMAIL.eq(email)).fetch();

    return utente;
  }

  /**
   * @param u utente da inserire nel database
   * @return risultato dell'operazione
   * @throws SQLException
   */
  public static int insertUtente(Utente u) throws SQLException {

    Connection conn = DriverManager.getConnection(GenerazioneCodiceJOOQ.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

    int risultato =
        create
            .insertInto(Utente.UTENTE)
            .values(u.id, u.email, u.password, u.livello, u.bloccato)
            .execute();

    return risultato;
  }

  /**
   * @param u utente la cui presenza nel database va verificata
   * @return true se l'utente è presente, altrimenti false
   * @throws SQLException
   */
  public static boolean presenzaUtente(Utente u) throws SQLException {
    Connection conn = DriverManager.getConnection(GenerazioneCodiceJOOQ.DB_URL);
    DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
    
    Result<UtenteRecord> utente =
        create.selectFrom(Utente.UTENTE).where(Utente.UTENTE.EMAIL.eq(u.email)).fetch();
    
    if (utente.equals(null)) {
    	return true;
    }
    else {
    	return false;
    }
  }
}
