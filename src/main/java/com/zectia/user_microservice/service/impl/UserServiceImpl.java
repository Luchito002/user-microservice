package com.zectia.user_microservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zectia.user_microservice.dto.UserDto;
import com.zectia.user_microservice.exception.ResourceNotFoundException;
import com.zectia.user_microservice.model.User;
import com.zectia.user_microservice.repository.UserRepository;
import com.zectia.user_microservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto findById(Long id) {
    User user = this.userRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));

    return new UserDto(user);
  }

  @Override
  public UserDto insertUser(UserDto userDto) {
    User user = this.dtoToEntity(userDto);
    User insertedUser = this.userRepository.save(user);
    return new UserDto(insertedUser);
  }

  @Override
  public UserDto updateUser(UserDto userDto) {

  // Verificar si el usuario existe antes de actualizar
    User existingUser = userRepository.findById(userDto.getId())
      .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()));

    // Actualizar los campos del usuario existente con los datos del DTO
    existingUser.setNombres(userDto.getNombres());
    existingUser.setApellidos(userDto.getApellidos());
    existingUser.setCorreo(userDto.getCorreo());
    existingUser.setFechaNacimiento(userDto.getFechaNacimiento());
    existingUser.setUrlImagenPerfil(userDto.getUrlImagenPerfil());
    existingUser.setNumeroCelular(userDto.getNumeroCelular());
    existingUser.setRegionNumCelular(userDto.getRegionNumCelular());
    existingUser.setEstado(userDto.getEstado());

    User updatedUser = userRepository.save(existingUser);

    return new UserDto(updatedUser);
  }

  private User dtoToEntity(UserDto userDto) {
    User user = new User();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }
}
