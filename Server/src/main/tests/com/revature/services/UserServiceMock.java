package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.junit.Before;
import org.junit.Test;

import com.revature.dtos.Credentials;
import com.revature.models.User;
import com.revature.repos.UserRepository;

public class UserServiceMock {
	private List<User> users;
	private UserService userService = new UserService();
	@Mock private UserRepository userRepo;
	
	/**
	 * Define an in-memory database that will be queried instead of the actual database.
	 */
	
	@Before
	public void setup() {
		
		// String username, String password, String firstname, String lastname, String email, Role role
		users = new ArrayList<>();
		User user1;
		user1.setId(1);
		user1.setUsername("ltorvalds");
		user1.setPassword("tuxThePengu!n");
		user1.setEmail("ltorvalds@kernel.org");
		
		User user1;
		user1.setId(1);
		user1.setUsername("ltorvalds");
		user1.setPassword("tuxThePengu!n");
		user1.setEmail("ltorvalds@kernel.org");
		
		users.add(new User(2, "ltorvalds", "tuxThePengu!n", "Linus", "Torvalds", "ltorvalds@kernel.org", new Role("FM")));
		users.add(new User(3, "theOnlyWaldo", "whereiswaldo", "Waldo", "Where", "whereswaldo@gmail.com", new Role("FM")));
		users.add(new User(4, "hpotter", "urawizard", "Harry", "Potter", "hpotterwizrd@aol.com", new Role("EMP")));
	}
	
	// Test getByUsername
	
	@Test
	public void getUserByUsernameTest() {
		String username = users.get(1).getUsername();
		User user = users.get(1);
	
		when(userRepo.getByUsername(username)).thenReturn(user);
		System.out.println(user);
		User testUser = userService.getUserByUsername(username);
		System.out.println(testUser);

		assertEquals(testUser, user);
	}
	
	// Test getAll
	
		@Test
		public void loginTest() {
			String username = users.get(1).getUsername();
			String password = users.get(1).getPassword();
			
			when(userRepo.getUserByCredentials(username, password)).thenReturn(users.get(1));
			
			User u1 = userService.getByCredentials(new Credentials(username, password));
			System.out.println(u1);
			assertEquals(users.get(1), u1);
		}
}
}
