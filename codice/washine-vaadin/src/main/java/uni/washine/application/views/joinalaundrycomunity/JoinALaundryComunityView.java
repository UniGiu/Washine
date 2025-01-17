package uni.washine.application.views.joinalaundrycomunity;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import washine.washineCore.user.WashineUserIf;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Join A Laundry Comunity")
@Route("join-a-laundry-comunity")
@Menu(order = 5, icon = LineAwesomeIconUrl.SIGN_IN_ALT_SOLID)
public class JoinALaundryComunityView extends Composite<VerticalLayout>  implements BeforeEnterObserver{

	private WashineUserIf userData;

    public JoinALaundryComunityView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h2.setText("Join a Loundry Community");
        h2.setWidth("max-content");
        textLarge.setText(
                "Enter the code you received by a machine owner to join their laundry community. Choose a meaningful community name so that you will be able to understand who is offering the wash");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        textField.setLabel("Join code");
        textField.setWidth("min-content");
        textField2.setLabel("Community name");
        textField2.setWidth("min-content");
        buttonPrimary.setText("Join");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(h2);
        getContent().add(textLarge);
        getContent().add(layoutColumn2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(buttonPrimary);
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
