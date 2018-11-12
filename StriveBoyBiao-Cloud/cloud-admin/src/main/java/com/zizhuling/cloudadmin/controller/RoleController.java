package com.zizhuling.cloudadmin.controller;

import com.zizhuling.cloudadmin.domain.RoleDO;
import com.zizhuling.cloudadmin.service.RoleService;
import com.zizhuling.cloudcommon.utils.PageUtils;
import com.zizhuling.cloudcommon.utils.Query;
import com.zizhuling.cloudcommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo
 * 角色
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RoleDO> roleDOS = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roleDOS, total);
        return pageUtil;
    }

    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping
    R save(@RequestBody RoleDO roleDO){
        if(roleService.save(roleDO)>0){
            return R.ok();
        }
        return R.error();
    }

    @PutMapping
    R update(@RequestBody RoleDO roleDO){
        if(roleService.update(roleDO)>0){
            return R.ok();
        }
        return R.error();
    }

}
