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
        /* 
        washingResult = washing.participateToWashing(testWashingId, testUserId, 1.0);
        */
  }
	@Test
	void createWashingTest() throws WashineCoreException {
		
		assertTrue(created);
	}
	
	@Test
	void getLaundryPersonWashingIdsTest() throws WashineCoreException {
	    List<String> ids = washing.getLaundryPersonWashingIds(testUserId);
	    assertNotNull(ids);
	    assertFalse(ids.isEmpty());
	    assertTrue(ids.contains(testWashingId));
	}
	
	@Test
	void getParticipatedWashingIdsTest() throws WashineCoreException {
		List<String> ids = washing.getParticipatedWashingIds(testUserId);
        assertNotNull(ids);
	}
	@Test
	void getWashingTest() throws WashineCoreException {
		
		assertNotNull(testWashingId);
		assertEquals(options.getDatetime(),1640995200);
	    assertEquals(options.getInitialLoad(), 1.5);
	    assertEquals(options.getMaxLoad(), 7.0);
	    assertEquals(options.getTemperature(), "30°C");
	    assertEquals(options.getSpinSpeed(), "800");
	    assertEquals(options.getFabricType(),"Cotton");
	    assertEquals(options.getColor(), "White");
	    assertEquals(options.getDetergentType(), "Gentle");

	}
	
	
	@Test
	void getLaunderWashingsTest() throws WashineCoreException{
		List<WashineLaundryWashingIf> washings = washing.getLaunderWashings(testUserId);
		assertFalse(washings.isEmpty());
	}
	
}