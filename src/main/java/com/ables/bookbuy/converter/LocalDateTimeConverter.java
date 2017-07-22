package com.ables.bookbuy.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime ldt) {
		// TODO Auto-generated method stub
		return (ldt == null ? null : Timestamp.valueOf(ldt));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		// TODO Auto-generated method stub
		return (timestamp == null ? null : timestamp.toLocalDateTime());
	}

}
