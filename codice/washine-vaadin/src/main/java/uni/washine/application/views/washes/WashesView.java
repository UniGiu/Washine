package uni.washine.application.views.washes;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import elemental.json.JsonObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import washine.washineCore.user.WashineUserIf;


@PageTitle("Washes")
@Route("washes")
@Menu(order = 2, icon = LineAwesomeIconUrl.TSHIRT_SOLID)
@Uses(Icon.class)
public class WashesView extends Composite<VerticalLayout>  implements BeforeEnterObserver{

    private static Logger logger = LogManager.getLogger();

	private WashineUserIf userData;
    private VerticalLayout layoutAvailWashListContainer;
    private ParticipantWashingsList layoutAvailWashingsList;
    public WashesView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        H3 h3 = new H3();
        WashPictograms washPictograms = new WashPictograms();
        
        layoutAvailWashListContainer = new VerticalLayout();
        layoutAvailWashingsList = new ParticipantWashingsList();
        
        
        
        
        
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h2.setText("Available Washes");
        h2.setWidth("max-content");
        textLarge.setText(
                "Here you can see which washings you are participating to, which ones are available for you and wich ones you participated recently");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        h3.setText("Available washings");
        h3.setWidth("max-content");
       
     
        getContent().add(layoutRow);
        layoutRow.add(washPictograms);
        layoutRow.add(h2);
        getContent().add(textLarge);
        getContent().add(h3);            
      
        getContent().add(layoutAvailWashListContainer);

        layoutAvailWashingsList.getElement().addEventListener("retirefromwashing", event -> {
            JsonObject evtData = event.getEventData();    
            JsonObject evtDetails=evtData.getObject("event.detail");
             String washingId =evtDetails.getString("washingId");       
          
          }).addEventData("event.detail");
          layoutAvailWashingsList.getElement().addEventListener("washingeditparticipation", event -> {
            JsonObject evtData = event.getEventData();    
            JsonObject evtDetails=evtData.getObject("event.detail");
             String washingId =evtDetails.getString("washingId");       
            showForm();
       //     machineForm.init(washingId);
          }).addEventData("event.detail");
          layoutAvailWashingsList.getElement().addEventListener("washingjoin", event -> {
            JsonObject evtData = event.getEventData();    
            JsonObject evtDetails=evtData.getObject("event.detail");
             String washingId =evtDetails.getString("washingId");       
            showForm();
          //  machineForm.init(washingId);
          }).addEventData("event.detail");

          layoutAvailWashingsList.refreshData();
    }
   /**
   * shows the form to add/edit a washing and hides the list of washings
   * 
   */
  private void showForm() {
  //  machineForm.reset();
  //machineForm.setVisible(true);
  layoutAvailWashListContainer.setVisible(false);
}

/**
 * hides the form and shows the list of washings and refreshes it
 */
private void showList() {
  //machineForm.setVisible(false);
  layoutAvailWashListContainer.setVisible(true);
  // TODO: think if the refresh can be in the constructor for initializing and
  // in the SavedEvent handler (so no refresh on cancel)
  layoutAvailWashingsList.refreshData();
}

/**
 * Redirects anonymous users to home
 */
  @Override
  public void beforeEnter(BeforeEnterEvent event) {
	  userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
	  if(userData==null) {
		  event.forwardTo("/");
	  }
  }
}
