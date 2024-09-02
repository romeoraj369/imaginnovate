package com.imaginnovate.utils;

import com.opencsv.bean.AbstractBeanField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter extends AbstractBeanField<Date, String> {

	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Override
	protected Date convert(String value) {
		try {
			return new java.sql.Date(dateFormat.parse(value).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("Date conversion error", e);
		}
	}
}
