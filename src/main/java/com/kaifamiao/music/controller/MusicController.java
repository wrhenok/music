package com.kaifamiao.music.controller;

import com.kaifamiao.music.domain.Music;
import com.kaifamiao.music.service.MusicService;
import com.kaifamiao.music.util.MusicUtil;
import com.kaifamiao.music.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MusicController {

    @Resource
    private MusicService musicService;

//    文件上传
    @PostMapping("/upload")
    public ResponseUtil upload1(@RequestParam("multipartFile") MultipartFile multipartFile, Integer userId, String artist) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return new ResponseUtil(0, "文件不能为空", null);
        }
        return musicService.uploadMusic(multipartFile, userId, artist);
    }

//  查询所有歌曲
    @GetMapping ("/all/{userId}")
    public List<Music> musicAll(@PathVariable("userId") String userId){
        return musicService.selectAll(userId);
    }
    
//  根据id查询歌曲
    @GetMapping("/select/{id}")
    public Music selectOne(@PathVariable("id") Integer id){
        return musicService.selectByPrimaryKey(id);
    }

//  查询红心歌曲
    @GetMapping("/heart/{userId}")
    public List<Music> selectHeart(@PathVariable("userId") String userId){
        return musicService.selectByIsHeart(userId);
    }

//  更新红心
    @PostMapping("/updHeart")
    public Integer updateHeart(@RequestBody Music music){
        return musicService.updateByPrimaryKeySelective(music);
    }

//    歌曲数量
    @GetMapping("/art/{userId}")
    public Map<String, Integer> selectCountByArtist(@PathVariable("userId") String userId){
        return musicService.selectArtist(userId);
    }

    //  下载歌曲
    @GetMapping("/download/{id}")
    public void downloadMusic(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        //1.获取文件绝对路径
        Music music = musicService.selectByPrimaryKey(id);
        String path = music.getUploadPath();
        //2.通过绝对路径定义File
        File file = new File(path);
        //3.调用FileUtil下载文件
        MusicUtil.downloadFile(request,response,file);
    }

//    删除歌曲
    @GetMapping("/delete/{id}")
    public Integer deleteMusic(@PathVariable("id") Integer id) {
        return musicService.deleteByPrimaryKey(id);
    }

//  下载音乐2
    @GetMapping("/down/{id}")
    public void downMusic(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response){
        Music music = musicService.selectByPrimaryKey(id);
        String fileName = music.getNewName() +music.getMusicSuffix();
        // 获取输出流
        OutputStream outputStream=null;
        InputStream inputStream=null;
        BufferedInputStream bufferedInputStream=null;
        byte[] bytes=new byte[1024];
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/octet-stream");
            inputStream=musicService.getMusicInputStream(music);
            bufferedInputStream=new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i=bufferedInputStream.read(bytes);
            while (i!=-1){
                outputStream.write(bytes,0,i);
                i=bufferedInputStream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                if (bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
