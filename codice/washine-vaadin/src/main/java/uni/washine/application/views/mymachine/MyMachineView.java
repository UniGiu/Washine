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

    public MyMachineView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        Button buttonPrimary = new Button();
        TabSheet tabSheet = new TabSheet();
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
        buttonPrimary.setText("Create a new washing");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        tabSheet.setWidth("100%");
        setTabSheetSampleData(tabSheet);
        getContent().add(layoutRow);
        layoutRow.add(h2);
        getContent().add(textLarge);
        getContent().add(buttonPrimary);
        getContent().add(tabSheet);
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
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
