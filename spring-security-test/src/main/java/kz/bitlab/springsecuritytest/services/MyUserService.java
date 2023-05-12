package kz.bitlab.springsecuritytest.services;

import kz.bitlab.springsecuritytest.entities.Permission;
import kz.bitlab.springsecuritytest.entities.Users;
import kz.bitlab.springsecuritytest.repositories.PermissionRepository;
import kz.bitlab.springsecuritytest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public String registerUser(String fullname, String email, String password, String rePassword) {
        String flag = "userExist";

        Users checkUser = usersRepository.findByEmail(email);

        if (checkUser == null) {
            flag = "passwordNotMatch";
            if (password.equals(rePassword)) {
                List<Permission> permissionList = new ArrayList<>();
                Permission permission = permissionRepository.findByRole("ROLE_USER");
                permissionList.add(permission);

                Users user = Users.builder()
                        .fullName(fullname)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .permissions(permissionList)
                        .build();

                if (usersRepository.save(user) != null) {
                    flag = "registerSuccess";
                }

            }
        }

        return flag;
    }
}
