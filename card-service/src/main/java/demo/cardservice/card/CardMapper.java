package demo.cardservice.card;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CardMapper {


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Card toEntity(CardDto dto);

    public abstract CardDto toDto(Card card);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Card card, CardDto dto);
}
