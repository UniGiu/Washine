package washine.washineCore;

import java.util.Random;
import java.util.List;
import org.jooq.Result;

import washine.washineCore.exceptions.WashineCoreException;
import washine_db.exceptions.WashineDataException;
import washine_db.jooq.generated.tables.records.WashingoptionsRecord;
import washine_db.washings.WashineWashingDb;


public class WashineCoreWashing implements WashineCoreWashingIf{

	@Override
	public boolean createWashing(String washingId, String laundryPersonId) throws WashineCoreException {
	    WashineWashingDb washineDb = new WashineWashingDb();

	    try {
	        return washineDb.createWashing(washingId, laundryPersonId);
	    } catch (WashineDataException e) {
	        throw new WashineCoreException("WashineCoreException");
	    }
	}
	@Override
    public boolean participateToWashing(String washingId, String participantId, double load) throws WashineCoreException {
		 WashineWashingDb washineDb = new WashineWashingDb();
		 
        try {
     
            return washineDb.participateToWashing(washingId, participantId, load);
        } catch (WashineDataException e) {
            
        	throw new WashineCoreException("WashineCoreException");
        }
    }
	@Override
    public List<String> getLaundryPersonWashingIds(String laundryPersonId) throws WashineCoreException {
		WashineWashingDb washineDb = new WashineWashingDb();
        try {
            return washineDb.getLaundryPersonWashingIds(laundryPersonId);
        } catch (WashineDataException e) {
        	throw new WashineCoreException("WashineCoreException");
        }
    }
	@Override
    public List<String> getParticipatedWashingIds(String userId) throws WashineCoreException {
		 WashineWashingDb washineDb = new WashineWashingDb();
        try {
            return washineDb.getParticipatedWashingIds(userId);
        } catch (WashineDataException e) {
        	throw new WashineCoreException("WashineCoreException");
        }
    }
    @Override
    public double getParticipationWeight(String washingId, String userId) throws WashineCoreException {
    	WashineWashingDb washineDb = new WashineWashingDb();
        try {
           
            return washineDb.getParticipationWeight(washingId, userId);
        } catch (WashineDataException e) {
            
        	throw new WashineCoreException("WashineCoreException");
        }
    }
	@Override
	public boolean deleteWashing(String washingId) throws WashineCoreException {
	    WashineWashingDb washineDb = new WashineWashingDb();
	    try {
	        
	        if (washineDb.getWashingOptions(washingId) != null) {
	            return washineDb.deleteWashing(washingId);
	        } else {
	            return false;
	        }
	    } catch (WashineDataException e) {
	    	throw new WashineCoreException("WashineCoreException");
	    }
	}
	
	@Override
    public Result<WashingoptionsRecord> getWashingOptions(String washingId) throws WashineCoreException {
        WashineWashingDb washineDb = new WashineWashingDb();
        try {
            return washineDb.getWashingOptions(washingId);
        } catch (WashineDataException e) {
        	throw new WashineCoreException("WashineCoreException");
        }
    }

	@Override
    public boolean updateWashingOptions(
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
        int washingAccessCloseDate
    ) throws WashineCoreException {
        WashineWashingDb washineDb = new WashineWashingDb();
        try {
            return washineDb.updateWashingOptions(
                washingId, visibilityTime, dateTime, durationMinutes, initialLoad, maxLoad,
                temperature, spinSpeed, fabricType, color, detergentTypes, refundTypes,
                underwear, pickupAddress, deliveryAddress, pickupAvailability, deliveryAvailability,
                drying, ironing, participantMaxLoad, washingAccessOpenDate, washingAccessCloseDate
            );
        } catch (WashineDataException e) {
        	throw new WashineCoreException("WashineCoreException");
        }
    }
	@Override
    public boolean deleteParticipation(String washingId, String userId) throws WashineCoreException {
        WashineWashingDb washineDb = new WashineWashingDb();
        try {
            return washineDb.deleteParticipation(washingId, userId);
        } catch (WashineDataException e) {
            
        	throw new WashineCoreException("WashineCoreException");
        }
    }
    
    public String generateUniqueCode() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
          int index = (int) (rnd.nextFloat() * SALTCHARS.length());
          salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
      }
}
