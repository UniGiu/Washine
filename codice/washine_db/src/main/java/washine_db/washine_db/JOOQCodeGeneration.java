package washine_db.washine_db;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;

/*
 * Class used to generate jooq's classes for the database interaction
 */
public class JOOQCodeGeneration {

  /*
   * database's relative URL
   */
  public static final String DB_URL = "jdbc:sqlite:" + "../washine_db/db/washineDB.db";

  public static void main(String[] args) {
    Jdbc JDBC = new Jdbc().withDriver("org.sqlite.JDBC").withUrl(DB_URL);

    Database database =
        new Database()
            .withName("org.jooq.meta.sqlite.SQLiteDatabase")
            .withIncludes(".*")
            .withExcludes("");

    Target target =
        new Target().withPackageName("washine_db.jooq.generated").withDirectory("src-generated/");

    Generator generator = new Generator().withDatabase(database).withTarget(target);

    Configuration configuration = new Configuration().withJdbc(JDBC).withGenerator(generator);

    try {
      GenerationTool.generate(configuration);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
