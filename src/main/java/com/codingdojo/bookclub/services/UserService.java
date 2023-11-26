package com.codingdojo.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.repositories.UserRepo;
import com.codingdojo.bookclub.validators.LoginValidator;

@Service
public class UserService {
	@Autowired
	private UserRepo uRepo;
	
	public User findById(Long id) {
		return uRepo.findById(id).orElse(null);
	}
    // This method will be called from the controller
    // whenever a user submits a registration form.
    public User register(User newUser, BindingResult result) {
    
    	// TO-DO - Reject values or register if no errors:
    	Optional<User> potentialUser = uRepo.findByEmail(newUser.getEmail());
        
        // Reject if email is taken (present in database)
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email is already registered. Please either choose another email or log in!");
    	}
        // Reject if password doesn't match confirmation
    		// == should not be used because it will check for memory location
    		// rather than checking if the Strings are equal/match
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "Matches", "Passwords must match!");
        }
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        // Hash and set password, save user to database
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
    	return this.uRepo.save(newUser);
    }
    // This method will be called from the controller
    // whenever a user submits a login form.
        public User login(LoginValidator newLoginObject, BindingResult result) {
        // TO-DO - Reject values:
        
    	// Find user in the DB by email
        // Reject if NOT present
        	Optional<User> potentialUser = uRepo.findByEmail(newLoginObject.getEmail());
        	if(potentialUser.isPresent()) {
        		User user = potentialUser.get();
        		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
        		    result.rejectValue("email", "Matches", "Invalid Credentials!");
        		}
        		return user;  		
        	}else {
        		// Reject if BCrypt password match fails
        		if(!newLoginObject.getEmail().equals("")) {
        			result.rejectValue("email", "Matches", "Invalid Credentials!");
        		}
        		
        	}
        // Return null if result has errors
        // Otherwise, return the user object
        	return null;
        }
}
