package washine.washineCore;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;
import washine.washineCore.washing.WashineWashingOptions;

class WashineCoreWashingTest{
	
	
	static WashineCoreWashing washing;
	static WashineLaundryWashingOptionsLaunderIf options;
	static String testUserId;
	static String testWashingId;
	static boolean created;
	static WashineLaundryWashingIf washingResult;
	

	@BeforeAll
	static void setUp() throws WashineCoreException, InterruptedException {
		
		washing = new WashineCoreWashing();
		options = new WashineWashingOptions();
		
        ((WashineWashingOptions) options).setDateTime(1640995200); 
        ((WashineWashingOptions) options).setVisibilityTime(10);
        ((WashineWashingOptions) options).setDurationMinutes(90);
        ((WashineWashingOptions) options).setInitialLoad(1.5);
        ((WashineWashingOptions) options).setMaxLoad(7.0);
        ((WashineWashingOptions) options).setTemperature("30°C");
        ((WashineWashingOptions) options).setSpinSpeed("800");
        ((WashineWashingOptions) options).setFabricType("Cotton");
        ((WashineWashingOptions) options).setColor("White");
        ((WashineWashingOptions) options).setDetergentTypes("Gentle");
        ((WashineWashingOptions) options).setRefundTypes("None");
        
        testUserId = "5444046707115218308";
        created = washing.createWashing(testUserId, options);
        assertTrue(created);
        List<String> washingIds = washing.getLaundryPersonWashingIds(testUserId);
        assertFalse(washingIds.isEmpty());
        
        
        testWashingId = washingIds.get(washingIds.size() - 1);
        assertNotNull(testWashingId);
        System.out.println(testWashingId); // per vedere quale prende 
        System.out.println(testUserId);
        
       /* 
        washingResult = washing.participateToWashing(testWashingId, testUserId, 1.0);
        System.out.println(testWashingId);
        System.out.println(washingResult);
        
       */

        
       
	}
	@Test
	void createWashingTest() throws WashineCoreException {
		assertTrue(created);
	}
	
	/* NON VA*/
	@Test
	void partecipateToWashingTest() throws WashineCoreException {
		 System.out.println(testWashingId); // per vedere quale prende 
	        System.out.println(testUserId);
		washingResult = washing.participateToWashing(testWashingId, testUserId, 1.0);
		assertNotNull(washingResult);
	}
	
	
	/*@Test
	void getLaundryPersonWashingIdsTest() throws WashineCoreException {
	    List<String> ids = washing.getLaundryPersonWashingIds(testUserId);
	    assertNotNull(ids);
	    assertFalse(ids.isEmpty());
	    assertTrue(ids.contains(testWashingId));
	}
	*/
	@Test
	void getParticipatedWashingIdsTest() throws WashineCoreException {
		List<String> ids = washing.getParticipatedWashingIds(testUserId);
        assertNotNull(ids);
	}
	
	/* PARTICIPATETOWASHING NON VA 
	@Test
	void getParticipationWeightTest throws WashineCoreException {
		double weight = washing.getParticipationWeight(testWashingId, testUserId);
        assertEquals(1.0, weight)
	}*/
	
	/*NON FARE 
	@Test
	void deleteWashingTest() throws WashineCoreException {
		boolean deleted = washing.deleteWashing(testWashingId);
		assertTrue(deleted);
		assertFalse(washingResult.contains(testWashingId));
	}*/
	
	/*@Test
	void getWashingTest() throws WashineCoreException {
		assertNotNull(testWashingId);
		WashineLaundryWashingOptionsIf washingOptions = new WashineLaundryWashingOptionsIf;
		assertEquals(options.getDatetime(), washingOptions.getDatetime());
	    assertEquals(options.getInitialLoad(), washingOptions.getInitialLoad());
	    assertEquals(options.getMaxLoad(), washingOptions.getMaxLoad());
	    assertEquals(options.getTemperature(), washingOptions.getTemperature());
	    assertEquals(options.getSpinSpeed(), washingOptions.getSpinSpeed());
	    assertEquals(options.getFabricType(), washingOptions.getFabricType());
	    assertEquals(options.getColor(), washingOptions.getColor());
	    assertEquals(options.getDetergentType(), washingOptions.getDetergentType());

	}*/
	/*@Test
	void deleteParticipation() throws WashineCoreException  {
		assertNotNull(testWashingId);
		
	    assertTrue(washingResult.contains(testWashingId));
		boolean deleted = washing.deleteParticipation(testWashingId, testUserId);
		assertTrue(deleted);
		participatedWashingIds = washing.getParticipatedWashingIds(testUserId);
	    assertFalse(participatedWashingIds.contains(testWashingId));
	}*/
	/*@Test
	void calculateWashingTotalLoadTest() throws WashineCoreException{
	
	
	}*/
	
	@Test
	void getLaunderWashingsTest() throws WashineCoreException{
		List<WashineLaundryWashingIf> washings = washing.getLaunderWashings(testUserId);
		assertFalse(washings.isEmpty());
		
	}
	
	/*@Test
	void getParticipantWashingsTest() throws WashineCoreException{
		
		washing.participateToWashing(testWashingId, testUserId, 2.0);
		List<WashineLaundryWashingIf> washings = washing.getParticipantWashings(testUserId);
		assertFalse(washings.isEmpty());
		assertEquals(testWashingId, washings.get(0).getWashingId());
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}