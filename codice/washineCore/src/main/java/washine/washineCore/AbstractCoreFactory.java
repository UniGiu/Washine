package washine.washineCore;

public interface AbstractCoreFactory {

    /**
     * Gets the singleton instance of the factory
     * @return The singleton instance of AbstractCoreFactory
     */
    static AbstractCoreFactory getInstance(String type) {
        if ("vaadin".equals(type)) {
            return CoreFactoryVaadin.getInstance();
        } else {
        //we will use it
            return CoreFactoryDefault.getInstance();            
        }
       
    }

    /**
     * Creates a WashineCoreAuthIf
     * @return WashineCoreAuth interface
     */
    public abstract WashineCoreAuthIf createCoreAuth();

    /**
     * Creates a WashineCoreCommunityIf
     * @return WashineCoreCommunity interface
     */
    public abstract WashineCoreCommunityIf createCoreWashineCommunity();

    /**
     * Creates a WashineCoreWashingIf
     * @return WashineCoreWashing interface
     */
    public abstract WashineCoreWashingIf createCoreWashing();

}