/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ncf.apollodemo.controller;

import com.ncf.apollodemo.pojo.entity.User;
import com.ncf.apollodemo.pojo.userdo.UserLoginDO;
import com.ncf.apollodemo.pojo.userdo.UserSignDO;
import com.ncf.apollodemo.pojo.vo.CreateUserVO;
import com.ncf.apollodemo.resp.ResponseResult;
import com.ncf.apollodemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public ResponseResult<String> userLogin(@RequestBody UserLoginDO userLoginDO) {
        logger.info("登陆");
        String token = userService.userLogin(userLoginDO);
        if (token.isEmpty()) {
            return ResponseResult.error(500,"登录失败，请检查账号密码是否正确");
        }
        return ResponseResult.success(token);
    }

    @PostMapping(value = "/user/signIn")
    public ResponseResult<CreateUserVO> singIn(@RequestBody UserSignDO userSignDO) {
        logger.info("注册");
        CreateUserVO user = userService.createUser(userSignDO);
        if (user == null) {
            return ResponseResult.error(500,"数据库插入时异常");
        }
        return ResponseResult.success(user);
    }
}
