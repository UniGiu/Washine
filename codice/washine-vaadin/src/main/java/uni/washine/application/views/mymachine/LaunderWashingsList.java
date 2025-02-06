package uni.washine.application.views.mymachine;

import java.util.List;

import com.vaadin.flow.server.VaadinSession;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashingsList;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;


public class LaunderWashingsList extends WashingsList{
	
   @Override
   protected void addItem(WashineLaundryWashingIf washing) {
		LaunderWashingListItem item = new LaunderWashingListItem(washing);
      add(item);
	}
    
}
