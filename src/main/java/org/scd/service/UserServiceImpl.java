package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.User;
import org.scd.model.dto.UserLoginDTO;
import org.scd.model.dto.UserRegisterDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.model.security.Role;
import org.scd.repository.RoleRepository;
import org.scd.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) throws BusinessException {

        if (Objects.isNull(userLoginDTO)) {
            throw new BusinessException(401, "Body null !");
        }

        if (Objects.isNull(userLoginDTO.getEmail())) {
            throw new BusinessException(400, "Email cannot be null ! ");
        }

        if (Objects.isNull(userLoginDTO.getPassword())) {
            throw new BusinessException(400, "Password cannot be null !");
        }

        final User user = userRepository.findByEmail(userLoginDTO.getEmail());

        if (Objects.isNull(user)) {
            throw new BusinessException(401, "Bad credentials !");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(401, "Bad credentials !");
        }


        return user;
    }
    @Override
    public User register(UserRegisterDTO userRegisterDTO)throws BusinessException{
        if(userRegisterDTO.getEmail()== null)
            throw new BusinessException(400,"Don't forget email!!");
        if(userRegisterDTO.getFirst_name()==null)
            throw  new BusinessException(400,"Don't forget first name!!");
        if(userRegisterDTO.getLast_name()==null)
            throw new BusinessException(400,"Don't forget last name!!");
        if(userRegisterDTO.getPassword()==null)
            throw new BusinessException(400,"Don't forget pass!!");
        User test=null;
        if(userRepository.findByEmail(userRegisterDTO.getEmail())!= test)
            throw new BusinessException(409,"Email already used!!");
        final User user=new User();
        String pass=passwordEncoder.encode(userRegisterDTO.getPassword());
        user.setEmail(userRegisterDTO.getEmail());
        user.setFirstName(userRegisterDTO.getFirst_name());
        user.setLastName(userRegisterDTO.getLast_name());
        user.setPassword(pass);
    Role role=new Role();
    role.setId(2L); // seteaza obiectul role id-ul 2; L=long
    role.setRole("BASIC_USER");
    Set<Role> roles=new HashSet<>();
    roles.add(role);
    user.setRoles(roles);

        final User createdUser= userRepository.save(user);

        return createdUser;
    }
}
