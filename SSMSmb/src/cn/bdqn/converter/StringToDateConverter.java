package cn.bdqn.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	private String pattern=null;
	
	public StringToDateConverter(String pattern) {
		super();
		this.pattern = pattern;
	}


	public StringToDateConverter() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Date convert(String dateStr) {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

}

