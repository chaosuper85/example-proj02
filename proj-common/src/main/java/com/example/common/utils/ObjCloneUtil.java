package com.example.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuchao
 * @date 2022/2/6 9:33 上午
 */
@Slf4j
public class ObjCloneUtil {

    @SuppressWarnings("unchecked")
    public static <T> T deepClone(T obj) {

        T cloneObj = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);

            byte[] buf = baos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            cloneObj = (T) ois.readObject();

        } catch (IOException e) {
            log.error("【clone异常：IOException】", e);
        } catch (ClassNotFoundException e) {
            log.error("【clone异常：ClassNotFoundException】", e);
        }
        return cloneObj;
    }
}
