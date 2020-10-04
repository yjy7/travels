package com.yujy.travels.controller;

import com.yujy.travels.entity.Province;
import com.yujy.travels.entity.Result;
import com.yujy.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yujy
 */
@RestController
@CrossOrigin
@RequestMapping("province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 修改省份信息
     *
     * @param province
     * @return
     */
    @PostMapping("updateProvinceInfo")
    public Result updateProvinceInfo(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.updateProvinceInfo(province);
            result.setMsg("修改省份信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 根据id查询省份信息
     *
     * @param id
     * @return
     */
    @GetMapping("findOne")
    public Province findOne(String id) {
        return provinceService.findOne(id);
    }

    /**
     * 删除省份信息
     *
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("已删除！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false).setMsg("删除省份信息失败！");
        }
        return result;
    }

    /**
     * 查询所有
     *
     * @param page 当前页
     * @param rows 每页显示多少记录
     * @return
     */
    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 5 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Province> provinceList = provinceService.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces", provinceList);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;

    }

    /**
     * 保存省份信息
     *
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("省份信息添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("省份信息添加失败").setStatus(false);
        }
        return result;
    }
}
