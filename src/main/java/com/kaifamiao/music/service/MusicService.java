package com.kaifamiao.music.service;

import com.kaifamiao.music.domain.Music;
import com.kaifamiao.music.util.ResponseUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface MusicService {

    //    文件上传接口
    ResponseUtil uploadMusic(MultipartFile multipartFile, Integer userid,String artist);

    //根据id获取数据流
    InputStream getMusicInputStream(Music music);

    //查找所有的音乐
    List<Music> selectAll(String userId);

    int deleteByPrimaryKey(Integer id);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    List<Music> selectByIsHeart(String userId);

    Map<String, Integer> selectArtist(String userId);
}







