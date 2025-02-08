package uni.washine.application.views.help;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uni.washine.application.utils.WashineVideo;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Help")
@Route("help")
@Menu(order = 6, icon = LineAwesomeIconUrl.QUESTION_CIRCLE)
public class HelpView extends Composite<VerticalLayout> {

  public HelpView() {

    VerticalLayout layout1 = new VerticalLayout();
    layout1.setWidth("100%");
    layout1.getStyle().set("flex-grow", "1");
    H2 subtitle1 = new H2("Registration and log in");
    Anchor loginLink = new Anchor("", "Home page");
    Paragraph paragraph1 =
        new Paragraph(
            new Text("To get started with Washine go to the "),
            loginLink,
            new Text(
                ". First of all you need to register a new account, you can do that by inserting a valid email and a safe password in the specific fields in the bottom part of the page and clicking the subscribe button. Make sure that the email is correct and that your password is at least 8 characters long. After successfully creating an account you can go ahead and insert the same email and password you just entered in the fields in the top part of the page. Make sure you are entering the same credentials you registerred with. By clicking on the login button you should be set to use Washine. You can follow the video below for further clarification"));
    WashineVideo video1 = new WashineVideo("video url");
    video1.setWidth("600px");
    layout1.add(subtitle1, paragraph1, video1);
    getContent().add(layout1);
  }
}
