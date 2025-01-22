package washine.washineCore.washing;

import java.util.Date;

public class WashineWashingOptions implements WashineLaundryWashingOptionsIf {

  private int dateTime;
  private int visibilityTime;
  private int durationMinutes;
  private double initialLoad;
  private double maxLoad;
  private String temperature;
  private String spinSpeed;
  private String fabricType;
  private String color;
  private String detergentTypes;
  private boolean underwear;
  private String refundTypes;
  private String pickupAddress;
  private String deliveryAddress;
  private String drying;
  private String pickupAvailability;
  private String deliveryAvailability;
  private boolean ironing;
  private double participantMaxLoad;
  private int washingAccessOpenDate;
  private int washingAccessCloseDate;

  public WashineWashingOptions(
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
      boolean underwear,
      String refundTypes,
      String pickupAddress,
      String deliveryAddress,
      String drying,
      String pickupAvailability,
      String deliveryAvailability,
      boolean ironing,
      double participantMaxLoad,
      int washingAccessOpenDate,
      int washingAccessCloseDate) {
    this.visibilityTime = visibilityTime;
    this.dateTime = dateTime;
    this.durationMinutes = durationMinutes;
    this.initialLoad = initialLoad;
    this.maxLoad = maxLoad;
    this.temperature = temperature;
    this.spinSpeed = spinSpeed;
    this.fabricType = fabricType;
    this.color = color;
    this.detergentTypes = detergentTypes;
    this.underwear = underwear;
    this.refundTypes = refundTypes;
    this.pickupAddress = pickupAddress;
    this.deliveryAddress = deliveryAddress;
    this.drying = drying;
    this.pickupAvailability = pickupAvailability;
    this.deliveryAvailability = deliveryAvailability;
    this.ironing = ironing;
    this.participantMaxLoad = participantMaxLoad;
    this.washingAccessOpenDate = washingAccessOpenDate;
    this.washingAccessCloseDate = washingAccessCloseDate;
  }

  public WashineWashingOptions() {}

  @Override
  public int getVisibilityTime() {
    return visibilityTime;
  }

  @Override
  public int getDatetime() {
    return dateTime;
  }

  @Override
  public int getDurationMinutes() {
    return durationMinutes;
  }

  @Override
  public double getInitialLoad() {
    return initialLoad;
  }

  @Override
  public double getMaxLoad() {
    return maxLoad;
  }

  @Override
  public String getTemperature() {
    return temperature;
  }

  @Override
  public String getSpinSpeed() {
    return spinSpeed;
  }

  @Override
  public String getFabricType() {
    return fabricType;
  }

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public String getDetergentType() {
    return detergentTypes;
  }

  @Override
  public boolean isUnderwear() {
    return underwear;
  }

  @Override
  public String getRefundType() {
    return refundTypes;
  }

  @Override
  public String getPickupAddress() {
    return pickupAddress;
  }

  @Override
  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  @Override
  public String getDryingType() {
    return drying;
  }

  @Override
  public String getPickupAvailability() {
    return pickupAvailability;
  }

  @Override
  public String getDeliveryAvailability() {
    return deliveryAvailability;
  }

  @Override
  public boolean isIroning() {
    return ironing;
  }

  @Override
  public double getMaxLoadParticipant() {
    return participantMaxLoad;
  }

  @Override
  public int getWashingAccessOpenDate() {
    return washingAccessOpenDate;
  }

  @Override
  public int getWashingAccessCloseDate() {
    return washingAccessCloseDate;
  }
}
