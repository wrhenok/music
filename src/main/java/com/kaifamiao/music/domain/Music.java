package com.kaifamiao.music.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Music implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 音乐名
     */
    private String oldName;

    /**
     * 音乐类型
     */
    private String musicType;

    /**
     * 上传路径
     */
    private String uploadPath;

    /**
     * 音乐保存名
     */
    private String newName;

    /**
     * 音乐后缀
     */
    private String musicSuffix;

    /**
     * 音乐大小
     */
    private Long musicSize;

    /**
     * 音乐时长
     */
    private String duration;

    /**
     * 是否收藏（0：收藏，1：未收藏）
     */
    private Integer isHeart;

    /**
     * 作者
     */
    private String artist;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 下载次数
     */
    private Integer downloadTimes;

    /**
     * 上传id
     */
    private Integer userId;

    private User user;

    private static final long serialVersionUID = 1L;

}