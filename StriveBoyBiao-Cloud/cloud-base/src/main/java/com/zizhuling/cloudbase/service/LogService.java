package com.zizhuling.cloudbase.service;


import com.zizhuling.cloudcommon.dto.LogDO;
import com.zizhuling.cloudcommon.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LogService {
    int save(LogDO logDO);

    List<LogDO> queryList(Query query);

    int count(Query query);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
