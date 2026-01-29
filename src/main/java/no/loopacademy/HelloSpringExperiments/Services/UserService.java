package no.loopacademy.HelloSpringExperiments.Services;

import no.loopacademy.HelloSpringExperiments.Models.User;
import no.loopacademy.HelloSpringExperiments.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {


        return userRepository.save(user);
    }


    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }


    public Optional<User> getByJwtSub(String jwtSub) {
        return userRepository.findByJwtSub(jwtSub);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(Integer id, User updatedUser) {

        return userRepository.findById(id)
                .map(existing -> {
                    existing.setOrnoCertificateCode(updatedUser.getOrnoCertificateCode());
                    existing.setYearsExperience(updatedUser.getYearsExperience());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
