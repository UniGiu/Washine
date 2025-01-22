package washine.washineCore.washing;

import java.util.Date; 

public class WashineWashingOptions implements WashineLaundryWashingOptionsIf, WashineLaundryWashingOptionsLaunderIf {
	
		
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

	    public WashineWashingOptions(int visibilityTime, int dateTime, int durationMinutes, double initialLoad, double maxLoad, String temperature, 
	    		String spinSpeed, String fabricType, String color, String detergentTypes,boolean underwear, String refundTypes,String pickupAddress,String deliveryAddress,String drying,
	    		String pickupAvailability, String deliveryAvailability,boolean ironing, double participantMaxLoad,int washingAccessOpenDate,int washingAccessCloseDate) 
	    {
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
		public void setDateTime(int dateTime) {
			this.dateTime = dateTime;
		}
		public void setVisibilityTime(int visibilityTime) {
			this.visibilityTime = visibilityTime;
		}
		public void setDurationMinutes(int durationMinutes) {
			this.durationMinutes = durationMinutes;
		}
		public void setInitialLoad(double initialLoad) {
			this.initialLoad = initialLoad;
		}
		public void setMaxLoad(double maxLoad) {
			this.maxLoad = maxLoad;
		}
		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}
		public void setSpinSpeed(String spinSpeed) {
			this.spinSpeed = spinSpeed;
		}
		public void setFabricType(String fabricType) {
			this.fabricType = fabricType;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public void setDetergentTypes(String detergentTypes) {
			this.detergentTypes = detergentTypes;
		}
		public void setUnderwear(boolean underwear) {
			this.underwear = underwear;
		}
		public void setRefundTypes(String refundTypes) {
			this.refundTypes = refundTypes;
		}
		public void setPickupAddress(String pickupAddress) {
			this.pickupAddress = pickupAddress;
		}
		public void setDeliveryAddress(String deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
		}
		public void setDrying(String drying) {
			this.drying = drying;
		}
		public void setPickupAvailability(String pickupAvailability) {
			this.pickupAvailability = pickupAvailability;
		}
		public void setDeliveryAvailability(String deliveryAvailability) {
			this.deliveryAvailability = deliveryAvailability;
		}
		public void setIroning(boolean ironing) {
			this.ironing = ironing;
		}
		public void setParticipantMaxLoad(double participantMaxLoad) {
			this.participantMaxLoad = participantMaxLoad;
		}
		public void setWashingAccessOpenDate(int washingAccessOpenDate) {
			this.washingAccessOpenDate = washingAccessOpenDate;
		}
		public void setWashingAccessCloseDate(int washingAccessCloseDate) {
			this.washingAccessCloseDate = washingAccessCloseDate;
		}
		@Override
		public double setParticipantMaxLoad() {
			// TODO Auto-generated method stub
			return 0;
		}

}
