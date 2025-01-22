package washine.washineCore;

public class CoreFactoryDefault implements AbstractCoreFactory {
	
		private static CoreFactoryDefault instance;
		
	
		private CoreFactoryDefault() {}
		
		
		public static AbstractCoreFactory getInstance() {
			if (instance == null) {			
				instance = new CoreFactoryDefault();			
			}
			return instance;
		}
	@Override
	public WashineCoreAuthIf createCoreAuth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WashineCoreCommunityIf createCoreWashineCommunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WashineCoreWashingIf createCoreWashing() {
		// TODO Auto-generated method stub
		return null;
	}

}
