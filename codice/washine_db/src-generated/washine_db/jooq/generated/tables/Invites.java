/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import washine_db.jooq.generated.DefaultSchema;
import washine_db.jooq.generated.Keys;
import washine_db.jooq.generated.tables.records.InvitesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Invites extends TableImpl<InvitesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INVITES</code>
     */
    public static final Invites INVITES = new Invites();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InvitesRecord> getRecordType() {
        return InvitesRecord.class;
    }

    /**
     * The column <code>INVITES.LaundryPersonId</code>.
     */
    public final TableField<InvitesRecord, String> LAUNDRYPERSONID = createField(DSL.name("LaundryPersonId"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>INVITES.InvitedName</code>.
     */
    public final TableField<InvitesRecord, String> INVITEDNAME = createField(DSL.name("InvitedName"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>INVITES.Code</code>.
     */
    public final TableField<InvitesRecord, String> CODE = createField(DSL.name("Code"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>INVITES.TS</code>.
     */
    public final TableField<InvitesRecord, Integer> TS = createField(DSL.name("TS"), SQLDataType.INTEGER, this, "");

    private Invites(Name alias, Table<InvitesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Invites(Name alias, Table<InvitesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>INVITES</code> table reference
     */
    public Invites(String alias) {
        this(DSL.name(alias), INVITES);
    }

    /**
     * Create an aliased <code>INVITES</code> table reference
     */
    public Invites(Name alias) {
        this(alias, INVITES);
    }

    /**
     * Create a <code>INVITES</code> table reference
     */
    public Invites() {
        this(DSL.name("INVITES"), null);
    }

    public <O extends Record> Invites(Table<O> child, ForeignKey<O, InvitesRecord> key) {
        super(child, key, INVITES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<InvitesRecord> getPrimaryKey() {
        return Keys.INVITES__PK_INVITES;
    }

    @Override
    public List<ForeignKey<InvitesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.INVITES__FK_INVITES_PK_USER);
    }

    private transient User _user;

    /**
     * Get the implicit join path to the <code>USER</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.INVITES__FK_INVITES_PK_USER);

        return _user;
    }

    @Override
    public Invites as(String alias) {
        return new Invites(DSL.name(alias), this);
    }

    @Override
    public Invites as(Name alias) {
        return new Invites(alias, this);
    }

    @Override
    public Invites as(Table<?> alias) {
        return new Invites(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Invites rename(String name) {
        return new Invites(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Invites rename(Name name) {
        return new Invites(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Invites rename(Table<?> name) {
        return new Invites(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
