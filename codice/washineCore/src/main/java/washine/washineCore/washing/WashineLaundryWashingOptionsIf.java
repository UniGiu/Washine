package washine.washineCore.washing;

import java.util.Date;

/**
 * Interface representing the washing options for a laundry process.
 * This interface defines the various settings and parameters that can be used to configure a washing operation.
 */
public interface WashineLaundryWashingOptionsIf {

    /**
     * Gets the visibility time of the washing operation.
     * 
     * @return the visibility time in minutes
     */
    int getVisibilityTime();

    /**
     * Gets the date and time when the washing operation is scheduled.
     * 
     * @return the date and time of the washing operation
     */
    int getDatetime();

    /**
     * Gets the duration of the washing operation in minutes.
     * 
     * @return the duration in minutes
     */
    int getDurationMinutes();

    /**
     * Gets the initial load of the washing operation.
     * 
     * @return the initial load 
     */
    double getInitialLoad();

    /**
     * Gets the maximum load capacity of the washing operation.
     * 
     * @return the maximum load 
     */
    double getMaxLoad();

    /**
     * Gets the temperature setting for the washing operation.
     * 
     * @return the temperature setting as a string 
     */
    String getTemperature();

    /**
     * Gets the spin speed for the washing operation.
     * 
     * @return the spin speed as a string 
     */
    String getSpinSpeed();

    /**
     * Gets the fabric type setting for the washing operation.
     * 
     * @return the fabric type 
     */
    String getFabricType();

    /**
     * Gets the color setting for the washing operation.
     * 
     * @return the color setting 
     */
    String getColor();

    /**
     * Gets the detergent type used in the washing operation.
     * 
     * @return the detergent type
     */
    String getDetergentType();

    /**
     * Determines if the washing operation includes delicate or lingerie items.
     * 
     * @return true if the washing operation includes lingerie, false otherwise
     */
    boolean isUnderwear();

    /**
     * Gets the refund type for the washing operation, if applicable.
     * 
     * @return the refund type 
     */
    String getRefundType();

    /**
     * Gets the pickup address for the laundry operation.
     * 
     * @return the pickup address as a string
     */
    String getPickupAddress();

    /**
     * Gets the delivery address for the laundry operation.
     * 
     * @return the delivery address as a string
     */
    String getDeliveryAddress();

    /**
     * Gets the drying type for the laundry operation 
     * 
     * @return the drying type 
     */
    String getDryingType();

    /**
     * Gets the pickup availability window for the washing operation.
     * 
     * @return the pickup availability as a string 
     */
    String getPickupAvailability();

    /**
     * Gets the delivery availability window for the washing operation.
     * 
     * @return the delivery availability as a string 
     */
    String getDeliveryAvailability();

    /**
     * Determines if ironing is included in the washing operation.
     * 
     * @return true if ironing is included, false otherwise
     */
    boolean isIroning();

    /**
     * Gets the maximum load that a single participant can add to the washing operation.
     * 
     * @return the maximum load for a single participant
     */
    double getMaxLoadParticipant();

    /**
     * Gets the date and time when access to the washing operation opens.
     * 
     * @return the opening date and time for washing access
     */
    int getWashingAccessOpenDate();

    /**
     * Gets the date and time when access to the washing operation closes.
     * 
     * @return the closing date and time for washing access
     */
    int getWashingAccessCloseDate();
}
