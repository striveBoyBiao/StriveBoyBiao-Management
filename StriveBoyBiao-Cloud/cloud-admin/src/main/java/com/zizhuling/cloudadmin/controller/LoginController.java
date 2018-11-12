package com.zizhuling.cloudadmin.controller;

import com.zizhuling.cloudadmin.domain.UserDO;
import com.zizhuling.cloudadmin.service.MenuService;
import com.zizhuling.cloudadmin.service.TokenService;
import com.zizhuling.cloudadmin.service.UserService;
import com.zizhuling.cloudadmin.utils.MD5Utils;
import com.zizhuling.cloudcommon.annotation.Log;
import com.zizhuling.cloudcommon.context.FilterContextHandler;
import com.zizhuling.cloudcommon.dto.LoginDTO;
import com.zizhuling.cloudcommon.dto.UserToken;
import com.zizhuling.cloudcommon.utils.JwtUtils;
import com.zizhuling.cloudcommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;

    @Log("登录")
    @PostMapping("/login")
    R login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPwd().trim();
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        List<UserDO> userDOs = userService.list(param);
        if(userDOs.size()<1){
            return R.error("用户或密码错误");
        }
        UserDO userDO = userDOs.get(0);
        if (null == userDO || !userDO.getPassword().equals(password)) {
            return R.error("用户或密码错误");
        }
        UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString(), userDO.getName());
        String token="";
        try {
            token = JwtUtils.generateToken(userToken, 2*60*60*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //首先清除用户缓存权限
        menuService.clearCache(userDO.getUserId());
        // String token = tokenService.createToken(userDO.getUserId());
        return R.ok("登录成功")
                .put("token", token).put("user",userDO)
                .put("perms",menuService.PermsByUserId(userDO.getUserId()))
                .put("router",menuService.RouterDTOsByUserId(userDO.getUserId()));
    }


    @RequestMapping("/logout")
    R logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        return R.ok();
    }


}
