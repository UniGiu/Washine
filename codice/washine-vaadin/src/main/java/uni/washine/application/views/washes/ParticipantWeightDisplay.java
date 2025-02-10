package uni.washine.application.views.washes;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;

import com.vaadin.flow.component.html.Paragraph;

import uni.washine.application.utils.UiNotifier;

public class ParticipantWeightDisplay extends Paragraph {
   
   /**
    * 
    * @param weight weight to display
    */
    private void setWeight(double weight){    
    		getStyle().setFontWeight(800);
            setText("Your clothes weight: "+weight+" kg");   
	}
    /**
     * Refreshes the display of the user contribution to the total weight
     * @param washingId id of the washing
     * @param userId id of the user
     */
    public void refresh(String washingId, String userId){
        WashineCoreWashingIf wCore= AbstractCoreFactory.getInstance("vaadin").createCoreWashing();  		
        double weight= 0;
        try {
    	   weight= wCore.getParticipationWeight( washingId, userId);
          
       }catch (WashineCoreException e) {
    	   UiNotifier.showErrorNotification("An error occourred in retreiving the weight of your clothes");
    	   UiNotifier.showErrorNotification(e.getMessage());
       }   
         setWeight(weight);
        }
}