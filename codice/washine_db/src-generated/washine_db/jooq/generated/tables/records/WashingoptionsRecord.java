/*
 * This file is generated by jOOQ.
 */
package washine_db.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record22;
import org.jooq.Row22;
import org.jooq.impl.UpdatableRecordImpl;

import washine_db.jooq.generated.tables.Washingoptions;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class WashingoptionsRecord extends UpdatableRecordImpl<WashingoptionsRecord> implements Record22<String, Integer, String, Integer, Double, Double, String, String, String, String, String, String, Boolean, String, String, String, String, String, Boolean, Double, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>WASHINGOPTIONS.WashingId</code>.
     */
    public void setWashingid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.WashingId</code>.
     */
    public String getWashingid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.VisibilityTime</code>.
     */
    public void setVisibilitytime(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.VisibilityTime</code>.
     */
    public Integer getVisibilitytime() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.DateTime</code>.
     */
    public void setDatetime(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.DateTime</code>.
     */
    public String getDatetime() {
        return (String) get(2);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.DurationMinutes</code>.
     */
    public void setDurationminutes(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.DurationMinutes</code>.
     */
    public Integer getDurationminutes() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.InitialLoad</code>.
     */
    public void setInitialload(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.InitialLoad</code>.
     */
    public Double getInitialload() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.MaxLoad</code>.
     */
    public void setMaxload(Double value) {
        set(5, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.MaxLoad</code>.
     */
    public Double getMaxload() {
        return (Double) get(5);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.Temperature</code>.
     */
    public void setTemperature(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.Temperature</code>.
     */
    public String getTemperature() {
        return (String) get(6);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.SpinSpeed</code>.
     */
    public void setSpinspeed(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.SpinSpeed</code>.
     */
    public String getSpinspeed() {
        return (String) get(7);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.FabricType</code>.
     */
    public void setFabrictype(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.FabricType</code>.
     */
    public String getFabrictype() {
        return (String) get(8);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.Color</code>.
     */
    public void setColor(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.Color</code>.
     */
    public String getColor() {
        return (String) get(9);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.DetergentTypes</code>.
     */
    public void setDetergenttypes(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.DetergentTypes</code>.
     */
    public String getDetergenttypes() {
        return (String) get(10);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.RefundType</code>.
     */
    public void setRefundtype(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.RefundType</code>.
     */
    public String getRefundtype() {
        return (String) get(11);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.Underwear</code>.
     */
    public void setUnderwear(Boolean value) {
        set(12, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.Underwear</code>.
     */
    public Boolean getUnderwear() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.PickupAddress</code>.
     */
    public void setPickupaddress(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.PickupAddress</code>.
     */
    public String getPickupaddress() {
        return (String) get(13);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.DeliveryAddress</code>.
     */
    public void setDeliveryaddress(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.DeliveryAddress</code>.
     */
    public String getDeliveryaddress() {
        return (String) get(14);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.PickupAvailability</code>.
     */
    public void setPickupavailability(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.PickupAvailability</code>.
     */
    public String getPickupavailability() {
        return (String) get(15);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.DeliveryAvailability</code>.
     */
    public void setDeliveryavailability(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.DeliveryAvailability</code>.
     */
    public String getDeliveryavailability() {
        return (String) get(16);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.Drying</code>.
     */
    public void setDrying(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.Drying</code>.
     */
    public String getDrying() {
        return (String) get(17);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.Ironing</code>.
     */
    public void setIroning(Boolean value) {
        set(18, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.Ironing</code>.
     */
    public Boolean getIroning() {
        return (Boolean) get(18);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.ParticipantMaxLoad</code>.
     */
    public void setParticipantmaxload(Double value) {
        set(19, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.ParticipantMaxLoad</code>.
     */
    public Double getParticipantmaxload() {
        return (Double) get(19);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.WashingAccessOpenDate</code>.
     */
    public void setWashingaccessopendate(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.WashingAccessOpenDate</code>.
     */
    public String getWashingaccessopendate() {
        return (String) get(20);
    }

    /**
     * Setter for <code>WASHINGOPTIONS.WashingAccessCloseDate</code>.
     */
    public void setWashingaccessclosedate(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>WASHINGOPTIONS.WashingAccessCloseDate</code>.
     */
    public String getWashingaccessclosedate() {
        return (String) get(21);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record22 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row22<String, Integer, String, Integer, Double, Double, String, String, String, String, String, String, Boolean, String, String, String, String, String, Boolean, Double, String, String> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    @Override
    public Row22<String, Integer, String, Integer, Double, Double, String, String, String, String, String, String, Boolean, String, String, String, String, String, Boolean, Double, String, String> valuesRow() {
        return (Row22) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Washingoptions.WASHINGOPTIONS.WASHINGID;
    }

    @Override
    public Field<Integer> field2() {
        return Washingoptions.WASHINGOPTIONS.VISIBILITYTIME;
    }

    @Override
    public Field<String> field3() {
        return Washingoptions.WASHINGOPTIONS.DATETIME;
    }

    @Override
    public Field<Integer> field4() {
        return Washingoptions.WASHINGOPTIONS.DURATIONMINUTES;
    }

    @Override
    public Field<Double> field5() {
        return Washingoptions.WASHINGOPTIONS.INITIALLOAD;
    }

    @Override
    public Field<Double> field6() {
        return Washingoptions.WASHINGOPTIONS.MAXLOAD;
    }

    @Override
    public Field<String> field7() {
        return Washingoptions.WASHINGOPTIONS.TEMPERATURE;
    }

    @Override
    public Field<String> field8() {
        return Washingoptions.WASHINGOPTIONS.SPINSPEED;
    }

    @Override
    public Field<String> field9() {
        return Washingoptions.WASHINGOPTIONS.FABRICTYPE;
    }

    @Override
    public Field<String> field10() {
        return Washingoptions.WASHINGOPTIONS.COLOR;
    }

    @Override
    public Field<String> field11() {
        return Washingoptions.WASHINGOPTIONS.DETERGENTTYPES;
    }

    @Override
    public Field<String> field12() {
        return Washingoptions.WASHINGOPTIONS.REFUNDTYPE;
    }

    @Override
    public Field<Boolean> field13() {
        return Washingoptions.WASHINGOPTIONS.UNDERWEAR;
    }

    @Override
    public Field<String> field14() {
        return Washingoptions.WASHINGOPTIONS.PICKUPADDRESS;
    }

    @Override
    public Field<String> field15() {
        return Washingoptions.WASHINGOPTIONS.DELIVERYADDRESS;
    }

    @Override
    public Field<String> field16() {
        return Washingoptions.WASHINGOPTIONS.PICKUPAVAILABILITY;
    }

    @Override
    public Field<String> field17() {
        return Washingoptions.WASHINGOPTIONS.DELIVERYAVAILABILITY;
    }

    @Override
    public Field<String> field18() {
        return Washingoptions.WASHINGOPTIONS.DRYING;
    }

    @Override
    public Field<Boolean> field19() {
        return Washingoptions.WASHINGOPTIONS.IRONING;
    }

    @Override
    public Field<Double> field20() {
        return Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD;
    }

    @Override
    public Field<String> field21() {
        return Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE;
    }

    @Override
    public Field<String> field22() {
        return Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE;
    }

    @Override
    public String component1() {
        return getWashingid();
    }

    @Override
    public Integer component2() {
        return getVisibilitytime();
    }

    @Override
    public String component3() {
        return getDatetime();
    }

    @Override
    public Integer component4() {
        return getDurationminutes();
    }

    @Override
    public Double component5() {
        return getInitialload();
    }

    @Override
    public Double component6() {
        return getMaxload();
    }

    @Override
    public String component7() {
        return getTemperature();
    }

    @Override
    public String component8() {
        return getSpinspeed();
    }

    @Override
    public String component9() {
        return getFabrictype();
    }

    @Override
    public String component10() {
        return getColor();
    }

    @Override
    public String component11() {
        return getDetergenttypes();
    }

    @Override
    public String component12() {
        return getRefundtype();
    }

    @Override
    public Boolean component13() {
        return getUnderwear();
    }

    @Override
    public String component14() {
        return getPickupaddress();
    }

    @Override
    public String component15() {
        return getDeliveryaddress();
    }

    @Override
    public String component16() {
        return getPickupavailability();
    }

    @Override
    public String component17() {
        return getDeliveryavailability();
    }

    @Override
    public String component18() {
        return getDrying();
    }

    @Override
    public Boolean component19() {
        return getIroning();
    }

    @Override
    public Double component20() {
        return getParticipantmaxload();
    }

    @Override
    public String component21() {
        return getWashingaccessopendate();
    }

    @Override
    public String component22() {
        return getWashingaccessclosedate();
    }

    @Override
    public String value1() {
        return getWashingid();
    }

    @Override
    public Integer value2() {
        return getVisibilitytime();
    }

    @Override
    public String value3() {
        return getDatetime();
    }

    @Override
    public Integer value4() {
        return getDurationminutes();
    }

    @Override
    public Double value5() {
        return getInitialload();
    }

    @Override
    public Double value6() {
        return getMaxload();
    }

    @Override
    public String value7() {
        return getTemperature();
    }

    @Override
    public String value8() {
        return getSpinspeed();
    }

    @Override
    public String value9() {
        return getFabrictype();
    }

    @Override
    public String value10() {
        return getColor();
    }

    @Override
    public String value11() {
        return getDetergenttypes();
    }

    @Override
    public String value12() {
        return getRefundtype();
    }

    @Override
    public Boolean value13() {
        return getUnderwear();
    }

    @Override
    public String value14() {
        return getPickupaddress();
    }

    @Override
    public String value15() {
        return getDeliveryaddress();
    }

    @Override
    public String value16() {
        return getPickupavailability();
    }

    @Override
    public String value17() {
        return getDeliveryavailability();
    }

    @Override
    public String value18() {
        return getDrying();
    }

    @Override
    public Boolean value19() {
        return getIroning();
    }

    @Override
    public Double value20() {
        return getParticipantmaxload();
    }

    @Override
    public String value21() {
        return getWashingaccessopendate();
    }

    @Override
    public String value22() {
        return getWashingaccessclosedate();
    }

    @Override
    public WashingoptionsRecord value1(String value) {
        setWashingid(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value2(Integer value) {
        setVisibilitytime(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value3(String value) {
        setDatetime(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value4(Integer value) {
        setDurationminutes(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value5(Double value) {
        setInitialload(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value6(Double value) {
        setMaxload(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value7(String value) {
        setTemperature(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value8(String value) {
        setSpinspeed(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value9(String value) {
        setFabrictype(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value10(String value) {
        setColor(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value11(String value) {
        setDetergenttypes(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value12(String value) {
        setRefundtype(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value13(Boolean value) {
        setUnderwear(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value14(String value) {
        setPickupaddress(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value15(String value) {
        setDeliveryaddress(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value16(String value) {
        setPickupavailability(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value17(String value) {
        setDeliveryavailability(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value18(String value) {
        setDrying(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value19(Boolean value) {
        setIroning(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value20(Double value) {
        setParticipantmaxload(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value21(String value) {
        setWashingaccessopendate(value);
        return this;
    }

    @Override
    public WashingoptionsRecord value22(String value) {
        setWashingaccessclosedate(value);
        return this;
    }

    @Override
    public WashingoptionsRecord values(String value1, Integer value2, String value3, Integer value4, Double value5, Double value6, String value7, String value8, String value9, String value10, String value11, String value12, Boolean value13, String value14, String value15, String value16, String value17, String value18, Boolean value19, Double value20, String value21, String value22) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        value22(value22);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WashingoptionsRecord
     */
    public WashingoptionsRecord() {
        super(Washingoptions.WASHINGOPTIONS);
    }

    /**
     * Create a detached, initialised WashingoptionsRecord
     */
    public WashingoptionsRecord(String washingid, Integer visibilitytime, String datetime, Integer durationminutes, Double initialload, Double maxload, String temperature, String spinspeed, String fabrictype, String color, String detergenttypes, String refundtype, Boolean underwear, String pickupaddress, String deliveryaddress, String pickupavailability, String deliveryavailability, String drying, Boolean ironing, Double participantmaxload, String washingaccessopendate, String washingaccessclosedate) {
        super(Washingoptions.WASHINGOPTIONS);

        setWashingid(washingid);
        setVisibilitytime(visibilitytime);
        setDatetime(datetime);
        setDurationminutes(durationminutes);
        setInitialload(initialload);
        setMaxload(maxload);
        setTemperature(temperature);
        setSpinspeed(spinspeed);
        setFabrictype(fabrictype);
        setColor(color);
        setDetergenttypes(detergenttypes);
        setRefundtype(refundtype);
        setUnderwear(underwear);
        setPickupaddress(pickupaddress);
        setDeliveryaddress(deliveryaddress);
        setPickupavailability(pickupavailability);
        setDeliveryavailability(deliveryavailability);
        setDrying(drying);
        setIroning(ironing);
        setParticipantmaxload(participantmaxload);
        setWashingaccessopendate(washingaccessopendate);
        setWashingaccessclosedate(washingaccessclosedate);
        resetChangedOnNotNull();
    }
}
