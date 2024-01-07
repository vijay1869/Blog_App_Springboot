package com.blog.BlogAppApis;

import com.blog.BlogAppApis.respositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	UserRepo userRepo;
	@Test
	void contextLoads() {
	}
	@Test
	public void repoClassTest(){
		System.out.println(this.userRepo.getClass().getName());
		//jdk.proxy2.$Proxy108-created by spring on run time
		//these implementation classes are called proxy classes

		System.out.println(this.userRepo.getClass().getPackageName());
		//jdk.proxy2
	}

}
