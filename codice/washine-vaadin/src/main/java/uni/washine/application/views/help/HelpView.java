package uni.washine.application.views.help;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Help")
@Route("help")
@Menu(order = 6, icon = LineAwesomeIconUrl.QUESTION_CIRCLE)
public class HelpView extends Composite<VerticalLayout> {

    public HelpView() {
        getContent().setHeightFull();
        getContent().setWidthFull();
    }
}
