package com.example.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MybatisTestMapper {
// 如果配置 .xml 檔案就不用使用註解

	List<Map<String, Object>> getDataList() throws Exception;
	
	List<MybatisTest> findAll(String name, String phone, String email);
	
//	@Select("select * from mybatis_test where id = #{id}")
	MybatisTest findById(int id);
	
//	@Insert("insert into mybatis_test(name, phone, email) values(#{name}, #{phone}, #{email})")
	int insert(MybatisTest mybatisTest);
	
//	@Update("update mybatis_test "
//			+ "set name = #{member.name}, phone = #{member.phone}, email = #{member.email} "
//			+ "where id = #{member.id}")
	int update(@Param("member") MybatisTest mybatisTest);
	
//	@Delete("delete from mybatis_test "
//			+ "where id = #{id}")
	int deleteById(int id);

}
