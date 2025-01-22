package uni.washine.application.views.mymachine;

import washine.washineCore.washing.WashineLaundryWashingIf;

public class MachineBuilder {
    
    public MachineForm getMachineForm() {  
        MachineForm mForm=MachineForm.getInstance(); 
        mForm.reset();   
        return mForm;
    }

    public MachineForm getMachineUpdateForm(WashineLaundryWashingIf washing){
        MachineForm mForm=MachineForm.getInstance(); 
        mForm.init(washing);   
        return mForm;
    }
    
}

