package com.taik.webshop.service;

import com.taik.webshop.dao.UserRepository;
import com.taik.webshop.domain.User;
import com.taik.webshop.dto.UserDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;



    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }


    @Test
    void checkFindByName() {
        //have
        String name = "user";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

    }

    @Test
    void checkFindByNameExact() {
        //have
        String name = "user";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.eq(name))).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);
        User rndUser = userService.findByName(UUID.randomUUID().toString());

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

        Assertions.assertNull(rndUser);

    }

    @Test
    void checkSaveIncorrectPassword(){
        //have
        UserDto userDto = UserDto.builder()
                .password("password")
                .matchingPassword("another")
                .build();

        //execute
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDto);
            }
        });

    }

    @Test
    void checkSave(){
        //have
        UserDto userDto = UserDto.builder()
                .username("name")
                .email("email")
                .password("password")
                .matchingPassword("password")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        //execute
        boolean result = userService.save(userDto);

        //check
        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());

    }
}
