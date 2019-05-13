package br.com.i2assessoria.consultaplaca.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanSimNaoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return value ? "S": "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        if( "S".equals(value) )
            return true;
        else if( "N".equals(value) )
            return false;

        return null;
    }

}