package uni.washine.application.utils;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;

public class WashingsList extends VerticalLayout{
    protected final WashineCoreWashingIf wCore;
    protected final WashineUserIf userData;
    
   public  WashingsList(){
    wCore= AbstractCoreFactory.getInstance("vaadin").createCoreWashing();  		
	userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
        setPadding(false);
        setSpacing(true);           
    }
    public void removeItems(){
        removeAll();
    }
 
    public void refreshData(){
        
        removeItems();
        fetchData();      
    }
    protected void fetchData() {
    	 try{
         
             List<WashineLaundryWashingIf> washings = wCore.getLaunderWashings(userData.getId());
             for (WashineLaundryWashingIf washing : washings) {
             	addItem(washing);
             }
         }catch(WashineCoreException e){
             UiNotifier.showErrorNotification(e.getMessage());
         }
    }
    
	protected void addItem(WashineLaundryWashingIf washing) {
		add(new WashingListItem(washing));
	}
   
}
