package com.zizhuling.cloudzuul.controller;

import com.zizhuling.cloudcommon.constants.CommonConstants;
import com.zizhuling.cloudcommon.context.FilterContextHandler;
import com.zizhuling.cloudcommon.dto.MenuDTO;
import com.zizhuling.cloudzuul.prc.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version V1.0
 */
@RestController
public class LoginController {
    @Autowired
    MenuService menuService;
    @GetMapping({"/test"})
    List<MenuDTO> login(HttpServletRequest request)  {
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.userMenus();
    }
}
