package uni.washine.application.views.washes;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;

import com.vaadin.flow.component.html.Paragraph;

import uni.washine.application.utils.UiNotifier;

public class ParticipantWeightDisplay extends HorizontalLayout {
    private Paragraph userWeight;
    ParticipantWeightDisplay(){
        userWeight=new Paragraph();
    }
    private void setWeight(double weight){
        if(weight>0){
            userWeight.setText("Your clothes weight: "+weight+" kg");
        }else{
            userWeight.setText("Your do not have set your clothes weight yet");
        }
	}
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