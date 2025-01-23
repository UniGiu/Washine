package washine.washineCore;

import java.util.List;
import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineWashing;
import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;

/** Interface for managing washing operations in the WashineCore module. */
public interface WashineCoreWashingIf {

  /**
   * Creates a new washing associated with a laundry person.
   *
   * @param washingId The ID of the washing.
   * @param laundryPersonId The ID of the laundry person.
   * @return the washing
   * @throws WashineCoreException
   */
  WashineLaundryWashingIf createWashing(String laundryPersonId) throws WashineCoreException;

  /**
   * Allows a participant to join a washing with a specified load.
   *
   * @param washingId The ID of the washing.
   * @param participantId The ID of the participant.
   * @param load The load weight the participant wants to contribute.
   * @return the washing
   * @throws WashineCoreException
   */
  public WashineLaundryWashingIf participateToWashing(
      String washingId, String participantId, double load) throws WashineCoreException;

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
   * @return the washing
   * @throws WashineCoreException
   */
  public WashineLaundryWashingIf getWashingOptions(String washingId) throws WashineCoreException;

  /**
   * Updates the washing options for a specific washing.
   *
   * @param washingInfo the updated washing
   * @throws WashineCoreException
   */
  boolean updateWashingOptions(WashineLaundryWashingIf washing) throws WashineCoreException;

  /**
   * Deletes a participant's involvement in a washing.
   *
   * @param washingId The ID of the washing.
   * @param userId The ID of the user to delete.
   * @return true if the participation was successfully deleted, false otherwise.
   * @throws WashineCoreException
   */
  boolean deleteParticipation(String washingId, String userId) throws WashineCoreException;

  public List<WashineLaundryWashingIf> getLaundryPersonWashings(String laundryPersonId)
      throws WashineDataException;
}
