package uni.washine.application.utils;
/**
 * Helper class for date time conversion
 */
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
public class WashineTimeUtils {

	private WashineTimeUtils() {};
	
	/**
 * Utility function to transoform unix timestamp in seconds to LocalDateTime
 * 
 * @param timestampSeconds
 * @return the LocalDateTime of the date
 */
static public  LocalDateTime unixTimestampToLocalDate(int timestampSeconds) {
	Instant instant = Instant.ofEpochSecond(timestampSeconds);
	return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
}
 	/**
 * Utility function to transoform LocalDateTime to local
 * String
 * 
 * @param ldt
 * @return the date in local format string
 */
static public String dateTimeToString(LocalDateTime ldt) {	
	
	return ldt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
}
	/**
 * Utility function to transoform unix timestamp in seconds to local
 * String
 * 
 * @param timestampSeconds
 * @return the date in local format string
 */
static public String unixTimestampToString(int timestampSeconds) {		
	return dateTimeToString(unixTimestampToLocalDate(timestampSeconds));
}
}
