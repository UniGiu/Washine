/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import washine_db.jooq.generated.tables.Groupuserslist;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class GroupuserslistRecord extends UpdatableRecordImpl<GroupuserslistRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>GROUPUSERSLIST.LaundryPersonId</code>.
     */
    public void setLaundrypersonid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>GROUPUSERSLIST.LaundryPersonId</code>.
     */
    public String getLaundrypersonid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>GROUPUSERSLIST.ParticipantId</code>.
     */
    public void setParticipantid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>GROUPUSERSLIST.ParticipantId</code>.
     */
    public String getParticipantid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>GROUPUSERSLIST.Name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>GROUPUSERSLIST.Name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Groupuserslist.GROUPUSERSLIST.LAUNDRYPERSONID;
    }

    @Override
    public Field<String> field2() {
        return Groupuserslist.GROUPUSERSLIST.PARTICIPANTID;
    }

    @Override
    public Field<String> field3() {
        return Groupuserslist.GROUPUSERSLIST.NAME;
    }

    @Override
    public String component1() {
        return getLaundrypersonid();
    }

    @Override
    public String component2() {
        return getParticipantid();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String value1() {
        return getLaundrypersonid();
    }

    @Override
    public String value2() {
        return getParticipantid();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public GroupuserslistRecord value1(String value) {
        setLaundrypersonid(value);
        return this;
    }

    @Override
    public GroupuserslistRecord value2(String value) {
        setParticipantid(value);
        return this;
    }

    @Override
    public GroupuserslistRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public GroupuserslistRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GroupuserslistRecord
     */
    public GroupuserslistRecord() {
        super(Groupuserslist.GROUPUSERSLIST);
    }

    /**
     * Create a detached, initialised GroupuserslistRecord
     */
    public GroupuserslistRecord(String laundrypersonid, String participantid, String name) {
        super(Groupuserslist.GROUPUSERSLIST);

        setLaundrypersonid(laundrypersonid);
        setParticipantid(participantid);
        setName(name);
        resetChangedOnNotNull();
    }
}