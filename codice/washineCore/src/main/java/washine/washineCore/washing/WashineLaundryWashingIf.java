package washine.washineCore.washing;

import java.util.List;

/**
 * Interface representing the basic functionality for managing a laundry washing operation. This
 * interface defines the methods for retrieving the washing operation's details and options.
 */
public interface WashineLaundryWashingIf {

  /**
   * Gets the unique identifier of the washing operation.
   *
   * @return the unique ID of the washing operation
   */
  String getId();

  /**
   * Gets the list of participants who are enabled to join the washing operation.
   *
   * @return a list of participant IDs (as strings)
   */
  List<String> getEnabledParticipants();

  /**
   * Checks if the washing operation is currently active.
   *
   * @return true if the washing operation is active, false otherwise
   */
  boolean isActive();

  /**
   * Gets the washing options that have been configured for the washing operation.
   *
   * @return the washing options for the operation
   */
  WashineLaundryWashingOptionsIf getWashingOptions();
  /**
   * Gets the washing options that have been configured for the washing operation.
   *
   * @return the washing options for the operation
   */
  WashineLaundryWashingOptionsLaunderIf getWashingOptionsLaunder();
}
