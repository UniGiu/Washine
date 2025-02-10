package washine.washineCore;

import java.util.List;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;
import washine_db.exceptions.WashineDataException;

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
  boolean createWashing(String laundryPersonId, WashineLaundryWashingOptionsLaunderIf options)
      throws WashineCoreException;

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
   * Allows participants to update the load of a washing they participate to.
   *
   * @param washingId The ID of the washing.
   * @param participantId The ID of the participant.
   * @param load The load weight the participant wants to contribute.
   * @return the washing
   * @throws WashineCoreException
   */
    public WashineLaundryWashingIf updateParticipantWashingLoad(
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
  public WashineLaundryWashingIf getWashing(String washingId) throws WashineCoreException;

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

  /**
   * Gets the list of a laundry person's washings
   *
   * @param laundryPersonId the id of the laundry person
   * @return the list of washings
   * @throws WashineDataException
   */
  public List<WashineLaundryWashingIf> getLaunderWashings(String laundryPersonId)
      throws WashineCoreException;

  /**
   * Gets the list of washings which a person participated to
   *
   * @param participantId the id of the participant
   * @return the list of washings
   * @throws WashineDataException
   */
  public List<WashineLaundryWashingIf> getParticipantWashings(String participantId)
      throws WashineCoreException;

  /**
   * Gets the list of all the washing available to the user identified by uId
   * 
   * @param uId the user id
   * @return a list of alla the washings available to the user
   * @throws WashineCoreException
   */
  public List<WashineLaundryWashingIf> getCommunitiesWashings(String uId)
	      throws WashineCoreException;
  
  /**
   * Gets a blank washing options object that can be set by the up to create a new washing
   *
   * @return a blank WashineLaundryWashingOptionsLaunderIf
   */
  public WashineLaundryWashingOptionsLaunderIf getBlankWashingOptions();
}
