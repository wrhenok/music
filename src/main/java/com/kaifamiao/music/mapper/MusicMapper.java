package com.kaifamiao.music.mapper;

import com.kaifamiao.music.domain.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Music record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Music record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Music selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Music record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Music record);

    List<Music> selectMusicAll(@Param("userId") String userId);

    List<Music> selectByIsHeart(@Param("userId") String userId);

    List<Music> selectArtist(@Param("userId") String userId);



}