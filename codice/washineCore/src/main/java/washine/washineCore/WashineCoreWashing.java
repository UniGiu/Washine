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
  public WashineWashing createWashing(String laundryPersonId) throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    String washingId = generateUniqueCode();

    try {
      if (washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing already exists");
      }
      washingDb.createWashing(washingId, laundryPersonId);
      WashineWashingOptions washingOptions = new WashineWashingOptions();
      return new WashineWashing(washingId, null, washingOptions);

    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public WashineWashing participateToWashing(String washingId, String participantId, double load)
      throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
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
      WashineWashing washing = new WashineWashing(washingId, null, washingOptions);
      double totalWashingLoad = calculateWashingTotalLoad(washing);

      if (totalWashingLoad >= washingOptions.getMaxLoad()) {
        throw new WashineCoreException("Max load Reached");
      }
      washingDb.participateToWashing(washingId, participantId, load);
      return washing;
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
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      return washingDb.getParticipationWeight(washingId, userId);
    } catch (WashineDataException e) {

      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean deleteWashing(String washingId) throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      if (washingDb.getWashingOptions(washingId) != null) {
        return washingDb.deleteWashing(washingId);
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
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      return washingDb.getWashingOptions(washingId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean updateWashingOptions(WashineWashing washing) throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washing.getId())) {
        throw new WashineCoreException("Washing does not exists");
      }
      if (washing.getWashingOptions().getDatetime() <= 0) {
        throw new WashineCoreException("Invalid date and time");
      }
      if (washing.getWashingOptions().getDurationMinutes() <= 0) {
        throw new WashineCoreException("Invalid duration minutes");
      }
      if (washing.getWashingOptions().getInitialLoad() < 0) {
        throw new WashineCoreException("Invalid initial load");
      }
      if (washing.getWashingOptions().getMaxLoad() < 0) {
        throw new WashineCoreException("Invalid max load");
      }
      if (washing.getWashingOptions().getMaxLoadParticipant() < 0) {
        throw new WashineCoreException("Invalid participant max load");
      }
      if (washing.getWashingOptions().getVisibilityTime() <= 0) {
        throw new WashineCoreException("Invalid visibility time");
      }
      if (washing.getWashingOptions().getWashingAccessCloseDate() <= 0) {
        throw new WashineCoreException("Invalid access date");
      }
      if (washing.getWashingOptions().getWashingAccessCloseDate() <= 0) {
        throw new WashineCoreException("Invalid closing date");
      }
      if (calculateWashingTotalLoad(washing) > washing.getWashingOptions().getMaxLoad()) {
        throw new WashineCoreException("New max load exceeded");
      }
      return washingDb.updateWashingOptions(
          washing.getId(),
          washing.getWashingOptions().getVisibilityTime(),
          washing.getWashingOptions().getDatetime(),
          washing.getWashingOptions().getDurationMinutes(),
          washing.getWashingOptions().getInitialLoad(),
          washing.getWashingOptions().getMaxLoad(),
          washing.getWashingOptions().getTemperature(),
          washing.getWashingOptions().getSpinSpeed(),
          washing.getWashingOptions().getFabricType(),
          washing.getWashingOptions().getColor(),
          washing.getWashingOptions().getDetergentType(),
          washing.getWashingOptions().getRefundType(),
          washing.getWashingOptions().isUnderwear(),
          washing.getWashingOptions().getPickupAddress(),
          washing.getWashingOptions().getDeliveryAddress(),
          washing.getWashingOptions().getPickupAvailability(),
          washing.getWashingOptions().getDeliveryAvailability(),
          washing.getWashingOptions().getDryingType(),
          washing.getWashingOptions().isIroning(),
          washing.getWashingOptions().getMaxLoadParticipant(),
          washing.getWashingOptions().getWashingAccessOpenDate(),
          washing.getWashingOptions().getWashingAccessCloseDate());
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean deleteParticipation(String washingId, String userId) throws WashineCoreException {
    WashineWashingDb washineDb = new WashineWashingDb();
    try {
      if (!washineDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      return washineDb.deleteParticipation(washingId, userId);

    } catch (WashineDataException e) {

      throw new WashineCoreException("WashineCoreException");
    }
  }

  /**
   * @return a new generated and unique id code
   */
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

  /**
   * Calculates the total load of a washing through the id
   *
   * @param washing the washing object (cointaing the supposed id)
   * @return the total load
   * @throws WashineCoreException
   */
  public double calculateWashingTotalLoad(WashineWashing washing) throws WashineCoreException {
    WashineWashingDb washingDb = new WashineWashingDb();
    try {
      double totalWashingLoad = washing.getWashingOptions().getInitialLoad();
      List<String> participantIds = washingDb.getParticipantIds(washing.getId());
      for (String s : participantIds) {
        totalWashingLoad += washingDb.getParticipationWeight(washing.getId(), s);
        return totalWashingLoad;
      }
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
    return 0;
  }
}
