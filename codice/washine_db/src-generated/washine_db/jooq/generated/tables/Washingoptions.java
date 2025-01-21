/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function22;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row22;
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
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Washingoptions extends TableImpl<WashingoptionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>WASHINGOPTIONS</code>
     */
    public static final Washingoptions WASHINGOPTIONS = new Washingoptions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WashingoptionsRecord> getRecordType() {
        return WashingoptionsRecord.class;
    }

    /**
     * The column <code>WASHINGOPTIONS.WashingId</code>.
     */
    public final TableField<WashingoptionsRecord, String> WASHINGID = createField(DSL.name("WashingId"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.VisibilityTime</code>.
     */
    public final TableField<WashingoptionsRecord, Integer> VISIBILITYTIME = createField(DSL.name("VisibilityTime"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.DateTime</code>.
     */
    public final TableField<WashingoptionsRecord, String> DATETIME = createField(DSL.name("DateTime"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.DurationMinutes</code>.
     */
    public final TableField<WashingoptionsRecord, Integer> DURATIONMINUTES = createField(DSL.name("DurationMinutes"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.InitialLoad</code>.
     */
    public final TableField<WashingoptionsRecord, Double> INITIALLOAD = createField(DSL.name("InitialLoad"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.MaxLoad</code>.
     */
    public final TableField<WashingoptionsRecord, Double> MAXLOAD = createField(DSL.name("MaxLoad"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.Temperature</code>.
     */
    public final TableField<WashingoptionsRecord, String> TEMPERATURE = createField(DSL.name("Temperature"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.SpinSpeed</code>.
     */
    public final TableField<WashingoptionsRecord, String> SPINSPEED = createField(DSL.name("SpinSpeed"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.FabricType</code>.
     */
    public final TableField<WashingoptionsRecord, String> FABRICTYPE = createField(DSL.name("FabricType"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.Color</code>.
     */
    public final TableField<WashingoptionsRecord, String> COLOR = createField(DSL.name("Color"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.DetergentTypes</code>.
     */
    public final TableField<WashingoptionsRecord, String> DETERGENTTYPES = createField(DSL.name("DetergentTypes"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.RefundType</code>.
     */
    public final TableField<WashingoptionsRecord, String> REFUNDTYPE = createField(DSL.name("RefundType"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.Underwear</code>.
     */
    public final TableField<WashingoptionsRecord, Boolean> UNDERWEAR = createField(DSL.name("Underwear"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>WASHINGOPTIONS.PickupAddress</code>.
     */
    public final TableField<WashingoptionsRecord, String> PICKUPADDRESS = createField(DSL.name("PickupAddress"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.DeliveryAddress</code>.
     */
    public final TableField<WashingoptionsRecord, String> DELIVERYADDRESS = createField(DSL.name("DeliveryAddress"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.PickupAvailability</code>.
     */
    public final TableField<WashingoptionsRecord, String> PICKUPAVAILABILITY = createField(DSL.name("PickupAvailability"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.DeliveryAvailability</code>.
     */
    public final TableField<WashingoptionsRecord, String> DELIVERYAVAILABILITY = createField(DSL.name("DeliveryAvailability"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.Drying</code>.
     */
    public final TableField<WashingoptionsRecord, String> DRYING = createField(DSL.name("Drying"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.Ironing</code>.
     */
    public final TableField<WashingoptionsRecord, Boolean> IRONING = createField(DSL.name("Ironing"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>WASHINGOPTIONS.ParticipantMaxLoad</code>.
     */
    public final TableField<WashingoptionsRecord, Double> PARTICIPANTMAXLOAD = createField(DSL.name("ParticipantMaxLoad"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>WASHINGOPTIONS.WashingAccessOpenDate</code>.
     */
    public final TableField<WashingoptionsRecord, String> WASHINGACCESSOPENDATE = createField(DSL.name("WashingAccessOpenDate"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>WASHINGOPTIONS.WashingAccessCloseDate</code>.
     */
    public final TableField<WashingoptionsRecord, String> WASHINGACCESSCLOSEDATE = createField(DSL.name("WashingAccessCloseDate"), SQLDataType.CLOB, this, "");

    private Washingoptions(Name alias, Table<WashingoptionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Washingoptions(Name alias, Table<WashingoptionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>WASHINGOPTIONS</code> table reference
     */
    public Washingoptions(String alias) {
        this(DSL.name(alias), WASHINGOPTIONS);
    }

    /**
     * Create an aliased <code>WASHINGOPTIONS</code> table reference
     */
    public Washingoptions(Name alias) {
        this(alias, WASHINGOPTIONS);
    }

    /**
     * Create a <code>WASHINGOPTIONS</code> table reference
     */
    public Washingoptions() {
        this(DSL.name("WASHINGOPTIONS"), null);
    }

    public <O extends Record> Washingoptions(Table<O> child, ForeignKey<O, WashingoptionsRecord> key) {
        super(child, key, WASHINGOPTIONS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<WashingoptionsRecord> getPrimaryKey() {
        return Keys.WASHINGOPTIONS__PK_WASHINGOPTIONS;
    }

    @Override
    public List<ForeignKey<WashingoptionsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.WASHINGOPTIONS__FK_WASHINGOPTIONS_PK_WASHING);
    }

    private transient Washing _washing;

    /**
     * Get the implicit join path to the <code>WASHING</code> table.
     */
    public Washing washing() {
        if (_washing == null)
            _washing = new Washing(this, Keys.WASHINGOPTIONS__FK_WASHINGOPTIONS_PK_WASHING);

        return _washing;
    }

    @Override
    public Washingoptions as(String alias) {
        return new Washingoptions(DSL.name(alias), this);
    }

    @Override
    public Washingoptions as(Name alias) {
        return new Washingoptions(alias, this);
    }

    @Override
    public Washingoptions as(Table<?> alias) {
        return new Washingoptions(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingoptions rename(String name) {
        return new Washingoptions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingoptions rename(Name name) {
        return new Washingoptions(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Washingoptions rename(Table<?> name) {
        return new Washingoptions(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row22 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row22<String, Integer, String, Integer, Double, Double, String, String, String, String, String, String, Boolean, String, String, String, String, String, Boolean, Double, String, String> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function22<? super String, ? super Integer, ? super String, ? super Integer, ? super Double, ? super Double, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super Double, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function22<? super String, ? super Integer, ? super String, ? super Integer, ? super Double, ? super Double, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super Double, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
