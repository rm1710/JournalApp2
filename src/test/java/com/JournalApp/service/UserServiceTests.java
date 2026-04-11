package com.JournalApp.service;

import com.journalApp.entity.User;
import com.journalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

//    @BeforeAll
//    void setUp(){
//
//    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
//        User user = userRepository.findByUserName("ramesh");
//        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(!user.getJournalEntries().isEmpty());
        assertTrue(userService.TestsaveNewUser(user));
    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//        "1,1,2", "2,2,4", "2,1,3"
//    })
//    public void test(int a, int b, int expected){
//        assertEquals(expected, a+b);
//    }




}
