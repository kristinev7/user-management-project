package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper //mapstruct will create code to implement these methods
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);//implementation class created by mapstruct
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}

/* if objects have different field names, @Mapping notation can be used to map the field names that differ.
    for example:
    in UserDto Class, email field is 'emailAddress' and
    in User class, email field is 'email'
    then in this mapstruct interface,
    @Mapping(source="email", target="emailAddress")
 */