package com.store.service;

import com.store.domain.User;

public interface UserService {

	void regist(User user) throws Exception;

	User active(String code) throws Exception;

}
