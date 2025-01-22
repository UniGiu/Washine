package washine.washineCore;

import java.util.Random;
import java.time.Instant;
import java.util.List;

import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineWashing;
import washine.washineCore.washing.WashineWashingOptions;
import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.Washingoptions;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;
import washine_db.washings.WashineWashingDb;

public class WashineCoreWashing implements WashineCoreWashingIf {

  @Override
  public boolean createWashing(String washingId, String laundryPersonId)
      throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();

    try {
      return washingDb.createWashing(washingId, laundryPersonId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public WashineWashing participateToWashing(String washingId, String participantId, double load)
      throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      Result<WashingoptionsRecord> washingOptionsDb = washingDb.getWashingOptions(washingId);
      WashineWashingOptions washingOptions =
          new WashineWashingOptions(
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.VISIBILITYTIME),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DATETIME),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DURATIONMINUTES),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.INITIALLOAD),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.MAXLOAD),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.TEMPERATURE),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.SPINSPEED),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.FABRICTYPE),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.COLOR),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DETERGENTTYPES),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.UNDERWEAR),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.REFUNDTYPE),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.PICKUPADDRESS),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DELIVERYADDRESS),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.PICKUPAVAILABILITY),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DELIVERYAVAILABILITY),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DRYING),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.IRONING),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE));

      if (!((washingOptions.getDatetime() > (int) Instant.now().getEpochSecond())
          && (washingOptions.getWashingAccessOpenDate() < (int) Instant.now().getEpochSecond())
          && (washingOptions.getWashingAccessCloseDate() > (int) Instant.now().getEpochSecond()))) {
        throw new WashineCoreException("Washing expired");
      }

      if (load > washingOptions.getMaxLoadParticipant()) {
        throw new WashineCoreException("Exceded maximum load");
      }

      double totalWashingLoad = washingOptions.getInitialLoad();
      List<String> participantIds = washingDb.getParticipantIds(washingId);
      for (String s : participantIds) {
        totalWashingLoad += washingDb.getParticipationWeight(washingId, s);
      }

      if (totalWashingLoad >= washingOptions.getMaxLoad()) {
        throw new WashineCoreException("Max load Reached");
      }
      washingDb.participateToWashing(washingId, participantId, load);
      return new WashineWashing(washingId, null, washingOptions);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public List<String> getLaundryPersonWashingIds(String laundryPersonId)
      throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      return washineDb.getLaundryPersonWashingIds(laundryPersonId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public List<String> getParticipatedWashingIds(String userId) throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      return washineDb.getParticipatedWashingIds(userId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public double getParticipationWeight(String washingId, String userId)
      throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {

      return washineDb.getParticipationWeight(washingId, userId);
    } catch (WashineDataException e) {

      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean deleteWashing(String washingId) throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {

      if (washineDb.getWashingOptions(washingId) != null) {
        return washineDb.deleteWashing(washingId);
      } else {
        return false;
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public Result<WashingoptionsRecord> getWashingOptions(String washingId)
      throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      return washineDb.getWashingOptions(washingId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
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
      String fabricType,
      String color,
      String detergentTypes,
      String refundTypes,
      boolean underwear,
      String pickupAddress,
      String deliveryAddress,
      String pickupAvailability,
      String deliveryAvailability,
      String drying,
      boolean ironing,
      double participantMaxLoad,
      int washingAccessOpenDate,
      int washingAccessCloseDate)
      throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      return washineDb.updateWashingOptions(
          washingId,
          visibilityTime,
          dateTime,
          durationMinutes,
          initialLoad,
          maxLoad,
          temperature,
          spinSpeed,
          fabricType,
          color,
          detergentTypes,
          refundTypes,
          underwear,
          pickupAddress,
          deliveryAddress,
          pickupAvailability,
          deliveryAvailability,
          drying,
          ironing,
          participantMaxLoad,
          washingAccessOpenDate,
          washingAccessCloseDate);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean deleteParticipation(String washingId, String userId) throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      return washineDb.deleteParticipation(washingId, userId);
    } catch (WashineDataException e) {

      throw new WashineCoreException("WashineCoreException");
    }
  }

  public String generateUniqueCode() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 12) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    return salt.toString();
  }
}
