package com.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * @Author:
 * @Description:
 * @Data:Created in 11:12 2018/9/4
 */

//会自动被spring自动封装成叫userRepository
//CRUD  代表 create read update delete

public interface UserRepository extends CrudRepository<User,Integer> {

}
