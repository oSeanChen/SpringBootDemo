package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MybatisTest;
import com.example.demo.MybatisTestService;

@RestController
public class MybatisController {
	@Autowired
	private MybatisTestService mybatisTestService;

	@GetMapping("/mybatisTest/dataList")
	public List<Map<String, Object>> getDataList() throws Exception {
		return mybatisTestService.getDataList();
	}

	@GetMapping("/mybatisTest")
	public ResponseEntity<HashMap<String, Object>> getAllMybatisTest(
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam(value = "email", required = false) String email) {
		try {
			List<MybatisTest> dataList = mybatisTestService.queryAll(name, phone, email);
			if (dataList != null && !dataList.isEmpty()) {
				HashMap<String, Object> responseMap = new HashMap<>();
				responseMap.put("status", HttpStatus.OK);
				responseMap.put("Data", dataList);
				return ResponseEntity.ok(responseMap);
			} else {
				HashMap<String, Object> responseMap = new HashMap<>();
				responseMap.put("status", HttpStatus.NO_CONTENT);
				responseMap.put("message", "No data found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMap);
			}
		} catch (Exception e) {
			HashMap<String, Object> responseMap = new HashMap<>();
			responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			responseMap.put("error", "An internal server error occurred");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
		}
	}

	@GetMapping("/mybatisTest/{id}")
	public ResponseEntity<MybatisTest> getMybatisTest(@PathVariable int id) {
		MybatisTest mybatisTest = mybatisTestService.queryById(id);
		if (mybatisTest != null) {
			return new ResponseEntity<>(mybatisTest, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/mybatisTest")
	public ResponseEntity<String> saveMybatisTest(@RequestBody MybatisTest mybatisTest) {
		int rowsAffected = mybatisTestService.insert(mybatisTest);
		if (rowsAffected > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("MybatisTest created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create MybatisTest");
		}
	}

	@PutMapping("/mybatisTest/{id}")
	public String updateMybatisTest(@PathVariable int id, @RequestBody MybatisTest mybatisTest) {
		mybatisTest.setId(id);
		int rowsAffected = mybatisTestService.update(mybatisTest);
		return rowsAffected > 0 ? "id = " + id + "更新成功" : "更新失敗";
	}

	@DeleteMapping("/mybatisTest/{id}")
	public String deleteMybatisTest(@PathVariable int id) {
		int rowsAffected = mybatisTestService.delete(id);
		return rowsAffected > 0 ? "id = " + id + "刪除成功" : "刪除失敗";
	}
}
