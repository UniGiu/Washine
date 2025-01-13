package uni.washine.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;

import washine.washineCore.WashineCore;
import washine.washineCore.WashineCoreIf;
import washine.washineCore.user.WashineUserIf;

import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 */
@Layout
@AnonymousAllowed
public class MainLayout extends AppLayout {

	private H1 viewTitle;
	private WashineUserIf userData;

	public MainLayout() {
		userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
		setPrimarySection(Section.DRAWER);
		addDrawerContent();
		addHeaderContent();
	}

	private void addHeaderContent() {
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel("Menu toggle");

		viewTitle = new H1();
		viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

		addToNavbar(true, toggle, viewTitle);
	}

	private void addDrawerContent() {
		Span appName = new Span("WASHINE");
		appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
		Header header = new Header(appName);

		Scroller scroller = new Scroller(createNavigation());

		addToDrawer(header, scroller, createFooter());
	}

	private SideNav createNavigation() {
		SideNav nav = new SideNav();

		List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
		menuEntries.forEach(entry -> {
			if (entry.icon() != null) {
				nav.addItem(new SideNavItem(entry.title(), entry.path(), new SvgIcon(entry.icon())));
			} else {
				nav.addItem(new SideNavItem(entry.title(), entry.path()));
			}
		});

		return nav;
	}

	private Footer createFooter() {
		Footer layout = new Footer();
		if (userData != null) {
			Button buttonLogout = new Button();
			buttonLogout.setText("Log out");
			buttonLogout.setWidth("min-content");
			buttonLogout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			buttonLogout.addClickListener(e -> {
				userData=null;
				WashineCoreIf wCore=new WashineCore();
				wCore.logOut();
				VaadinSession.getCurrent().setAttribute("currentUser", null);
				getUI().ifPresent(ui -> ui.getPage().reload());
			});
			layout.add(buttonLogout);
		}
		return layout;

	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		viewTitle.setText(getCurrentPageTitle());
	}

	private String getCurrentPageTitle() {
		return MenuConfiguration.getPageHeader(getContent()).orElse("");
	}
}
