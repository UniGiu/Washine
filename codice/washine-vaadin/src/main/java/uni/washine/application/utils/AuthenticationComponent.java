package uni.washine.application.utils;

import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.server.VaadinSession;


import washine.washineCore.WashineCoreAuth;
import washine.washineCore.WashineCoreAuthIf;
import washine.washineCore.user.WashineUserIf;

public class AuthenticationComponent extends VerticalLayout {
	
	public AuthenticationComponent() {
		super();
		add(getLoginForm(),getSignInForm());
	}
	
	private VerticalLayout getLoginForm(){
	        VerticalLayout layoutColumnLogin = new VerticalLayout();
	        H2 h2Login = new H2();
	        EmailField emailFieldLogin = new EmailField();
	        PasswordField passwordFieldLogin = new PasswordField();
	        Button buttonLogin = new Button();

	        layoutColumnLogin.setWidth("100%");
	        layoutColumnLogin.getStyle().set("flex-grow", "1");
	        h2Login.setText("Log in");
	        h2Login.setWidth("max-content");
	        emailFieldLogin.setLabel("Email *");
	        emailFieldLogin.setWidth("min-content");
	        passwordFieldLogin.setLabel("Password *");
	        passwordFieldLogin.setWidth("min-content");
	        buttonLogin.setText("Log in");
	        buttonLogin.setWidth("min-content");
	        buttonLogin.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

	        layoutColumnLogin.add(h2Login);
	        layoutColumnLogin.add(emailFieldLogin);
	        layoutColumnLogin.add(passwordFieldLogin);
	        layoutColumnLogin.add(buttonLogin);

	        buttonLogin.addClickListener(e -> {
	            String email = emailFieldLogin.getValue();
	            String password = passwordFieldLogin.getValue();
	            
	            if (validateAuthInput(email, password)) {
	                try {
	          handleLogin(email, password);
	        } catch (SQLException e1) {
	          // TODO Auto-generated catch block
	        e1.printStackTrace();
	        }
	            }
	        });

	        return layoutColumnLogin;
	    }
	    private VerticalLayout getSignInForm(){
	        VerticalLayout layoutColumnSignIn = new VerticalLayout();
	        H2 h2SignIn = new H2();
	        EmailField emailFieldSignIn = new EmailField();
	        PasswordField passwordFieldSignIn = new PasswordField();
	        Button buttonSignIn = new Button();

	        layoutColumnSignIn.setWidth("100%");
	        layoutColumnSignIn.getStyle().set("flex-grow", "1");
	        h2SignIn.setText("Subscribe");
	        h2SignIn.setWidth("max-content");
	        emailFieldSignIn.setLabel("Email *");
	        emailFieldSignIn.setWidth("min-content");
	        passwordFieldSignIn.setLabel("Password *");
	        passwordFieldSignIn.setWidth("min-content");
	        buttonSignIn.setText("Subscribe");
	        buttonSignIn.setWidth("min-content");
	        buttonSignIn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

	        layoutColumnSignIn.add(h2SignIn);
	        layoutColumnSignIn.add(emailFieldSignIn);
	        layoutColumnSignIn.add(passwordFieldSignIn);
	        layoutColumnSignIn.add(buttonSignIn);

	        buttonSignIn.addClickListener(e -> {
	            String email = emailFieldSignIn.getValue();
	            String password = passwordFieldSignIn.getValue();
	            
	            if (validateAuthInput(email, password)) {
	               try {
	        handleSignUp(email, password);
	      } catch (SQLException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	            }
	        });

	        return layoutColumnSignIn;
	    }
	   
	    private boolean validateAuthInput(String email, String password) {
	        if (email.isEmpty() || password.isEmpty()) {
	        	UiNotifier.showErrorNotification("Please fill in all required fields");
	            return false;
	        }
	        if (password.length() < 8) {
	        	UiNotifier.showErrorNotification("Password must be at least 8 characters long");
	            return false;
	        }
	      
	        if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
	        	UiNotifier.showErrorNotification("Please enter a valid email address");
	            return false;
	        }
	        return true;
	    }

	    private void handleLogin(String email, String password) throws SQLException {
	        WashineCoreAuthIf wCore=new WashineCoreAuth();
	        WashineUserIf loggedUser = wCore.authenticateUser(email, password);

	        if (loggedUser!=null) {
	            VaadinSession.getCurrent().setAttribute("currentUser", loggedUser);
	            getUI().ifPresent(ui -> ui.getPage().reload());
	        } else {
	        	UiNotifier.showErrorNotification("Invalid email or password");
	        }
	    }

	    private void handleSignUp(String email, String password) throws SQLException {
	    	  WashineCoreAuthIf wCore=new WashineCoreAuth();
	          WashineUserIf createdUser = wCore.addUser(email, password);        
	        if (createdUser!=null) {          
	        	UiNotifier.showSuccessNotification("Account created for " + createdUser.getEmail()+ ", please log in.");           
	        } else {
	        	UiNotifier.showErrorNotification("Invalid email or password, or user already registered");
	        }
	    }
}
