package org.shabbydev.test.middlejavadevelopertest.data.mapper.decorator;


import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.shabbydev.test.middlejavadevelopertest.data.dtos.InterdepartmentalRequestDTO;
import org.shabbydev.test.middlejavadevelopertest.data.entity.InterdepartmentalRequestEntity;
import org.shabbydev.test.middlejavadevelopertest.data.entity.UserEntity;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.InterdepartmentalTypeMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.UserMapper;
import org.shabbydev.test.middlejavadevelopertest.data.mapper.UserRequestMapper;
import org.shabbydev.test.middlejavadevelopertest.data.repo.InterdepartmentalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Mapper(componentModel = "spring", uses = UserMapper.class)
@Primary
public abstract class InterdepartmentalRequestMapperDecorator {

    @Autowired
    private InterdepartmentalTypeMapper interdepartmentalTypeMapper;

    @Autowired
    private UserRequestMapper userRequestMapper;

    @AfterMapping
    public void toDTO(InterdepartmentalRequestEntity interdepartmentalRequestEntity, @MappingTarget InterdepartmentalRequestDTO interdepartmentalRequestDTO) {
        interdepartmentalRequestDTO.setUser(userRequestMapper.toDto(interdepartmentalRequestEntity.getAuthor()));
        interdepartmentalRequestDTO.setType(interdepartmentalTypeMapper.toDTO(interdepartmentalRequestEntity.getType()));
    }

    @AfterMapping
    public void toEntity(InterdepartmentalRequestDTO interdepartmentalRequestDTO, @MappingTarget InterdepartmentalRequestEntity interdepartmentalRequestEntity) {

        UserEntity user = new UserEntity();
        user.setId(interdepartmentalRequestDTO.getId());
        interdepartmentalRequestEntity.setAuthor(user);
        interdepartmentalRequestEntity.setType(interdepartmentalTypeMapper.toEntity(interdepartmentalRequestDTO.getType()));
    }
}
