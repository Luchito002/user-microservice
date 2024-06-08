package com.zectia.user_microservice.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
  public String insertUser(UserDto userDto) {
    User user = this.dtoToEntity(userDto);
    user.setClave(new BCryptPasswordEncoder().encode(userDto.getClave()));
    this.userRepository.save(user);
    return "Registro completado exitosamente";
  }

  @Override
  public String updateUser(UserDto userDto) {

  // Verificar si el usuario existe antes de actualizar
    User existingUser = userRepository.findById(userDto.getId())
      .orElseThrow(() -> new ResourceNotFoundException(userDto.getId()));

    existingUser.setNombres(userDto.getNombres());
    existingUser.setApellidos(userDto.getApellidos());
    existingUser.setCorreo(userDto.getCorreo());
    existingUser.setNombreUsuario(userDto.getNombreUsuario());
    existingUser.setClave(new BCryptPasswordEncoder().encode(userDto.getClave()));
    existingUser.setFechaNacimiento(userDto.getFechaNacimiento());
    existingUser.setUrlImagenPerfil(userDto.getUrlImagenPerfil());
    existingUser.setNumeroCelular(userDto.getNumeroCelular());
    existingUser.setRegionNumCelular(userDto.getRegionNumCelular());
    existingUser.setEstado(userDto.getEstado());

    userRepository.save(existingUser);

    return "Datos actualizados exitosamente";
  }

  private User dtoToEntity(UserDto userDto) {
    User user = new User();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }

  @Override
  public String deleteUser(Long id) {

    User existingUser = userRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));

    existingUser.setEstado(false);

    userRepository.save(existingUser);

    return "Usuario eliminado con exito";
  }

  @Override
  public String recoverUser(Long id) {

    User existingUser = userRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));

    existingUser.setEstado(true);

    userRepository.save(existingUser);

    return "Usuario recuperado con exito";
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersById(List<Long> userIds) {
    return userRepository.findAllById(userIds);
  }
}
