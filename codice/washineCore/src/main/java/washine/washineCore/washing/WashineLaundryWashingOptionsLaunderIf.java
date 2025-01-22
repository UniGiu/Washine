package washine.washineCore.washing;

public interface WashineLaundryWashingOptionsLaunderIf extends WashineLaundryWashingOptionsIf {
	public void setDateTime(int dateTime);
	public void setDetergentTypes(String detergentTypes) ;
	public void setRefundTypes(String refundTypes) ;	
	public void setDrying(String drying);
	public double setParticipantMaxLoad();
	public void setParticipantMaxLoad(double participantMaxLoad) ;
	public void setVisibilityTime(int visibilityTime) ;
	public void setDurationMinutes(int durationMinutes);
	public void setInitialLoad(double initialLoad) ;
	public void setMaxLoad(double maxLoad) ;
	public void setTemperature(String temperature) ;
	public void setSpinSpeed(String spinSpeed) ;
	public void setFabricType(String fabricType);
	public void setColor(String color) ;	
	public void setUnderwear(boolean underwear) ;
	public void setPickupAddress(String pickupAddress);
	public void setDeliveryAddress(String deliveryAddress) ;
	public void setPickupAvailability(String pickupAvailability) ;
	public void setDeliveryAvailability(String deliveryAvailability) ;
	public void setIroning(boolean ironing) ;
	public void setWashingAccessOpenDate(int washingAccessOpenDate) ;
	public void setWashingAccessCloseDate(int washingAccessCloseDate) ;
}

