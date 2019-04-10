package com.bebel.web.util;

import enums.SaveType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SaveTypeConvertor implements Converter<String, SaveType> {
    @Override
    public SaveType convert(final String code) {
        return SaveType.fromCode(code);
    }
}
