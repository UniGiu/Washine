package washine.washineCore;

import java.util.List;
import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineWashing;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;

/** Interface for managing washing operations in the WashineCore module. */
public interface WashineCoreWashingIf {

  /**
   * Creates a new washing associated with a laundry person.
   *
   * @param washingId The ID of the washing.
   * @param laundryPersonId The ID of the laundry person.
   * @return true if the washing was successfully created, false otherwise.
   * @throws WashineCoreException
   */
  boolean createWashing(String washingId, String laundryPersonId) throws WashineCoreException;

  /**
   * Allows a participant to join a washing with a specified load.
   *
   * @param washingId The ID of the washing.
   * @param participantId The ID of the participant.
   * @param load The load weight the participant wants to contribute.
   * @return true if the participation was successfully recorded, false otherwise.
   * @throws WashineCoreException
   */
  WashineWashing participateToWashing(String washingId, String participantId, double load)
      throws WashineCoreException;

  /**
   * Retrieves the list of washing IDs associated with a specific laundry person.
   *
   * @param laundryPersonId The ID of the laundry person.
   * @return A list of washing IDs, or null if an error occurs.
   * @throws WashineCoreException
   */
  List<String> getLaundryPersonWashingIds(String laundryPersonId) throws WashineCoreException;

  /**
   * Retrieves the list of washing IDs in which a user is participating.
   *
   * @param userId The ID of the user.
   * @return A list of washing IDs, or null if an error occurs.
   * @throws WashineCoreException
   */
  List<String> getParticipatedWashingIds(String userId) throws WashineCoreException;

  /**
   * Retrieves the weight of a user's participation in a specific washing.
   *
   * @param washingId The ID of the washing.
   * @param userId The ID of the user.
   * @return The weight of the user's participation, or 0 if an error occurs.
   * @throws WashineCoreException
   */
  double getParticipationWeight(String washingId, String userId) throws WashineCoreException;

  /**
   * Deletes a washing by its ID if it exists.
   *
   * @param washingId The ID of the washing to be deleted.
   * @return true if the washing was successfully deleted, false otherwise.
   * @throws WashineCoreException
   */
  boolean deleteWashing(String washingId) throws WashineCoreException;

  /**
   * Retrieves the washing options for a specific washing.
   *
   * @param washingId The ID of the washing.
   * @return The washing options for the given washing ID, or null if an error occurs.
   * @throws WashineCoreException
   */
  Result<WashingoptionsRecord> getWashingOptions(String washingId) throws WashineCoreException;

  /**
   * Updates the washing options for a specific washing.
   *
   * @param washingId The ID of the washing to be updated.
   * @param visibilityTime Time for visibility.
   * @param dateTime The date and time of the washing.
   * @param durationMinutes The duration of the washing in minutes.
   * @param initialLoad The initial load.
   * @param maxLoad The maximum load.
   * @param temperature The temperature for the washing.
   * @param spinSpeed The spin speed for the washing.
   * @param fabricType The fabric type for the washing.
   * @param color The color of the washing.
   * @param detergentTypes The types of detergent to be used.
   * @param refundTypes The refund types allowed.
   * @param underwear Indicates if underwear is included.
   * @param pickupAddress The address for pickup.
   * @param deliveryAddress The address for delivery.
   * @param pickupAvailability Pickup availability.
   * @param deliveryAvailability Delivery availability.
   * @param drying Indicates if drying is needed.
   * @param ironing Indicates if ironing is needed.
   * @param participantMaxLoad The maximum load for participants.
   * @param washingAccessOpenDate Access start date.
   * @param washingAccessCloseDate Access end date.
   * @return true if the washing options were successfully updated, false otherwise.
   * @throws WashineCoreException
   */
  boolean updateWashingOptions(
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
      throws WashineCoreException;

  /**
   * Deletes a participant's involvement in a washing.
   *
   * @param washingId The ID of the washing.
   * @param userId The ID of the user to delete.
   * @return true if the participation was successfully deleted, false otherwise.
   * @throws WashineCoreException
   */
  boolean deleteParticipation(String washingId, String userId) throws WashineCoreException;
}
