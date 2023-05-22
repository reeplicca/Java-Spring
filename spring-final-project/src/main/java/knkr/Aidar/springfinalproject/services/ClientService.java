package knkr.Aidar.springfinalproject.services;

import knkr.Aidar.springfinalproject.entities.Client;
import knkr.Aidar.springfinalproject.entities.Permission;
import knkr.Aidar.springfinalproject.repositories.ClientRepository;
import knkr.Aidar.springfinalproject.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);

        if (client == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return client;
    }

    public String registerUser(String fullName, String email, String password, String rePassword) {
        String flag = "userExist";

        Client checkClient = clientRepository.findByEmail(email);

        if (checkClient == null) {
            flag = "passwordNotMatch";
            if (password.equals(rePassword)) {
                List<Permission> permissionList = new ArrayList<>();
                Permission permission = permissionRepository.findByRole("ROLE_USER");
                permissionList.add(permission);

                Client user = Client.builder()
                        .fullName(fullName)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .permissions(permissionList)
                        .build();

                if (clientRepository.save(user) != null) {
                    flag = "registerSuccess";
                }

            }
        }

        return flag;
    }
}
