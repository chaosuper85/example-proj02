package com.example.common.utils;

import java.io.StringReader;
import java.io.StringWriter;
import feign.jaxb.JAXBContextFactory;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuchao
 * @date 2022/2/6 12:02 上午
 */
@Slf4j
public class ObjectXmlMapperUtil {

    private static JAXBContextFactory jaxbContextFactory = new JAXBContextFactory.Builder()
            .withMarshallerJAXBEncoding("UTF-8")
            .withMarshallerFormattedOutput(true)
            .build();


    public static <T> String beanToXml(T bean) {
        try {
            Marshaller marshaller = jaxbContextFactory.createMarshaller(bean.getClass());
            StringWriter writer = new StringWriter();
            marshaller.marshal(bean, writer);
            return writer.toString();
        } catch (Exception e) {
            log.error("beanToXml Exception", e);
        }

        return null;
    }


    public static <T> T xmlToBean(String xml, Class<?> valueType) {
        try {
            Unmarshaller unmarshaller = jaxbContextFactory.createUnmarshaller(valueType);
            @SuppressWarnings("unchecked")
            T obj = (T) unmarshaller.unmarshal(new StringReader(xml));
            return obj;
        } catch (Exception e) {
            log.error("xmlToBean Exception", e);
        }

        return null;
    }

// 以下为测试程序
    @XmlRootElement(name="OrderDetailResult")
    @Data
    static class OrderDetailResult {
        private String bookManMobile;
        private String bookManName;
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><OrderDetailResult><bookManMobile>18310201535</bookManMobile><bookManName>滕博超</bookManName></OrderDetailResult>";
        OrderDetailResult orderDetailResult = xmlToBean(xml, OrderDetailResult.class);
        System.out.println((JsonFormatUtils.toJSON(orderDetailResult)));
    }
}
