/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import washine_db.jooq.generated.tables.Groups;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class GroupsRecord extends UpdatableRecordImpl<GroupsRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>GROUPS.UserId</code>.
     */
    public void setUserid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>GROUPS.UserId</code>.
     */
    public String getUserid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>GROUPS.GroupName</code>.
     */
    public void setGroupname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>GROUPS.GroupName</code>.
     */
    public String getGroupname() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Groups.GROUPS.USERID;
    }

    @Override
    public Field<String> field2() {
        return Groups.GROUPS.GROUPNAME;
    }

    @Override
    public String component1() {
        return getUserid();
    }

    @Override
    public String component2() {
        return getGroupname();
    }

    @Override
    public String value1() {
        return getUserid();
    }

    @Override
    public String value2() {
        return getGroupname();
    }

    @Override
    public GroupsRecord value1(String value) {
        setUserid(value);
        return this;
    }

    @Override
    public GroupsRecord value2(String value) {
        setGroupname(value);
        return this;
    }

    @Override
    public GroupsRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GroupsRecord
     */
    public GroupsRecord() {
        super(Groups.GROUPS);
    }

    /**
     * Create a detached, initialised GroupsRecord
     */
    public GroupsRecord(String userid, String groupname) {
        super(Groups.GROUPS);

        setUserid(userid);
        setGroupname(groupname);
        resetChangedOnNotNull();
    }
}