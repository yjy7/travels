package com.yujy.travels.controller;

import com.yujy.travels.entity.Place;
import com.yujy.travels.entity.Result;
import com.yujy.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("place")
@CrossOrigin
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("findOne")
    public Place findOne(String placeId) {
        return placeService.findOne(placeId);
    }

    /**
     * 删除景点
     *
     * @param placeId
     * @return
     */
    @GetMapping("deletePlace")
    public Result deletePlace(String placeId) {
        Result result = new Result();
        try {
            placeService.deletePlace(placeId);
            result.setMsg("删除景点成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("删除景点失败！！！");
        }
        return result;
    }

    /**
     * 更新景点信息
     *
     * @param place
     * @return
     */
    @PostMapping("updatePlaceInfo")
    public Result updatePlaceInfo(MultipartFile pic, Place place) {
        Result result = new Result();
        try {
            //更新
            //对接收的文件做base64
            String picPath = Base64Utils.encodeToString(pic.getBytes());
            place.setPicPath(picPath);
            //文件上传
            String realPath = "C:/Users/yujy/IdeaProjects/travels/src/main/resources/static/file";

            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            pic.transferTo(new File(realPath, newFileName));

            //修改景点信息
            placeService.updatePlaceInfo(place);
            result.setMsg("更新景点信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("更新景点信息失败！");
        }
        return result;
    }

    /**
     * 添加景点信息
     *
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @PostMapping("savePlaceInfo")
    public Result savePlaceInfo(MultipartFile pic, Place place) throws IOException {
        Result result = new Result();
        try {
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //base64
            place.setPicPath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            String realPath = "C:/Users/yujy/IdeaProjects/travels/src/main/resources/static/file";

            pic.transferTo(new File(realPath, newFileName));
            placeService.savePlace(place);
            result.setMsg("添加景点信息成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("添加景点信息失败！！！");
        }
        return result;
    }


    /**
     * 根据省份id查询所有景点信息
     *
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @GetMapping("findAllPlace")
    public Map<String, Object> findAllPlace(Integer page, Integer rows, String provinceId) {

        page = page == null ? 1 : page;
        rows = rows == null ? 3 : rows;
        HashMap<String, Object> result = new HashMap<>();
        //当前页景点集合
        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);
        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        //总页数
        Integer totalPage = counts % rows == 0 ? counts / rows : counts / rows + 1;

        result.put("places", places);
        result.put("page", page);
        result.put("counts", counts);
        result.put("totalPage", totalPage);
        return result;

    }
}
