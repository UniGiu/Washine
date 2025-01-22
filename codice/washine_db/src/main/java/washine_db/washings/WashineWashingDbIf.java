package washine_db.washings;

import java.util.List;

import org.jooq.Result;

import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;

/** Interface for the interaction between the washings part of the database */
public interface WashineWashingDbIf {
  /**
   * Add a participation tuple to the washing participation table
   *
   * @param washingId id of the washing
   * @param participantId id of the participant
   * @param load load in kg of the participant
   * @return true if only one tuple is added
   * @throws WashineDataException
   */
  public boolean participateToWashing(String washingId, String participantId, double load)
      throws WashineDataException;

  /**
   * Creates a new washing tuple in the washing table
   *
   * @param washingId id of the washing
   * @param laundryPersonId id of the creator of the washing
   * @return true if only one tuple is added
   * @throws WashineDataException
   */
  public boolean createWashing(String washingId, String laundryPersonId)
      throws WashineDataException;

  /**
   * Updates the options of a washing
   *
   * @param washingId
   * @param visibilityTime
   * @param dateTime
   * @param durationMinutes
   * @param initialLoad
   * @param maxLoad
   * @param temperature
   * @param spinSpeed
   * @param fabrycType
   * @param color
   * @param detergentTypes
   * @param refundTypes
   * @param underwear
   * @param pickupAddress
   * @param deliveryAddress
   * @param pickupAvailability
   * @param deliverAvailability
   * @param drying
   * @param ironing
   * @param participantMaxLoad
   * @param washingAccessOpenDate
   * @param washingAccessCloseDate
   * @return true if only one tuple is modified
   * @throws WashineDataException
   */
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
      throws WashineDataException;

  /**
   * Selects from the database all the washing ids of the washings created by a person through his
   * id
   *
   * @param laundryPersonId id of the creator of the washings
   * @return the list of washing ids
   * @throws WashineDataException
   */
  public List<String> getLaundryPersonWashingIds(String laundryPersonId)
      throws WashineDataException;

  /**
   * Selects from the database the washings that a person participated to thorugh his id
   *
   * @param userId id of the person
   * @return the list of washing ids
   * @throws WashineDataException
   */
  public List<String> getParticipatedWashingIds(String userId) throws WashineDataException;

  /**
   * Selects from the database the ids of the participant to a washing
   *
   * @param washingId id of the washing
   * @return the list of ids
   * @throws WashineDataException
   */
  public List<String> getParticipantIds(String washingId) throws WashineDataException;

  /**
   * Selects from the database all the options of a washing previously inserted through the washing
   * id
   *
   * @param washingId id of the washing
   * @return the record containing the id and all the options
   * @throws WashineDataException
   */
  public Result<WashingoptionsRecord> getWashingOptions(String washingId)
      throws WashineDataException;

  /**
   * Gets the load that a person participated to a washing with from the database
   *
   * @param washingId id of the washing
   * @param userId id of the person
   * @return the load in kg
   * @throws WashineDataException
   */
  public double getParticipationWeight(String washingId, String userId) throws WashineDataException;

  /**
   * Deletes from the database a washing through its id
   *
   * @param washingId id of the washing
   * @return true if only one tuple is deleted
   * @throws WashineDataException
   */
  public boolean deleteWashing(String washingId) throws WashineDataException;

  /**
   * Deletes a participation to a washing through the id of the person who participated and the
   * washing id
   *
   * @param washingId id of the washing
   * @param userId id of the person who participated
   * @return true if only one tuple is deleted
   * @throws WashineDataException
   */
  public boolean deleteParticipation(String washingId, String userId) throws WashineDataException;

  /**
   * Selects the date and time of a washing through its id
   *
   * @param washingId id of the washing
   * @return the date
   * @throws WashineDataException
   */
  public int getWashingDateTime(String washingId) throws WashineDataException;

  /**
   * Selects the date of the access opening for a washing through its id
   *
   * @param washingId id of the washing
   * @return the date
   * @throws WashineDataException
   */
  public int getWashingAccessOpenDate(String washingId) throws WashineDataException;

  /**
   * Selects the date of the access closing for a washing through its id
   *
   * @param washingId id of the washing
   * @return the date
   * @throws WashineDataException
   */
  public int getWashingAccessCloseDate(String washingId) throws WashineDataException;

  /**
   * Selects a washing's max load (that the laundry person set) through its id
   *
   * @param washingId id of the washing
   * @return the max load
   * @throws WashineDataException
   */
  public double getWashingMaxLoad(String washingId) throws WashineDataException;

  /**
   * Selects a washing's max load for a participant (that the laundry person set) through its id
   *
   * @param washingId id of the washing
   * @return the participant max load
   * @throws WashineDataException
   */
  public double getWashingParticipantMaxLoad(String washingId) throws WashineDataException;

  /**
   * Selects a washing's initial load (that the laundry person set) through its id
   *
   * @param washingId id of the washing
   * @return the inital load
   * @throws WashineDataException
   */
}
