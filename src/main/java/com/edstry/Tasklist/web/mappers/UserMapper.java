package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.web.dto.user.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

// Создастся компомент маппера из его реализации
// И мы сможем инжектить его в любом месте через @Autowired
@Mapper(componentModel = "spring")
public interface UserMapper {
    //@BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UserDTO toDto(User user);
    //@BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User toEntity(UserDTO userDTO);
}
