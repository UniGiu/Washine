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
import washine_db.jooq.generated.tables.records.WashingparticipationRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Washingparticipation extends TableImpl<WashingparticipationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>WASHINGPARTICIPATION</code>
     */
    public static final Washingparticipation WASHINGPARTICIPATION = new Washingparticipation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WashingparticipationRecord> getRecordType() {
        return WashingparticipationRecord.class;
    }

    /**
     * The column <code>WASHINGPARTICIPATION.WashingId</code>.
     */
    public final TableField<WashingparticipationRecord, String> WASHINGID = createField(DSL.name("WashingId"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGPARTICIPATION.ParticipantId</code>.
     */
    public final TableField<WashingparticipationRecord, String> PARTICIPANTID = createField(DSL.name("ParticipantId"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGPARTICIPATION.Load</code>.
     */
    public final TableField<WashingparticipationRecord, Double> LOAD = createField(DSL.name("Load"), SQLDataType.DOUBLE, this, "");

    private Washingparticipation(Name alias, Table<WashingparticipationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Washingparticipation(Name alias, Table<WashingparticipationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>WASHINGPARTICIPATION</code> table reference
     */
    public Washingparticipation(String alias) {
        this(DSL.name(alias), WASHINGPARTICIPATION);
    }

    /**
     * Create an aliased <code>WASHINGPARTICIPATION</code> table reference
     */
    public Washingparticipation(Name alias) {
        this(alias, WASHINGPARTICIPATION);
    }

    /**
     * Create a <code>WASHINGPARTICIPATION</code> table reference
     */
    public Washingparticipation() {
        this(DSL.name("WASHINGPARTICIPATION"), null);
    }

    public <O extends Record> Washingparticipation(Table<O> child, ForeignKey<O, WashingparticipationRecord> key) {
        super(child, key, WASHINGPARTICIPATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<WashingparticipationRecord> getPrimaryKey() {
        return Keys.WASHINGPARTICIPATION__PK_WASHINGPARTICIPATION;
    }

    @Override
    public List<ForeignKey<WashingparticipationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.WASHINGPARTICIPATION__FK_WASHINGPARTICIPATION_PK_WASHING, Keys.WASHINGPARTICIPATION__FK_WASHINGPARTICIPATION_PK_USER);
    }

    private transient Washing _washing;
    private transient User _user;

    /**
     * Get the implicit join path to the <code>WASHING</code> table.
     */
    public Washing washing() {
        if (_washing == null)
            _washing = new Washing(this, Keys.WASHINGPARTICIPATION__FK_WASHINGPARTICIPATION_PK_WASHING);

        return _washing;
    }

    /**
     * Get the implicit join path to the <code>USER</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.WASHINGPARTICIPATION__FK_WASHINGPARTICIPATION_PK_USER);

        return _user;
    }

    @Override
    public Washingparticipation as(String alias) {
        return new Washingparticipation(DSL.name(alias), this);
    }

    @Override
    public Washingparticipation as(Name alias) {
        return new Washingparticipation(alias, this);
    }

    @Override
    public Washingparticipation as(Table<?> alias) {
        return new Washingparticipation(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingparticipation rename(String name) {
        return new Washingparticipation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingparticipation rename(Name name) {
        return new Washingparticipation(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingparticipation rename(Table<?> name) {
        return new Washingparticipation(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, Double> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super Double, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super Double, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
