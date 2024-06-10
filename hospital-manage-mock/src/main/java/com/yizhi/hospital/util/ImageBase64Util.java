package com.yizhi.hospital.util;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * The type Image base 64 util.
 */
public class ImageBase64Util {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String imageFile = "D:\\yygh_work\\xh.png";// 待处理的图片
        System.out.println(getImageString(imageFile));
    }

    /**
     * Gets image string.
     *
     * @param imageFile the image file
     * @return the image string
     */
    public static String getImageString(String imageFile) {
        InputStream is = null;
        try {
            byte[] data = null;
            is = new FileInputStream(new File(imageFile));
            data = new byte[is.available()];
            is.read(data);
            return new String(Base64.encodeBase64(data));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                    is = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
