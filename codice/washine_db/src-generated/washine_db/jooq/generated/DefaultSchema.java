/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import washine_db.jooq.generated.tables.Communityuserslist;
import washine_db.jooq.generated.tables.Groups;
import washine_db.jooq.generated.tables.Invites;
import washine_db.jooq.generated.tables.User;
import washine_db.jooq.generated.tables.Washing;
import washine_db.jooq.generated.tables.Washingoptions;
import washine_db.jooq.generated.tables.Washingparticipation;


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
     * The table <code>COMMUNITYUSERSLIST</code>.
     */
    public final Communityuserslist COMMUNITYUSERSLIST = Communityuserslist.COMMUNITYUSERSLIST;

    /**
     * The table <code>GROUPS</code>.
     */
    public final Groups GROUPS = Groups.GROUPS;

    /**
     * The table <code>INVITES</code>.
     */
    public final Invites INVITES = Invites.INVITES;

    /**
     * The table <code>USER</code>.
     */
    public final User USER = User.USER;

    /**
     * The table <code>WASHING</code>.
     */
    public final Washing WASHING = Washing.WASHING;

    /**
     * The table <code>WASHINGOPTIONS</code>.
     */
    public final Washingoptions WASHINGOPTIONS = Washingoptions.WASHINGOPTIONS;

    /**
     * The table <code>WASHINGPARTICIPATION</code>.
     */
    public final Washingparticipation WASHINGPARTICIPATION = Washingparticipation.WASHINGPARTICIPATION;

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
            Communityuserslist.COMMUNITYUSERSLIST,
            Groups.GROUPS,
            Invites.INVITES,
            User.USER,
            Washing.WASHING,
            Washingoptions.WASHINGOPTIONS,
            Washingparticipation.WASHINGPARTICIPATION
        );
    }
}
