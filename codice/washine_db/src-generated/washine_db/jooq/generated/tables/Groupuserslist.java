/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
import washine_db.jooq.generated.tables.records.GroupuserslistRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Groupuserslist extends TableImpl<GroupuserslistRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>GROUPUSERSLIST</code>
     */
    public static final Groupuserslist GROUPUSERSLIST = new Groupuserslist();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GroupuserslistRecord> getRecordType() {
        return GroupuserslistRecord.class;
    }

    /**
     * The column <code>GROUPUSERSLIST.LaundryPersonId</code>.
     */
    public final TableField<GroupuserslistRecord, String> LAUNDRYPERSONID = createField(DSL.name("LaundryPersonId"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>GROUPUSERSLIST.ParticipantId</code>.
     */
    public final TableField<GroupuserslistRecord, String> PARTICIPANTID = createField(DSL.name("ParticipantId"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>GROUPUSERSLIST.Name</code>.
     */
    public final TableField<GroupuserslistRecord, String> NAME = createField(DSL.name("Name"), SQLDataType.CLOB.nullable(false), this, "");

    private Groupuserslist(Name alias, Table<GroupuserslistRecord> aliased) {
        this(alias, aliased, null);
    }

    private Groupuserslist(Name alias, Table<GroupuserslistRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>GROUPUSERSLIST</code> table reference
     */
    public Groupuserslist(String alias) {
        this(DSL.name(alias), GROUPUSERSLIST);
    }

    /**
     * Create an aliased <code>GROUPUSERSLIST</code> table reference
     */
    public Groupuserslist(Name alias) {
        this(alias, GROUPUSERSLIST);
    }

    /**
     * Create a <code>GROUPUSERSLIST</code> table reference
     */
    public Groupuserslist() {
        this(DSL.name("GROUPUSERSLIST"), null);
    }

    public <O extends Record> Groupuserslist(Table<O> child, ForeignKey<O, GroupuserslistRecord> key) {
        super(child, key, GROUPUSERSLIST);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<GroupuserslistRecord> getPrimaryKey() {
        return Keys.GROUPUSERSLIST__PK_GROUPUSERSLIST;
    }

    @Override
    public List<ForeignKey<GroupuserslistRecord, ?>> getReferences() {
        return Arrays.asList(Keys.GROUPUSERSLIST__FK_GROUPUSERSLIST_PK_USER);
    }

    private transient User _user;

    /**
     * Get the implicit join path to the <code>USER</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.GROUPUSERSLIST__FK_GROUPUSERSLIST_PK_USER);

        return _user;
    }

    @Override
    public Groupuserslist as(String alias) {
        return new Groupuserslist(DSL.name(alias), this);
    }

    @Override
    public Groupuserslist as(Name alias) {
        return new Groupuserslist(alias, this);
    }

    @Override
    public Groupuserslist as(Table<?> alias) {
        return new Groupuserslist(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Groupuserslist rename(String name) {
        return new Groupuserslist(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Groupuserslist rename(Name name) {
        return new Groupuserslist(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Groupuserslist rename(Table<?> name) {
        return new Groupuserslist(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
