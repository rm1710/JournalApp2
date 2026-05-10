package com.journalapp;

import com.journalapp.repository.UserRepository;
import com.journalapp.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class JournalAppApplicationTests {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @MockBean
    private UserRepository userRepository;

	@Test
	void contextLoads() {
	}



}
