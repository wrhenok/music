package com.kaifamiao.music.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music")
public class MusicConfig {

        private static String uploadfile;

    public static String getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(String uploadfile) {
        MusicConfig.uploadfile = uploadfile;
    }
}
