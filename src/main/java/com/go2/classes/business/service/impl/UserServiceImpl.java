package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.User;
import com.go2.classes.models.jpa.UserEntity;
import com.go2.classes.business.service.UserService;
import com.go2.classes.business.service.mapping.UserServiceMapper;
import com.go2.classes.data.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserJpaRepository userJpaRepository;

    @Resource
    private UserServiceMapper userServiceMapper;

    @Override
    public User findById(Long id) {
	UserEntity userEntity = userJpaRepository.findOne(id);
	return userServiceMapper.mapUserEntityToUser(userEntity);
    }

    @Override
    public List<User> findAll() {
	Iterable<UserEntity> entities = userJpaRepository.findAll();
	List<User> beans = new ArrayList<User>();
	for (UserEntity userEntity : entities) {
	    beans.add(userServiceMapper.mapUserEntityToUser(userEntity));
	}
	return beans;
    }

    @Override
    public User save(User user) {
	return update(user);
    }

    @Override
    public User create(User user) {
	UserEntity userEntity = null;
	if (!Objects.isNull(user.getEmail())) {
	    userEntity = userJpaRepository.findByEmail(user.getEmail());
	}
	if (!Objects.isNull(userEntity)) {
	    throw new IllegalStateException("user already exists");
	}
	userEntity = new UserEntity();
	userServiceMapper.mapUserToUserEntity(user, userEntity);
	UserEntity userEntitySaved = userJpaRepository.save(userEntity);
	return userServiceMapper.mapUserEntityToUser(userEntitySaved);
    }

    @Override
    public User update(User user) {
	UserEntity userEntity = userJpaRepository.findOne(user.getId());
	userServiceMapper.mapUserToUserEntity(user, userEntity);
	UserEntity userEntitySaved = userJpaRepository.save(userEntity);
	return userServiceMapper.mapUserEntityToUser(userEntitySaved);
    }

    @Override
    public void delete(Long id) {
	userJpaRepository.delete(id);
    }

    public UserJpaRepository getUserJpaRepository() {
	return userJpaRepository;
    }

    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
	this.userJpaRepository = userJpaRepository;
    }

    public UserServiceMapper getUserServiceMapper() {
	return userServiceMapper;
    }

    public void setUserServiceMapper(UserServiceMapper userServiceMapper) {
	this.userServiceMapper = userServiceMapper;
    }

    @Override
    public User findByEmail(String email) {
	UserEntity userEntity = userJpaRepository.findByEmail(email);
	if (Objects.isNull(userEntity)) {
	    return null;
	}
	return userServiceMapper.mapUserEntityToUser(userEntity);
    }

}
