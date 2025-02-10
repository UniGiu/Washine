package washine.washineCore;

import java.util.Random;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;
import washine.washineCore.washing.WashineWashing;
import washine.washineCore.washing.WashineWashingOptions;
import washine_db.exceptions.WashineDataException;
import washine_db.groups.WashineGroupDb;
import washine_db.groups.WashineGroupDbIf;
import washine_db.jooq.generated.tables.Washingoptions;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;
import washine_db.washings.WashineWashingDb;
import washine_db.washings.WashineWashingDbIf;

public class WashineCoreWashing implements WashineCoreWashingIf {

  @Override
  public boolean createWashing(
      String laundryPersonId, WashineLaundryWashingOptionsLaunderIf options)
      throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    String washingId = generateUniqueCode();

    try {
      if (washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing already exists");
      }
      if (options.getDatetime() < 0) {
        throw new WashineCoreException("invalid washing date time");
      }
      if (options.getVisibilityTime() < 0) {
        throw new WashineCoreException("invalid visibility time");
      }
      if (options.getDurationMinutes() < 0) {
        throw new WashineCoreException("invalid duration minutes");
      }
      if (options.getInitialLoad() < 0) {
        throw new WashineCoreException("invalid initial load");
      }
      if (options.getMaxLoad() < 0) {
        throw new WashineCoreException("invalid max load");
      }
      if (options.getInitialLoad() > options.getMaxLoad()) {
        throw new WashineCoreException("Max load exceeded");
      }
      if (options.getTemperature() == null) {
        throw new WashineCoreException("invalid temperature");
      }
      if (options.getSpinSpeed() == null) {
        throw new WashineCoreException("invalid spin speed");
      }
      if (options.getFabricType() == null) {
        throw new WashineCoreException("invalid fabric type");
      }
      if (options.getColor() == null) {
        throw new WashineCoreException("invalid color");
      }
      if (options.getDetergentType() == null) {
        throw new WashineCoreException("invalid detergent type");
      }
      if (options.getRefundType() == null) {
        throw new WashineCoreException("invalid refund type");
      }
      if (options.getInitialLoad() > options.getMaxLoad()) {
        throw new WashineCoreException("invalid inital load");
      }
      washingDb.createWashing(washingId, laundryPersonId);
      washingDb.createWashingOptions(
          washingId,
          options.getVisibilityTime(),
          options.getDatetime(),
          options.getDurationMinutes(),
          options.getInitialLoad(),
          options.getMaxLoad(),
          options.getTemperature(),
          options.getSpinSpeed(),
          options.getFabricType(),
          options.getColor(),
          options.getDetergentType(),
          options.getRefundType(),
          options.isUnderwear(),
          options.getPickupAddress(),
          options.getDeliveryAddress(),
          options.getPickupAvailability(),
          options.getDeliveryAvailability(),
          options.getDryingType(),
          options.isIroning(),
          options.getMaxLoadParticipant(),
          options.getWashingAccessOpenDate(),
          options.getWashingAccessCloseDate());
      return true;

    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  private void validateWashingParticipation(WashineLaundryWashingIf washing, double load) throws WashineCoreException {
    WashineLaundryWashingOptionsIf options = washing.getWashingOptions();
    if (options.getDatetime() < (int) Instant.now().getEpochSecond()) {
        throw new WashineCoreException("Washing expired");
    }

    if (options.getWashingAccessOpenDate() != 0
        && options.getWashingAccessOpenDate() > (int) Instant.now().getEpochSecond()) {
        throw new WashineCoreException("Access date has yet to happen");
    }
    if (options.getWashingAccessCloseDate() != 0
        && options.getWashingAccessCloseDate() < (int) Instant.now().getEpochSecond()) {
        throw new WashineCoreException("Washing closed");
    }

    if (options.getMaxLoadParticipant() != 0 && load > options.getMaxLoadParticipant()) {
        throw new WashineCoreException("Exceeded participant maximum load");
    }
    if (load > options.getMaxLoad()) {
        throw new WashineCoreException("Exceeded maximum load");
    }
    if (load > (options.getMaxLoad() - washing.getLoad())) {
        throw new WashineCoreException("Your load is heavier than the possible room left");
    }
    if (washing.getLoad() >= options.getMaxLoad()) {
        throw new WashineCoreException("Max load Reached");
    }
  }

  @Override
  public WashineLaundryWashingIf participateToWashing(
      String washingId, String participantId, double load) throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      WashineLaundryWashingIf washing = getWashing(washingId);
      validateWashingParticipation(washing, load);
      washingDb.participateToWashing(washingId, participantId, load);
      return washing;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public WashineLaundryWashingIf updateParticipantWashingLoad(
      String washingId, String participantId, double load) throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    try {
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exists");
      }
      WashineLaundryWashingIf washing = getWashing(washingId);
      validateWashingParticipation(washing, load);
      washingDb.updateParticipationToWashing(washingId, participantId, load);
      return washing;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }
  
  @Override
  public List<String> getLaundryPersonWashingIds(String laundryPersonId)
      throws WashineCoreException {
    WashineWashingDbIf washineDb = new WashineWashingDb();
    try {
      return washineDb.getLaundryPersonWashingIds(laundryPersonId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public List<String> getParticipatedWashingIds(String userId) throws WashineCoreException {
    WashineWashingDbIf washineDb = new WashineWashingDb();
    try {
      return washineDb.getParticipatedWashingIds(userId);
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public double getParticipationWeight(String washingId, String userId)
      throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
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
    WashineWashingDbIf washingDb = new WashineWashingDb();
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
  public WashineLaundryWashingIf getWashing(String washingId) throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
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
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DRYING),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.PICKUPAVAILABILITY),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.DELIVERYAVAILABILITY),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.IRONING),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE),
              washingOptionsDb.getValue(0, Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE));
      WashineWashing washing = new WashineWashing(washingId, null, washingOptions);
      for (String s : washingDb.getParticipantIds(washingId)) {
        washing.addParticipant(s);
      }
      double washineTotalLoad = calculateWashingTotalLoad(washing);
      washing.addToLoad(washineTotalLoad);

      return washing;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean updateWashingOptions(WashineLaundryWashingIf washing) throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    WashineLaundryWashingOptionsIf options = washing.getWashingOptions();
    String washingId = washing.getId();
    try {
      if (washing.getWashingOptions().getDatetime() < (int) Instant.now().getEpochSecond()) {
        throw new WashineCoreException("Washing already took place");
      }
      if (calculateWashingTotalLoad(washing) > options.getMaxLoad()) {
        throw new WashineCoreException("New max load exceeded");
      }
      if (!washingDb.existsWashing(washingId)) {
        throw new WashineCoreException("Washing does not exist");
      }
      if (options.getDatetime() < 0) {
        throw new WashineCoreException("invalid washing date time");
      }
      if (options.getVisibilityTime() < 0) {
        throw new WashineCoreException("invalid visibility time");
      }
      if (options.getDurationMinutes() < 0) {
        throw new WashineCoreException("invalid duration minutes");
      }
      if (options.getInitialLoad() < 0) {
        throw new WashineCoreException("invalid initial load");
      }
      if (options.getMaxLoad() < 0) {
        throw new WashineCoreException("invalid max load");
      }
      if (options.getTemperature() == null) {
        throw new WashineCoreException("invalid temperature");
      }
      if (options.getSpinSpeed() == null) {
        throw new WashineCoreException("invalid spin speed");
      }
      if (options.getFabricType() == null) {
        throw new WashineCoreException("invalid fabric type");
      }
      if (options.getColor() == null) {
        throw new WashineCoreException("invalid color");
      }
      if (options.getDetergentType() == null) {
        throw new WashineCoreException("invalid detergent type");
      }
      if (options.getRefundType() == null) {
        throw new WashineCoreException("invalid refund type");
      }
      return washingDb.updateWashingOptions(
          washingId,
          options.getVisibilityTime(),
          options.getDatetime(),
          options.getDurationMinutes(),
          options.getInitialLoad(),
          options.getMaxLoad(),
          options.getTemperature(),
          options.getSpinSpeed(),
          options.getFabricType(),
          options.getColor(),
          options.getDetergentType(),
          options.getRefundType(),
          options.isUnderwear(),
          options.getPickupAddress(),
          options.getDeliveryAddress(),
          options.getPickupAvailability(),
          options.getDeliveryAvailability(),
          options.getDryingType(),
          options.isIroning(),
          options.getMaxLoadParticipant(),
          options.getWashingAccessOpenDate(),
          options.getWashingAccessCloseDate());
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public boolean deleteParticipation(String washingId, String userId) throws WashineCoreException {
    WashineWashingDbIf washineDb = new WashineWashingDb();
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
  public double calculateWashingTotalLoad(WashineLaundryWashingIf washing)
      throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    String id = washing.getId();
    WashineLaundryWashingOptionsIf options = washing.getWashingOptions();
    List<String> participantIds = washing.getParticipantIds();
    try {
      double totalWashingLoad = options.getInitialLoad();
      for (String s : participantIds) {
        totalWashingLoad += washingDb.getParticipationWeight(id, s);
      }
      return totalWashingLoad;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  @Override
  public List<WashineLaundryWashingIf> getLaunderWashings(String laundryPersonId)
      throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    List<WashineLaundryWashingIf> washings = new ArrayList<WashineLaundryWashingIf>();
    Result<?> records;
    try {
      records = washingDb.getLaunderWashings(laundryPersonId);
      for (org.jooq.Record r : records) {

        WashineWashingOptions washingOptions = fetchWashingOptions(r);
        String washingId = r.getValue(Washingoptions.WASHINGOPTIONS.WASHINGID);
        WashineLaundryWashingIf washing = new WashineWashing(washingId, null, washingOptions);

        for (String s : washingDb.getParticipantIds(washingId)) {
          washing.addParticipant(s);
        }
        double washineTotalLoad = calculateWashingTotalLoad(washing);
        washing.addToLoad(washineTotalLoad);
        washings.add(washing);
      }

      return washings;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  public WashineLaundryWashingOptionsLaunderIf getBlankWashingOptions() {
    return new WashineWashingOptions() {};
  }

  @Override
  public List<WashineLaundryWashingIf> getParticipantWashings(String participantId)
      throws WashineCoreException {
    WashineWashingDbIf washingDb = new WashineWashingDb();
    List<WashineLaundryWashingIf> washings = new ArrayList<WashineLaundryWashingIf>();
    Result<?> records;
    try {
      records = washingDb.getParticipantWashings(participantId);
      for (org.jooq.Record r : records) {

        WashineWashingOptions washingOptions = fetchWashingOptions(r);
        String washingId = r.getValue(Washingoptions.WASHINGOPTIONS.WASHINGID);
        WashineLaundryWashingIf washing = new WashineWashing(washingId, null, washingOptions);

        for (String s : washingDb.getParticipantIds(washingId)) {

          washing.addParticipant(s);
        }

        double washineTotalLoad = calculateWashingTotalLoad(washing);
        washing.addToLoad(washineTotalLoad);
        washings.add(washing);
      }

      return washings;
    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  public List<WashineLaundryWashingIf> getCommunitiesWashings(String uId)
      throws WashineCoreException {
    List<WashineLaundryWashingIf> washings = new ArrayList<WashineLaundryWashingIf>();
    WashineWashingDbIf washingDb = new WashineWashingDb();
    WashineGroupDbIf groupDb = new WashineGroupDb();
    Result<?> records;
    try {
      List<String> communities = groupDb.getParticipatedGroups(uId);
      for (String c : communities) {
        records = washingDb.getLaunderWashings(c);
        for (org.jooq.Record r : records) {

          WashineWashingOptions washingOptions = fetchWashingOptions(r);
          String washingId = r.getValue(Washingoptions.WASHINGOPTIONS.WASHINGID);
          WashineLaundryWashingIf washing = new WashineWashing(washingId, null, washingOptions);

          for (String s : washingDb.getParticipantIds(washingId)) {
            washing.addParticipant(s);
          }
          double washineTotalLoad = calculateWashingTotalLoad(washing);
          washing.addToLoad(washineTotalLoad);
          washings.add(washing);
        }
      }
      return washings;

    } catch (WashineDataException e) {
      throw new WashineCoreException("WashineCoreException");
    }
  }

  public WashineWashingOptions fetchWashingOptions(org.jooq.Record r) {
    return new WashineWashingOptions(
        r.getValue(Washingoptions.WASHINGOPTIONS.VISIBILITYTIME),
        r.getValue(Washingoptions.WASHINGOPTIONS.DATETIME),
        r.getValue(Washingoptions.WASHINGOPTIONS.DURATIONMINUTES),
        r.getValue(Washingoptions.WASHINGOPTIONS.INITIALLOAD),
        r.getValue(Washingoptions.WASHINGOPTIONS.MAXLOAD),
        r.getValue(Washingoptions.WASHINGOPTIONS.TEMPERATURE),
        r.getValue(Washingoptions.WASHINGOPTIONS.SPINSPEED),
        r.getValue(Washingoptions.WASHINGOPTIONS.FABRICTYPE),
        r.getValue(Washingoptions.WASHINGOPTIONS.COLOR),
        r.getValue(Washingoptions.WASHINGOPTIONS.DETERGENTTYPES),
        r.getValue(Washingoptions.WASHINGOPTIONS.UNDERWEAR),
        r.getValue(Washingoptions.WASHINGOPTIONS.REFUNDTYPE),
        r.getValue(Washingoptions.WASHINGOPTIONS.PICKUPADDRESS),
        r.getValue(Washingoptions.WASHINGOPTIONS.DELIVERYADDRESS),
        r.getValue(Washingoptions.WASHINGOPTIONS.DRYING),
        r.getValue(Washingoptions.WASHINGOPTIONS.PICKUPAVAILABILITY),
        r.getValue(Washingoptions.WASHINGOPTIONS.DELIVERYAVAILABILITY),
        r.getValue(Washingoptions.WASHINGOPTIONS.IRONING),
        r.getValue(Washingoptions.WASHINGOPTIONS.PARTICIPANTMAXLOAD),
        r.getValue(Washingoptions.WASHINGOPTIONS.WASHINGACCESSOPENDATE),
        r.getValue(Washingoptions.WASHINGOPTIONS.WASHINGACCESSCLOSEDATE));
  }
}
