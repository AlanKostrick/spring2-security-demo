package org.wecancodeit.securitydemo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.securitydemo.models.User;
import org.wecancodeit.securitydemo.models.UserRepository;

@Component
public class UserPopulator implements CommandLineRunner {

	@Resource
	private UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {

		userRepo.save(new User("admin", "admin", "ADMIN"));
		userRepo.save(new User("user", "user", "USER"));

	}

}
