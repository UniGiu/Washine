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
import uni.washine.application.utils.UiNotifier;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;

@PageTitle("Washes")
@Route("washes")
@Menu(order = 2, icon = LineAwesomeIconUrl.TSHIRT_SOLID)
@Uses(Icon.class)
public class WashesView extends Composite<VerticalLayout> implements BeforeEnterObserver {

	private static Logger logger = LogManager.getLogger();
	private ParticipantForm washingForm;
	private WashineUserIf userData;
	private VerticalLayout layoutAvailWashListContainer;
	private ParticipantWashingsList layoutAvailWashingsList;
	private String userId;
	WashineCoreWashingIf wCore;
	public WashesView() {
				
		wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
		HorizontalLayout layoutRow = new HorizontalLayout();
		H2 h2 = new H2();
		Paragraph textLarge = new Paragraph();
		H3 h3 = new H3();
		VerticalLayout utilitiesButtons=new VerticalLayout();
		WashPictograms washPictograms = new WashPictograms();
		LoadCalculator loadCalculator=new LoadCalculator();
		layoutAvailWashListContainer = new VerticalLayout();		

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
		
		utilitiesButtons.setWidth("100%");
		utilitiesButtons.setHeight("min-content");
		utilitiesButtons.add(loadCalculator);
		utilitiesButtons.add(washPictograms);
		
		
		getContent().add(layoutRow, textLarge, h3);
			
		layoutRow.add(h2);
		getContent().add(utilitiesButtons);		
		getContent().add(layoutAvailWashListContainer);	
	}
	private void initListeners(){

		layoutAvailWashingsList.getElement().addEventListener("retirefromwashing", event -> {
			JsonObject evtData = event.getEventData();
			JsonObject evtDetails = evtData.getObject("event.detail");
			String washingId = evtDetails.getString("washingId");
			try {
				wCore.deleteParticipation(washingId, userId);
			} catch (WashineCoreException e) {

				UiNotifier.showErrorNotification(e.getMessage());
			}
			showList();
			UiNotifier.showSuccessNotification("You successfully retired from this washing");
		}).addEventData("event.detail");

		layoutAvailWashingsList.getElement().addEventListener("washingeditparticipation", event -> {
			JsonObject evtData = event.getEventData();
			JsonObject evtDetails = evtData.getObject("event.detail");
			String washingId = evtDetails.getString("washingId");
			showForm();
			washingForm.init(washingId, true);
		}).addEventData("event.detail");

		layoutAvailWashingsList.getElement().addEventListener("washingjoin", event -> {
			JsonObject evtData = event.getEventData();
			JsonObject evtDetails = evtData.getObject("event.detail");
			String washingId = evtDetails.getString("washingId");
			showForm();
			washingForm.init(washingId, false);
		}).addEventData("event.detail");
		washingForm.getElement().addEventListener("participantjoincancelled", event -> {
			showList();			
		}).addEventData("event.detail");
		washingForm.getElement().addEventListener("participantjoincreated", event -> {
			showList();
		}).addEventData("event.detail");
		washingForm.getElement().addEventListener("participantjoinupdated", event -> {
			showList();
		}).addEventData("event.detail");
	}
	/**
	 * shows the form to add/edit a washing and hides the list of washings
	 * 
	 */
	private void showForm() {
		washingForm.reset();
		washingForm.setVisible(true);
		layoutAvailWashListContainer.setVisible(false);
	}

	/**
	 * hides the form and shows the list of washings and refreshes it
	 */
	private void showList() {
		washingForm.setVisible(false);
		layoutAvailWashingsList.refreshData();
		layoutAvailWashListContainer.setVisible(true);
		
	}
	private void start(){
		userId = userData.getId();
		layoutAvailWashingsList = new ParticipantWashingsList();
		layoutAvailWashListContainer.add(layoutAvailWashingsList);
		washingForm = new ParticipantForm();
		getContent().add(washingForm);
		initListeners();
		showList();
	}
	/**
	 * Redirects anonymous users to home
	 */
	  @Override
	  public void beforeEnter(BeforeEnterEvent event) {	  
		  userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
		  if(userData==null) {
			  event.forwardTo("/");
			  return;
		  }	else{
			start();
		  }	
		 
	  }
}
