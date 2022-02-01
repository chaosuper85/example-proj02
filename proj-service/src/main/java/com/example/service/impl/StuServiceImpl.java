package com.example.service.impl;

import com.example.mapper.StuMapper;
import com.example.pojo.Stu;
import com.example.service.StuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author zhuchao
 * @date 2022/1/30 12:32 上午
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Stu> getStuInfo() {
        return stuMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu(Stu stu) {
        int ret = stuMapper.insertSelective(stu);
        return ;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(Stu stu) {
        stuMapper.updateByPrimaryKey(stu);
        return ;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(int id) {
        stuMapper.deleteByPrimaryKey(id);
        return ;
    }
}
