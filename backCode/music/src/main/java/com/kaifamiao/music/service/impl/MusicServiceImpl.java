package com.kaifamiao.music.service.impl;

import com.kaifamiao.music.config.MusicConfig;
import com.kaifamiao.music.util.ResponseUtil;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.kaifamiao.music.mapper.MusicMapper;
import com.kaifamiao.music.domain.Music;
import com.kaifamiao.music.service.MusicService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public ResponseUtil uploadMusic(MultipartFile multipartFile, Integer userid, String artist) {

        String filename = multipartFile.getOriginalFilename();
        String oldName = filename.substring(0, filename.lastIndexOf("."));
        String musicSuffix = filename.contains(".") ? filename.substring(filename.lastIndexOf(".")) : null;
        long size = multipartFile.getSize();
        String newName = String.valueOf(System.currentTimeMillis());
        File newFile = new File(MusicConfig.getUploadfile() , newName + musicSuffix);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }

        String duration = null;
        try {
            //  获取时长
            multipartFile.transferTo(newFile);  //存储
            MP3File file = new MP3File(newFile.getPath());
            MP3AudioHeader audioHeader = (MP3AudioHeader) file.getAudioHeader();
            duration = audioHeader.getTrackLengthAsString();
            System.out.println(duration);

        } catch (IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        //  存储
        Music music = new Music();
        music.setOldName(oldName);
        music.setUploadPath(newFile.getPath());
        music.setNewName(newName);
        music.setMusicSuffix(musicSuffix);
        music.setMusicSize(size);
        music.setDuration(duration);
        music.setArtist(artist);
        music.setUserId(userid);
        int a = musicMapper.insertSelective(music);
        return new ResponseUtil(a, "操作成功", music);
    }

    @Override
    public InputStream getMusicInputStream(Music music) {
        File file = new File(music.getUploadPath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Music> selectAll(String userId) {
        return musicMapper.selectMusicAll(userId);
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return musicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Music record) {
        return musicMapper.insert(record);
    }

    @Override
    public int insertSelective(Music record) {
        return musicMapper.insertSelective(record);
    }

    @Override
    public Music selectByPrimaryKey(Integer id) {
        return musicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Music record) {
        return musicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Music record) {
        System.out.println(record);
        return musicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Music> selectByIsHeart(String userId) {
        return musicMapper.selectByIsHeart(userId);
    }

    @Override
    public Map<String, Integer> selectArtist(String userId){
        Map<String, Integer> map = new HashMap<>();
        List<Music> list = musicMapper.selectArtist(userId);
        for (Music item : list) {
            int count = map.containsKey(item.getArtist()) ? map.get(item.getArtist()) : 0;
            map.put(item.getArtist(), count + 1);
        }
    return map;
    }

}







