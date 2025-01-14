package uni.washine.application.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
/**
 * This class pops up notification badges
 */
public class UiNotifier {
	/**
	 * Pops up an error message
	 * @param message the error message
	 */
	static public void showErrorNotification(String message) {
        Notification notification = Notification.show(message);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.TOP_CENTER);
    }
	/**
	 * Pops up a success message
	 * @param message the success message
	 */
	static public void showSuccessNotification(String message) {
        Notification notification = Notification.show(message);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.TOP_CENTER);
    }
}
