package utile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DateUtil {

	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(date);
		} catch (ParseException ex) {
			return null;
		}

	}

	public static java.sql.Date convertUtilToSql(java.util.Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		} else {
			return null;
		}
	}

	public static java.util.Date convertSqlToUtil(java.sql.Date date) {

		return new java.util.Date(date.getTime());
	}

	public static String addConstraint(String beanAbrev, String atributeName, String operator, Object value) {
		if (value != null) {
			return " AND " + beanAbrev + "." + atributeName + " " + operator + " '" + value + "'";
		}
		return "";
	}

	public static String addConstraintMinMax(String beanAbrev, String atributeName, Object valueMin, Object valueMax) {
		String requette = "";
		if (valueMin != null) {
			requette += " AND " + beanAbrev + "." + atributeName + " >= '" + valueMin + "'";
		}
		if (valueMax != null) {
			requette += " AND " + beanAbrev + "." + atributeName + " <= '" + valueMax + "'";
		}
		return requette;
	}

	public static String addConstraintDate(String beanAbrev, String atributeName, String operator, Date value) {
		return addConstraint(beanAbrev, atributeName, operator, convertUtilToSql(value));
	}

	public static String addConstraintMinMaxDate(String beanAbrev, String atributeName, Date valueMin, Date valueMax) {
		return addConstraintMinMax(beanAbrev, atributeName, convertUtilToSql(valueMin), convertUtilToSql(valueMax));
	}

	public static Date myconvertStringToDate(String date) throws ParseException {
		if (!"".equals(date) && date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(date);
		} else {
			return null;
		}

	}
	

	public static Date myDateConvert(Date date) throws ParseException {
       String Datestr =myConvertDateToString(date);
       return convertStringToDate(Datestr);
	}

	public static String myConvertDateToString(Date date) throws ParseException {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.format(date);
		} else {
			return "";
		}

	}
	public static String myConvertDateToStringtwo(Date date) throws ParseException {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy");
			return simpleDateFormat.format(date);
		} else {
			return "";
		}

	}
	public static Date myconvertStringToDatetwo(String date) throws ParseException {
		if (!"".equals(date) && date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy");
			return simpleDateFormat.parse(date);
		} else {
			return null;
		}

	}

	public static Date localDateToDate(LocalDate date) throws ParseException {
		if (date != null) {
			return myconvertStringToDate(date.toString());
		} else {
			return null;
		}
	}

	public static LocalDate dateToLocalDate(java.util.Date date) {
		if (date != null) {
			Instant instant = date.toInstant();
			ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
			LocalDate localDate = zdt.toLocalDate();
			return localDate;
		} else {
			return null;
		}
	}

	public static Optional<ButtonType> alerter(Alert.AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert.showAndWait();
	}
}