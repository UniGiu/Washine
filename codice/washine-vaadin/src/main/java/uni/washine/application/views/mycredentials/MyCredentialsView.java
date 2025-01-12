package uni.washine.application.views.mycredentials;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("My Credentials")
@Route("my-credentials")
@Menu(order = 3, icon = LineAwesomeIconUrl.USER_LOCK_SOLID)
public class MyCredentialsView extends Composite<VerticalLayout> {

    public MyCredentialsView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        Paragraph textLarge = new Paragraph();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        FormLayout formLayout2Col = new FormLayout();
        EmailField emailField = new EmailField();
        Button buttonPrimary = new Button();
        Hr hr = new Hr();
        FormLayout formLayout2Col2 = new FormLayout();
        PasswordField passwordField = new PasswordField();
        PasswordField passwordField2 = new PasswordField();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h3.setText("Manage login data");
        h3.setWidth("max-content");
        textLarge.setText("Here you can update your login email and change your password");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        emailField.setLabel("New email");
        emailField.setWidth("min-content");
        buttonPrimary.setText("Change my email");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout2Col2.setWidth("100%");
        passwordField.setLabel("New password");
        passwordField.setWidth("min-content");
        passwordField2.setLabel("Repeat new password");
        passwordField2.setWidth("min-content");
        buttonPrimary2.setText("Change my password");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(h3);
        getContent().add(textLarge);
        getContent().add(layoutColumn2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(emailField);
        formLayout2Col.add(buttonPrimary);
        layoutColumn2.add(hr);
        layoutColumn2.add(formLayout2Col2);
        formLayout2Col2.add(passwordField);
        formLayout2Col2.add(passwordField2);
        formLayout2Col2.add(buttonPrimary2);
    }
}
