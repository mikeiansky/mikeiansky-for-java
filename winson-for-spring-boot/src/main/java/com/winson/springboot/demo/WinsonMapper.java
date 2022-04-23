package com.winson.springboot.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author winson
 * @date 2022/4/23
 **/
@Mapper
public interface WinsonMapper {

    @Update("update course set cname = 'winson-0001' where cid = 6")
    void update();

}
