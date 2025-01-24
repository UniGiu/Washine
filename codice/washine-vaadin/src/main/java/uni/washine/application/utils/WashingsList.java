package uni.washine.application.utils;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;

public class WashingsList extends VerticalLayout{
    final WashineCoreWashingIf wCore;

   public  WashingsList(){
    wCore= AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
  
		
        setPadding(false);
            setSpacing(true);           
    }
    public void removeItems(){
        removeAll();
    }
    //TODO: usare WashineCoreException quando aggiornato nel core
    public void refreshData(){
        WashineUserIf userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
        try{
            List<WashineLaundryWashingIf> washings = wCore.getLaunderWashings(userData.getId());
            for (WashineLaundryWashingIf washing : washings) {
            	addItem(washing);
            }
        }catch(Exception e){
            UiNotifier.showErrorNotification(e.getMessage());
        }
      
    }
    
	protected void addItem(WashineLaundryWashingIf washing) {
		add(new WashingListItem(washing));
	}
   
}
