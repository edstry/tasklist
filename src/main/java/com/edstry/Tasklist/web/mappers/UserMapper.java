package com.edstry.Tasklist.web.mappers;

import com.edstry.Tasklist.domain.user.User;
import com.edstry.Tasklist.web.dto.user.UserDTO;
import org.mapstruct.Mapper;

// Создастся компомент маппера из его реализации
// И мы сможем инжектить его в любом месте через @Autowired
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
