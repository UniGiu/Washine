package washine_db.washings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.Washing;
import washine_db.jooq.generated.tables.Washingoptions;
import washine_db.jooq.generated.tables.Washingparticipation;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;
import washine_db.washine_db.JOOQCodeGeneration;

public class WashineWashingDb implements WashineWashingDbIf {

  public WashineWashingDb() {
    /* this constructor is empty */
  }

  @Override
  public boolean participateToWashing(String washingId, String participantId, double load)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      int result =
          create
              .insertInto(Washingparticipation.WASHINGPARTICIPATION)
              .values(washingId, participantId, load)
              .execute();
      return result == 1;
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public boolean createWashing(String washingId, String laundryPersonId)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      int result = create.insertInto(Washing.WASHING).values(washingId, laundryPersonId).execute();
      return result == 1;
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public boolean updateWashingOptions(
      String washingId,
      int visibilityTime,
      int dateTime,
      int durationMinutes,
      double initialLoad,
      double maxLoad,
      String temperature,
      String spinSpeed,
      String fabrycType,
      String color,
      String detergentTypes,
      String refundTypes,
      boolean underwear,
      String pickupAddress,
      String deliveryAddress,
      String pickupAvailability,
      String deliverAvailability,
      String drying,
      boolean ironing,
      double participantMaxLoad,
      int washingAccessOpenDate,
      int washingAccessCloseDate)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      create
          .insertInto(Washingoptions.WASHINGOPTIONS, Washingoptions.WASHINGOPTIONS.WASHINGID)
          .values(washingId)
          .execute();
      int result =
          create
              .update(Washingoptions.WASHINGOPTIONS)
              .set(Washingoptions.WASHINGOPTIONS.VISIBILITYTIME, visibilityTime)
              .set(Washingoptions.WASHINGOPTIONS.DATETIME, dateTime)
              .set(Washingoptions.WASHINGOPTIONS.DURATIONMINUTES, durationMinutes)
              .set(Washingoptions.WASHINGOPTIONS.INITIALLOAD, initialLoad)
              .set(Washingoptions.WASHINGOPTIONS.MAXLOAD, maxLoad)
              .set(Washingoptions.WASHINGOPTIONS.TEMPERATURE, temperature)
              .set(Washingoptions.WASHINGOPTIONS.SPINSPEED, spinSpeed)
              .set(Washingoptions.WASHINGOPTIONS.FABRICTYPE, fabrycType)
              .set(Washingoptions.WASHINGOPTIONS.COLOR, color)
              .set(Washingoptions.WASHINGOPTIONS.DETERGENTTYPES, detergentTypes)
              .set(Washingoptions.WASHINGOPTIONS.REFUNDTYPE, refundTypes)
              .set(Washingoptions.WASHINGOPTIONS.UNDERWEAR, underwear)
              .set(Washingoptions.WASHINGOPTIONS.PICKUPADDRESS, pickupAddress)
              .set(Washingoptions.WASHINGOPTIONS.DELIVERYADDRESS, deliveryAddress)
              .set(Washingoptions.WASHINGOPTIONS.PICKUPAVAILABILITY, pickupAvailability)
              .set(Washingoptions.WASHINGOPTIONS.DELIVERYAVAILABILITY, deliverAvailability)
              .set(Washingoptions.WASHINGOPTIONS.DRYING, drying)
              .set(Washingoptions.WASHINGOPTIONS.IRONING, ironing)
              .set(Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD, participantMaxLoad)
              .set(Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE, washingAccessOpenDate)
              .set(Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE, washingAccessCloseDate)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .execute();
      return result == 1;
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public List<String> getLaundryPersonWashingIds(String laundryPersonId)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      return create
          .select()
          .from(Washing.WASHING)
          .where(Washing.WASHING.LAUNDRYPERSONID.eq(laundryPersonId))
          .fetch(Washing.WASHING.WASHINGID);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public List<String> getParticipatedWashingIds(String userId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      return create
          .select()
          .from(Washingparticipation.WASHINGPARTICIPATION)
          .where(Washingparticipation.WASHINGPARTICIPATION.PARTICIPANTID.eq(userId))
          .fetch(Washingparticipation.WASHINGPARTICIPATION.WASHINGID);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public double getParticipationWeight(String washingId, String userId)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Double> weight =
          create
              .select(Washingparticipation.WASHINGPARTICIPATION.LOAD)
              .from(Washingparticipation.WASHINGPARTICIPATION)
              .where(
                  Washingparticipation.WASHINGPARTICIPATION
                      .PARTICIPANTID
                      .eq(userId)
                      .and(Washingparticipation.WASHINGPARTICIPATION.WASHINGID.eq(washingId)))
              .fetchOne();
      return weight.getValue(Washingparticipation.WASHINGPARTICIPATION.LOAD);

    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public boolean deleteWashing(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      int result =
          create
              .deleteFrom(Washing.WASHING)
              .where(Washing.WASHING.WASHINGID.eq(washingId))
              .execute();
      return result == 1;
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public Result<WashingoptionsRecord> getWashingOptions(String washingId)
      throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      return create
          .selectFrom(Washingoptions.WASHINGOPTIONS)
          .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
          .fetch();
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public boolean deleteParticipation(String washingId, String userId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      int result =
          create
              .deleteFrom(Washingparticipation.WASHINGPARTICIPATION)
              .where(
                  Washingparticipation.WASHINGPARTICIPATION
                      .WASHINGID
                      .eq(washingId)
                      .and(Washingparticipation.WASHINGPARTICIPATION.PARTICIPANTID.eq(userId)))
              .execute();
      return result == 1;
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public int getWashingDateTime(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Integer> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.DATETIME)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.DATETIME);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public int getWashingAccessOpenDate(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Integer> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public int getWashingAccessCloseDate(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Integer> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  public double getWashingMaxLoad(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Double> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.MAXLOAD)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.MAXLOAD);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public double getWashingParticipantMaxLoad(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Double> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }

  @Override
  public double getWashingInitialLoad(String washingId) throws WashineDataException {
    try {
      Connection conn = DriverManager.getConnection(JOOQCodeGeneration.DB_URL);
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
      Record1<Double> date =
          create
              .select(Washingoptions.WASHINGOPTIONS.INITIALLOAD)
              .from(Washingoptions.WASHINGOPTIONS)
              .where(Washingoptions.WASHINGOPTIONS.WASHINGID.eq(washingId))
              .fetchOne();
      return date.getValue(Washingoptions.WASHINGOPTIONS.INITIALLOAD);
    } catch (SQLException e) {
      throw new WashineDataException("WashineDataException");
    }
  }
}
