package washine_db.washings;

import java.time.LocalDate;
import java.util.List;

import org.jooq.Result;

import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;

public interface WashineWashingDbIf {
  public boolean participateToWashing(String washingId, String participantId, double load)
      throws WashineDataException;

  public boolean createWashing(String washingId, String laundryPersonId)
      throws WashineDataException;

  public boolean updateWashingOptions(
      String washingId,
      int visibilityTime,
      String dateTime,
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
      String washingAccessOpenDate,
      String washingAccessCloseDate)
      throws WashineDataException;

  public List<String> getLaundryPersonWashingIds(String laundryPersonId)
      throws WashineDataException;

  public List<String> getParticipatedWashingIds(String userId) throws WashineDataException;

  public Result<WashingoptionsRecord> getWashingOptions(String washingId)
      throws WashineDataException;

  public double getParticipationWeight(String washingId, String userId) throws WashineDataException;

  public boolean deleteWashing(String washingId) throws WashineDataException;
  
  public boolean deleteParticipation(String washingId, String userId) throws WashineDataException;
}
