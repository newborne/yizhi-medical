package com.yizhi.hospital.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Http util.
 */
@Slf4j
public final class HttpUtil {

    /**
     * The Post.
     */
    static final String POST = "POST";
    /**
     * The Get.
     */
    static final String GET = "GET";
    /**
     * The Conn timeout.
     */
    static final int CONN_TIMEOUT = 30000;// ms
    /**
     * The Read timeout.
     */
    static final int READ_TIMEOUT = 30000;// ms

    /**
     * Do post byte [ ].
     *
     * @param strUrl  the str url
     * @param reqData the req data
     * @return the byte [ ]
     */
    public static byte[] doPost(String strUrl, byte[] reqData) {
        return send(strUrl, POST, reqData);
    }

    /**
     * Do get byte [ ].
     *
     * @param strUrl the str url
     * @return the byte [ ]
     */
    public static byte[] doGet(String strUrl) {
        return send(strUrl, GET, null);
    }

    /**
     * Send byte [ ].
     *
     * @param strUrl    the str url
     * @param reqmethod the reqmethod
     * @param reqData   the req data
     * @return the byte [ ]
     */
    public static byte[] send(String strUrl, String reqmethod, byte[] reqData) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setDoInput(true);
            httpcon.setUseCaches(false);
            httpcon.setInstanceFollowRedirects(true);
            httpcon.setConnectTimeout(CONN_TIMEOUT);
            httpcon.setReadTimeout(READ_TIMEOUT);
            httpcon.setRequestMethod(reqmethod);
            httpcon.connect();
            if (reqmethod.equalsIgnoreCase(POST)) {
                OutputStream os = httpcon.getOutputStream();
                os.write(reqData);
                os.flush();
                os.close();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "utf-8"));
            String inputLine;
            StringBuilder bankXmlBuffer = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                bankXmlBuffer.append(inputLine);
            }
            in.close();
            httpcon.disconnect();
            return bankXmlBuffer.toString().getBytes();
        } catch (Exception ex) {
            log.error(ex.toString(), ex);
            return null;
        }
    }

    /**
     * Read input stream byte [ ].
     *
     * @param inStream the in stream
     * @return the byte [ ]
     * @throws Exception the exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();// 网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }
}
