package fer.ftb.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fer.ftb.entities.User;
import fer.ftb.services.UserService;
import fer.ftb.util.CustomErrorType;

/**
 *  
 * @author Fernando Torres Bautista
 *
 */

@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	/* 
	 * Request method ( new account by a guest )
	 * 
	 */
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("user with username " + newUser.getUsername() + "already exist "), 
				HttpStatus.CONFLICT
			);
		}
		newUser.setRole("USER");
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}

	/* 
	 * LogIn end point 
	 */
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}
	
}
