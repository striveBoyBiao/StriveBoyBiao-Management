package com.zizhuling.cloudadmin.dto.do2dto;


import com.zizhuling.cloudadmin.domain.UserDO;
import com.zizhuling.cloudadmin.dto.UserDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    public UserDTO do2dto(UserDO person);

    public List<UserDTO> dos2dtos(List<UserDO> list);
}
