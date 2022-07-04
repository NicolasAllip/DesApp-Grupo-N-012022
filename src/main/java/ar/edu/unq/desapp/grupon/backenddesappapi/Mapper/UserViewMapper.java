package ar.edu.unq.desapp.grupon.backenddesappapi.Mapper;

import ar.edu.unq.desapp.grupon.backenddesappapi.Model.User;
import ar.edu.unq.desapp.grupon.backenddesappapi.service.dto.NewUserDTO;
import ar.edu.unq.desapp.grupon.backenddesappapi.persistence.IUserDao;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

  @Autowired
  private IUserDao userRepo;

  public abstract NewUserDTO toUserView(User user);

  public abstract List<NewUserDTO> toUserView(List<User> users);

  public NewUserDTO toUserViewById(Long id) {
    if (id == null) {
      return null;
    }
    return toUserView(userRepo.findById(id).orElse(null));
  }

}