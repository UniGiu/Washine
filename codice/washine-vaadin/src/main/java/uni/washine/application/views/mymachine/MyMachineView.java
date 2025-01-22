package uni.washine.application.views.mymachine;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import washine.washineCore.user.WashineUserIf;


import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("My Machine")
@Route("my-machine")
@Menu(order = 1, icon = LineAwesomeIconUrl.CC_DINERS_CLUB)
public class MyMachineView extends Composite<VerticalLayout>  implements BeforeEnterObserver{

	private WashineUserIf userData;
	private MachineBuilder machineBuilder;
    
    public MyMachineView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        Button buttonAddWash = new Button();        
        VerticalLayout layoutEditMachine = new VerticalLayout();
        VerticalLayout layoutMachinesListContainer = new VerticalLayout();
        VerticalLayout layoutMachinesList = new VerticalLayout();


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
       
        getContent().add(layoutRow,textLarge,layoutEditMachine);
        layoutRow.add(h2);
        
        getContent().add(layoutMachinesListContainer);
        layoutMachinesListContainer.add(buttonAddWash,layoutMachinesList);
        machineBuilder = new MachineBuilder();

        buttonAddWash.addClickListener(event -> {
            VerticalLayout machineForm = machineBuilder.getMachineForm();
            getContent().add(machineForm);
        });
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

