package com.example.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuchao
 * @date 2022/2/5 11:58 下午
 */
@Slf4j
public final class GZipUtil {
    /** * Do a gzip operation. */
    public static byte[] gzip(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipEntry ze = new ZipEntry("servletservice");
        ZipOutputStream zos = new ZipOutputStream(baos);
        zos.putNextEntry(ze);
        zos.write(data, 0, data.length);
        zos.close();
        byte[] zipBytes = baos.toByteArray();
        return zipBytes;
    }

    public static byte[] unzip(byte[] zipBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
        ZipInputStream zis = new ZipInputStream(bais);
        zis.getNextEntry();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final int bufsiz = 4096;
        byte inbuf[] = new byte[bufsiz];
        int n;
        try{
            while ((n = zis.read(inbuf, 0, bufsiz)) != -1) {
                baos.write(inbuf, 0, n);
            }
        } catch(Exception e){
            log.error("unzip error", e);
        }

        byte[] data = baos.toByteArray();
        zis.close();
        return data;
    }
}
