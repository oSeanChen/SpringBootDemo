package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisTestService {

	@Autowired
	private MybatisTestMapper mybatisTestMapper;

	public List<Map<String, Object>> getDataList() throws Exception {
		return mybatisTestMapper.getDataList();
	}
	
	public List<MybatisTest> queryAll(String name, String phone, String email) {
		return mybatisTestMapper.findAll(name, phone, email);
	}

	public MybatisTest queryById(int id) {
		return mybatisTestMapper.findById(id);
	}

	public int insert(MybatisTest mybatisTest) {
			return mybatisTestMapper.insert(mybatisTest);
	}
	
	public int update(MybatisTest mybatisTest) {
		return mybatisTestMapper.update(mybatisTest);
	}
	
	public int delete(int id) {
		return mybatisTestMapper.deleteById(id);
	}

}
