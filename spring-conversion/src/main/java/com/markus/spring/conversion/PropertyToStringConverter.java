package com.markus.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author: markus
 * @date: 2023/12/30 8:32 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class PropertyToStringConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getObjectType().equals(Properties.class)
                && targetType.getObjectType().equals(String.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        HashSet<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.add(new ConvertiblePair(Properties.class, String.class));
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // 这里可以直接强转
        Properties properties = (Properties) source;

        StringBuilder context = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String content = entry.getKey() + "=" + entry.getValue();
            context.append(content);
            context.append(",");
        }
        return context.substring(0, context.length() - 1);
    }
}
