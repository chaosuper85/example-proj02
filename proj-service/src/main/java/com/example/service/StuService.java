package com.example.service;

import com.example.pojo.Stu;
import java.util.List;

/**
 * @author zhuchao
 * @date 2022/1/30 12:30 上午
 */
public interface StuService {

    public List<Stu> getStuInfo();
    public void saveStu(Stu stu);
    public void update(Stu stu);
    public void delete(int id);
}
