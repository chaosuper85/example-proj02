package com.example.controller;

import com.example.common.PagedGridResult;
import com.example.pojo.Stu;
import com.example.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/1/30 12:36 上午
 */
@Api(value = "学生信息控制器", tags = {"学生信息控制器"})
@RestController
public class StuInfoController {

    @Autowired
    private StuService stuService;

    @ApiOperation(value = "获取学生信息value", notes = "获取学生信息note", httpMethod = "GET")
    @GetMapping("/getStuInfo")
    public Object getStuInfo(int id) {
        int page = 2;
        int pageSize = 2;
        PageHelper.startPage(page, pageSize);

        List<Stu> stu = stuService.getStuInfo();

        PagedGridResult result = setterPagedGrid(stu, 2);
        return result;
    }

    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    @ApiOperation(value = "保存学生信息value", notes = "保存学生信息note", httpMethod = "POST")
    @PostMapping("/saveStuInfo")
    public Stu saveStuInfo(@RequestBody Stu stu) {
        stuService.saveStu(stu);
        return stu;
    }

    @PostMapping("/updateStuInfo")
    public Stu updateStuInfo(@RequestBody Stu stu) {
        stuService.update(stu);
        return stu;
    }

    @PostMapping("/deleteStuInfo")
    public void deleteStuInfo(int id) {
        stuService.delete(id);
        return ;
    }

}