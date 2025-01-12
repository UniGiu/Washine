package washine_db.washine_db;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;

/*
 * Classe usata per generare le classi jooq usate per interagire con il database
 */
public class GenerazioneCodiceJOOQ {

  /*
   * URL relativo del database (che si trova nella cartella db)
   */
  public static String DB_URL = "jdbc:sqlite:" + "../washine_db/db/washineDB.db";

  public static void main(String[] args) {
    Jdbc JDBC = new Jdbc()
    		.withDriver("org.sqlite.JDBC")
    		.withUrl(DB_URL);

    Database database =
        new Database()
            .withName("org.jooq.meta.sqlite.SQLiteDatabase")
            .withIncludes(".*")
            .withExcludes("");

    Target target =
        new Target()
        .withPackageName("washine_db.jooq.generated")
        .withDirectory("src-generated/");

    Generator generator = new Generator()
    		.withDatabase(database)
    		.withTarget(target);

    Configuration configuration = new Configuration()
    		.withJdbc(JDBC)
    		.withGenerator(generator);

    try {
      GenerationTool.generate(configuration);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
