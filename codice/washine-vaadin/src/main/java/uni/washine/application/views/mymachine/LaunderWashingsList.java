package uni.washine.application.views.mymachine;

import uni.washine.application.utils.WashingsList;
import washine.washineCore.washing.WashineLaundryWashingIf;


public class LaunderWashingsList extends WashingsList{
   
   @Override
   protected void addItem(WashineLaundryWashingIf washing) {
		LaunderWashingListItem item = new LaunderWashingListItem(washing);
      add(item);
	}
    
}
