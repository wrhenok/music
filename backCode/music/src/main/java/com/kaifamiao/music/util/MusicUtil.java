package com.kaifamiao.music.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MusicUtil {

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            System.out.println(file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            System.out.println(e.getMessage() + e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage() + e);
                }
            }
        }
    }
}
