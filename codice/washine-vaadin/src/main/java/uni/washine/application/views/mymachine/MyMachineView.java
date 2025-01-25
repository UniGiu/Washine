package uni.washine.application.views.mymachine;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import elemental.json.JsonObject;
import uni.washine.application.utils.UiNotifier;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import washine.washineCore.user.WashineUserIf;

@PageTitle("My Machine")
@Route("my-machine")
@Menu(order = 1, icon = LineAwesomeIconUrl.CC_DINERS_CLUB)
public class MyMachineView extends Composite<VerticalLayout> implements BeforeEnterObserver {

  private static Logger logger = LogManager.getLogger();

  private WashineUserIf userData;
  private MachineForm machineForm;
  private VerticalLayout layoutMachinesListContainer;
  private LaunderWashingsList layoutMachinesList;

  public MyMachineView() {
    machineForm = new MachineForm();
    layoutMachinesListContainer = new VerticalLayout();
    layoutMachinesList = new LaunderWashingsList();

    HorizontalLayout layoutRow = new HorizontalLayout();
    H2 h2 = new H2();
    Paragraph textLarge = new Paragraph();
    Button buttonAddWash = new Button();
    VerticalLayout layoutEditMachine = new VerticalLayout();

    getContent().setWidth("100%");
    getContent().getStyle().set("flex-grow", "1");
    layoutRow.addClassName(Gap.MEDIUM);
    layoutRow.setWidth("100%");
    layoutRow.setHeight("min-content");
    h2.setText("Washings I am offering to the community");
    h2.setWidth("max-content");
    textLarge.setText(
        "Here you can set up a new washing and see a list of the washings you planned and their state.");
    textLarge.setWidth("100%");
    textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    buttonAddWash.setText("Create a new washing");
    buttonAddWash.setWidth("min-content");
    buttonAddWash.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    getContent().add(layoutRow, textLarge, layoutEditMachine);
    layoutRow.add(h2);

    getContent().add(layoutMachinesListContainer);
    layoutMachinesListContainer.add(buttonAddWash, layoutMachinesList);

    getContent().add(machineForm);

    machineForm.addCancelListener(event -> {
      showList();
    });
    machineForm.addSavedListener(event -> {
      machineForm.reset();
      showList();
    });
    buttonAddWash.addClickListener(event -> {
      showForm();
    });

    layoutMachinesList.getElement().addEventListener("washingdeleted", event -> {
      layoutMachinesList.refreshData();
      UiNotifier.showErrorNotification("DELETED");
    });
    //TOREMEMBER: addEventata("event.detail")!!!!!
    layoutMachinesList.getElement().addEventListener("washingedit", event -> {
      JsonObject evtData = event.getEventData();    
      JsonObject evtDetails=evtData.getObject("event.detail");
       String washingId =evtDetails.getString("washingId");     
      machineForm.init(washingId);
      showForm();
    }).addEventData("event.detail");
    showList();
  }

  /**
   * shows the form to add/edit a washing and hides the list of washings
   * 
   */
  private void showForm() {
    machineForm.setVisible(true);
    layoutMachinesListContainer.setVisible(false);
  }

  /**
   * hides the form and shows the list of washings and refreshes it
   */
  private void showList() {
    machineForm.setVisible(false);
    layoutMachinesListContainer.setVisible(true);
    // TODO: think if the refresh can be in the constructor for initializing and
    // in the SavedEvent handler (so no refresh on cancel)
    layoutMachinesList.refreshData();
  }

  /**
   * Redirects anonymous users to home
   */
  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
    if (userData == null) {
      event.forwardTo("/");
    }
  }
}
