package com.spring_boot.projectEx.dao;

import java.util.HashMap;

public interface IMemberDAO {
	String loginCheck(HashMap<String, Object> map);
}
