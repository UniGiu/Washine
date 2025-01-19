package uni.washine.application.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.server.VaadinSession;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import uni.washine.application.utils.AuthenticationComponent;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        Paragraph textMedium = new Paragraph();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h1.setText("Welcome to Washine");
        h1.setWidth("max-content");
        textMedium.setText(
                "Whashine is a Washing Machine sharing utility that facilitates you in sharing your washing machine with people you trust.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");

        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(textMedium);
        if (VaadinSession.getCurrent().getAttribute("currentUser") == null) {
            // layoutColumn2.add(getLoginForm());
            // layoutColumn2.add(getSignInForm());
            AuthenticationComponent authComp = new AuthenticationComponent();
            authComp.setWidth("100%");
            authComp.getStyle().set("flex-grow", "1");

            getContent().add(authComp);
        }
    }
}
