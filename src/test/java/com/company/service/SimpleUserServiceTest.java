package com.company.service;

import com.company.domain.User;
import com.company.dto.UserDTO;
import com.company.dto.UserDTOFactory;
import com.company.exceptions.MyAppException;
import com.company.repository.DefaultUserRepository;
import com.company.repository.UserRepository;
import com.company.service.SimpleUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleUserServiceTest {
    @MockBean
    private UserRepository userRepository;

    private SimpleUserService userService;

//    @Before
//    public void init() {
//        userRepository = mock(DefaultUserRepository.class);
//        userService = new SimpleUserService(userRepository);
//    }

    @Test
    public void test_get_should_return_users() {
        int id1 = 1;
        int id2 = 2;
        String name1 = "John";
        String name2 = "Leo";
        when(userRepository.GetAll()).thenReturn(Arrays.asList(
                new User(id1, name1),
                new User(id2, name2)
        ));
        List<UserDTO> actualUserDTOs = userService.Get();
        assertThat(actualUserDTOs).usingRecursiveFieldByFieldElementComparator().containsExactly(
                UserDTOFactory.create(id1, name1),
                UserDTOFactory.create(id2, name2)
        );
    }

    @Test
    public void test_get_when_repo_throws_exception_should_throw_wrapped_exception() throws MyAppException {
        when(userRepository.GetAll()).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> userService.Get())
                .isInstanceOf(MyAppException.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }
}
