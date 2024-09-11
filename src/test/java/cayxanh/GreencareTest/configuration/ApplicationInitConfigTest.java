package cayxanh.GreencareTest.configuration;

import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.enums.Role;
import cayxanh.GreencareTest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApplicationInitConfigTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ApplicationInitConfig applicationInitConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplicationRunnerCreatesAdminUser() throws Exception {
        // Arrange
        when(userRepo.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("admin")).thenReturn("encodedPassword");

        ApplicationRunner runner = applicationInitConfig.applicationRunner(userRepo);

        // Act
        runner.run(mock(ApplicationArguments.class));

        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("admin", savedUser.getUsername());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(new HashSet<>(Set.of(Role.ADMIN.name())), savedUser.getRoles());
    }
}
