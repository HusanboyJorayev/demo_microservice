package demo.userregistry.user;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {


    public abstract UserDto toDto(User user);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user,UserDto dto);
}
