package com.Novel.VirtualBookStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.entity.User; // Assuming this is your User entity
import com.Novel.VirtualBookStore.RepositoryInterface.UserRepository; // Assuming this is your UserRepository
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a User
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    // Get all Users
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a User by ID
    
    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update a User
    public User updateUser(UUID id, User newUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User oldUser = userOptional.get();
            oldUser.setUsername(newUser.getUsername());
            oldUser.setEmail(newUser.getEmail()); 
            oldUser.setPassword(newUser.getPassword()); 
            return userRepository.save(oldUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Delete a User
    public String deleteUser(UUID id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    
    //Get user By Email
	public User getUserByEmail(String email) {
	 Optional<User> user=userRepository.findByEmail(email);
	 return user.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	}
    //change password
	public User changePassword(String email, String newpassword) {
		User user= userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email));
		user.setPassword(newpassword);
		return userRepository.save(user);
	}
    //pagination
	public Page<User> getAllUsersPaginated(int page, int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	public long getCountofTotalUsers() {
		return userRepository.count();
	}
}