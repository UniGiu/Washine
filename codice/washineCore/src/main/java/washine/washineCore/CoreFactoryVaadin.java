package washine.washineCore;

public class CoreFactoryVaadin implements AbstractCoreFactory {
	
	private static CoreFactoryVaadin instance;
	
	private CoreFactoryVaadin() {}
	
	public static AbstractCoreFactory getInstance() {
		if (instance == null) {			
			instance = new CoreFactoryVaadin();			
		}
		return instance;
	}

	@Override
	public WashineCoreAuthIf createCoreAuth() {
		return new WashineCoreAuth();	
	}

	@Override
	public WashineCoreCommunityIf createCoreWashineCommunity() {
		return new WashineCoreCommunity();		
	}

	@Override
	public WashineCoreWashingIf createCoreWashing() {
		return new WashineCoreWashing();		
	}

}
