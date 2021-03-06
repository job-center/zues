package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AddressMapper extends MyBatisBaseMapper<Address> {
    int deleteByPrimaryKey(String id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int count(@Param("param")Parameter parameter);

    public List<HashMap<String,Object>> list(@Param("param")Parameter parameter);

    public Address findAddressById(@Param("address_id")Integer address_id);

    public List<Address> findAddressByLevel(@Param("level")String level);

    public List<Address> findAddressByParentId(@Param("parent_id")int parent_id);
}