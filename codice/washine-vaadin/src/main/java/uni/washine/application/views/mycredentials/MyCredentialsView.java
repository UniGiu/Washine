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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import uni.washine.application.utils.UiNotifier;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreAuthIf;
import washine.washineCore.user.WashineUserIf;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("My Credentials")
@Route("my-credentials")
@Menu(order = 3, icon = LineAwesomeIconUrl.USER_LOCK_SOLID)
public class MyCredentialsView extends Composite<VerticalLayout> implements BeforeEnterObserver{

	private WashineUserIf userData;
	final WashineCoreAuthIf wCore;

	public MyCredentialsView() {		
		wCore = AbstractCoreFactory.getInstance("vaadin").createCoreAuth();
		HorizontalLayout layoutRow = new HorizontalLayout();
		H3 h3 = new H3();
		Paragraph textLarge = new Paragraph();
		VerticalLayout layoutColumn2 = new VerticalLayout();
		FormLayout formLayout2Col = new FormLayout();
		EmailField emailField = new EmailField();
		Button buttonChangeEmail = new Button();
		Hr hr = new Hr();
		FormLayout formLayout2Col2 = new FormLayout();
		PasswordField passwordField = new PasswordField();
		PasswordField passwordField2 = new PasswordField();
		Button buttonChangePassword = new Button();
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
		buttonChangeEmail.setText("Change my email");
		buttonChangeEmail.setWidth("min-content");
		buttonChangeEmail.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		formLayout2Col2.setWidth("100%");
		passwordField.setLabel("New password");
		passwordField.setWidth("min-content");
		passwordField2.setLabel("Repeat new password");
		passwordField2.setWidth("min-content");
		buttonChangePassword.setText("Change my password");
		buttonChangePassword.setWidth("min-content");
		buttonChangePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		getContent().add(layoutRow);
		layoutRow.add(h3);
		getContent().add(textLarge);
		getContent().add(layoutColumn2);
		layoutColumn2.add(formLayout2Col);
		formLayout2Col.add(emailField);
		formLayout2Col.add(buttonChangeEmail);
		layoutColumn2.add(hr);
		layoutColumn2.add(formLayout2Col2);
		formLayout2Col2.add(passwordField);
		formLayout2Col2.add(passwordField2);
		formLayout2Col2.add(buttonChangePassword);

		buttonChangeEmail.addClickListener(e -> {
			String email = emailField.getValue();
			handleEmailChange(email);
		});
		buttonChangePassword.addClickListener(e -> {
			String password = passwordField.getValue();
			String password2 = passwordField2.getValue();
			handlePasswordChange(password, password2);
		});
	}

	private boolean handleEmailChange(String email) {
		if(email.isEmpty()) {
			UiNotifier.showErrorNotification("You must type the new email");
			return false;
		}

        if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
        	UiNotifier.showErrorNotification("Please enter a valid email address");
            return false;
        }
		
		WashineUserIf userWithNewEmail;
		
			userWithNewEmail = wCore.updateUserEmail(userData.getId(), email);

			if (userWithNewEmail != null) {
				VaadinSession.getCurrent().setAttribute("currentUser", userWithNewEmail);
				UiNotifier.showSuccessNotification("Email changed to " + userWithNewEmail.getEmail());
			} else {
				UiNotifier.showErrorNotification("Email not updated, are you already registered with this email?");
			}	
			return true;
	}

	private boolean handlePasswordChange(String password, String password2) {
		if(!password.equals(password2)) {
			UiNotifier.showErrorNotification("The two passwords you typed are different");
			return false;
		}
		if(password.isEmpty()) {
			UiNotifier.showErrorNotification("You must type the new password");
			return false;
		}
	
		WashineUserIf userWithNewPassword;		
			userWithNewPassword = wCore.updateUserPassword(userData.getId(), password);
			if (userWithNewPassword != null) {
				UiNotifier.showSuccessNotification("Your password has changed");
			} else {
				UiNotifier.showErrorNotification("Password change failure");
			}
		
			return true;
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
