/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import washine_db.jooq.generated.tables.Gruppo;
import washine_db.jooq.generated.tables.Inviti;
import washine_db.jooq.generated.tables.Lavaggio;
import washine_db.jooq.generated.tables.Listautentigruppo;
import washine_db.jooq.generated.tables.Opzionelavaggio;
import washine_db.jooq.generated.tables.Partecipazionelavaggio;
import washine_db.jooq.generated.tables.Utente;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>GRUPPO</code>.
     */
    public final Gruppo GRUPPO = Gruppo.GRUPPO;

    /**
     * The table <code>INVITI</code>.
     */
    public final Inviti INVITI = Inviti.INVITI;

    /**
     * The table <code>LAVAGGIO</code>.
     */
    public final Lavaggio LAVAGGIO = Lavaggio.LAVAGGIO;

    /**
     * The table <code>LISTAUTENTIGRUPPO</code>.
     */
    public final Listautentigruppo LISTAUTENTIGRUPPO = Listautentigruppo.LISTAUTENTIGRUPPO;

    /**
     * The table <code>OPZIONELAVAGGIO</code>.
     */
    public final Opzionelavaggio OPZIONELAVAGGIO = Opzionelavaggio.OPZIONELAVAGGIO;

    /**
     * The table <code>PARTECIPAZIONELAVAGGIO</code>.
     */
    public final Partecipazionelavaggio PARTECIPAZIONELAVAGGIO = Partecipazionelavaggio.PARTECIPAZIONELAVAGGIO;

    /**
     * The table <code>UTENTE</code>.
     */
    public final Utente UTENTE = Utente.UTENTE;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Gruppo.GRUPPO,
            Inviti.INVITI,
            Lavaggio.LAVAGGIO,
            Listautentigruppo.LISTAUTENTIGRUPPO,
            Opzionelavaggio.OPZIONELAVAGGIO,
            Partecipazionelavaggio.PARTECIPAZIONELAVAGGIO,
            Utente.UTENTE
        );
    }
}
