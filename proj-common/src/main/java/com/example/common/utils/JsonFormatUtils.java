package com.example.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


/**
 * @author zhuchao
 * @date 2022/2/5 11:25 下午
 */
@Slf4j
public class JsonFormatUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 特殊用途：只用于转换成小写
     */
    private static ObjectMapper lowCaseMapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        lowCaseMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        lowCaseMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
    }

    public static String toJSON(Object obj) {
        return toJSON(obj, false);
    }

    public static String toJSON(Object obj, boolean isLowCase) {
        try {
            String toJsonResult;
            if (isLowCase) {
                toJsonResult = lowCaseMapper.writeValueAsString(obj);
            } else {
                toJsonResult = mapper.writeValueAsString(obj);
            }
            return toJsonResult;
        } catch (Exception e) {
            log.error("JsonFormatUtils toJSON Exception, ", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseJSON(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error("JsonFormatUtils fromJSON Exception e1, json:{}", json, e);
            throw new RuntimeException(e);
        }
    }

    public static JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            log.error("JsonFormatUtils readTree Exception, json:{}, ", json, e);
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static <T> T parseJSON(String jsonStr, TypeReference<T> type) {
        return mapper.readValue(jsonStr, type);
    }

}
