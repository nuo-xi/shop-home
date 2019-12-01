package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.po.TypeBean;
import com.fh.beans.vo.TypeBeanVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao extends BaseMapper<TypeBean> {
    List<TypeBeanVo> queryTypeList();
}
