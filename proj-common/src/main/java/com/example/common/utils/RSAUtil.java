package com.example.common.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author zhuchao
 * @date 2022/2/5 11:52 下午
 */
public class RSAUtil {



    static final int            EIGHT_BIT               = 8;

    static final int            FOUR_BYTE               = 4;

    static final int			SIGN1					= -128;

    static final int            SIXTEEN_BIT             = 16;

    static final int			CHUNK_SIZE				= 76;

    static final int            BASE_LENGTH             = 255;

    static final int            LOOKUP_LENGTH           = 64;

    static final byte			PAD						= (byte) '=';

    static final int            TWENTY_FOUR_BIT_GROUP = 24;

    static final byte[]			CHUNK_SEPARATOR			= "\r\n".getBytes();

    public static final String	SIGN_TYPE_RSA			= "RSA";

    public static final String	SIGN_ALGORITHMS			= "SHA1WithRSA";

    public static final String	CHARSET					= "charset";

    public static final String	DATE_TIME_FORMAT		= "yyyy-MM-dd HH:mm:ss";

    public static final String	DATE_TIMEZONE			= "GMT+8";

    public static final String	CHARSET_UTF8			= "UTF-8";

    private static final int	DEFAULT_BUFFER_SIZE		= 8192;

    private static final byte[] base64Alphabet			= new byte[BASE_LENGTH];

    private static final byte[]	lookUpBase64Alphabet	= new byte[LOOKUP_LENGTH];

    public static String rsaSign(String content, String privateKey, String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(RSAUtil.SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            java.security.Signature signature = java.security.Signature.getInstance(RSAUtil.SIGN_ALGORITHMS);
            signature.initSign(priKey);
            if (RSAUtil.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(RSAUtil.encodeBase64(signed));
        } catch (Exception e) {
            throw new Exception("RSAContent = " + content + "; charset = " + charset, e);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || RSAUtil.isEmpty(algorithm)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = RSAUtil.readText(ins).getBytes();
        encodedKey = RSAUtil.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) throws Exception {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
            java.security.Signature signature = java.security.Signature.getInstance(RSAUtil.SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            if (RSAUtil.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(RSAUtil.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new Exception("RSAContent = " + content + ",sign=" + sign + ",charset = " + charset, e);
        }
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        RSAUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = RSAUtil.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    public static void io(Reader in, Writer out) throws IOException {
        io(in, out, -1);
    }

    public static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }
        char[] buffer = new char[bufferSize];
        int amount;
        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }

    public static String readText(InputStream in) throws IOException {
        return readText(in, null, -1);
    }

    public static String readText(InputStream in, String encoding, int bufferSize) throws IOException {
        Reader reader = (encoding == null) ? new InputStreamReader(in) : new InputStreamReader(in, encoding);
        return readText(reader, bufferSize);
    }

    public static String readText(Reader reader, int bufferSize) throws IOException {
        StringWriter writer = new StringWriter();
        io(reader, writer, bufferSize);
        return writer.toString();
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty(" ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    static {
        for (int i = 0; i < BASE_LENGTH; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('0' + j);
        }

        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        int lengthDataBits = binaryData.length * EIGHT_BIT;
        int fewerThan24bits = lengthDataBits % TWENTY_FOUR_BIT_GROUP;
        int numberTriplets = lengthDataBits / TWENTY_FOUR_BIT_GROUP;
        byte[] encodedData;
        int encodedDataLength;
        int nbrChunks = 0;
        if (fewerThan24bits != 0) {
            encodedDataLength = (numberTriplets + 1) * 4;
        } else {
            encodedDataLength = numberTriplets * 4;
        }
        if (isChunked) {
            nbrChunks = (CHUNK_SEPARATOR.length == 0 ? 0 : (int) Math.ceil((float) encodedDataLength / CHUNK_SIZE));
            encodedDataLength += nbrChunks * CHUNK_SEPARATOR.length;
        }
        encodedData = new byte[encodedDataLength];
        byte k, l, b1, b2, b3;
        int encodedIndex = 0;
        int dataIndex;
        int i;
        int nextSeparatorIndex = CHUNK_SIZE;
        int chunksSoFar = 0;
        for (i = 0; i < numberTriplets; i++) {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);
            byte val1 = ((b1 & SIGN1) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN1) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);
            byte val3 = ((b3 & SIGN1) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
            encodedIndex += 4;

            if (isChunked) {
                if (encodedIndex == nextSeparatorIndex) {
                    System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
                    chunksSoFar++;
                    nextSeparatorIndex = (CHUNK_SIZE * (chunksSoFar + 1)) + (chunksSoFar * CHUNK_SEPARATOR.length);
                    encodedIndex += CHUNK_SEPARATOR.length;
                }
            }
        }

        dataIndex = i * 3;

        if (fewerThan24bits == EIGHT_BIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 0x03);
            byte val1 = ((b1 & SIGN1) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == SIXTEEN_BIT) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN1) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN1) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex + 3] = PAD;
        }

        if (isChunked) {
            if (chunksSoFar < nbrChunks) {
                System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
            }
        }
        return encodedData;
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        base64Data = discardNonBase64(base64Data);
        if (base64Data.length == 0) {
            return new byte[0];
        }
        int numberQuadruple = base64Data.length / FOUR_BYTE;
        byte[] decodedData;
        byte b1, b2, b3, b4, marker0, marker1;
        int encodedIndex = 0;
        int dataIndex;
        {
            int lastData = base64Data.length;
            while (base64Data[lastData - 1] == PAD) {
                if (--lastData == 0) {
                    return new byte[0];
                }
            }
            decodedData = new byte[lastData - numberQuadruple];
        }

        for (int i = 0; i < numberQuadruple; i++) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker1 = base64Data[dataIndex + 3];

            b1 = base64Alphabet[base64Data[dataIndex]];
            b2 = base64Alphabet[base64Data[dataIndex + 1]];

            if (marker0 != PAD && marker1 != PAD) {
                b3 = base64Alphabet[marker0];
                b4 = base64Alphabet[marker1];
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex + 2] = (byte) (b3 << 6 | b4);
            } else if (marker0 == PAD) {
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
            } else {
                b3 = base64Alphabet[marker0];
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }

    static byte[] discardNonBase64(byte[] data) {
        byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;

        for (byte datum : data) {
            if (isBase64(datum)) {
                groomedData[bytesCopied++] = datum;
            }
        }
        byte[] packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }

    private static boolean isBase64(byte octect) {
        if (octect == PAD) {
            return true;
        } else {
            return base64Alphabet[octect] != -1;
        }
    }
}
