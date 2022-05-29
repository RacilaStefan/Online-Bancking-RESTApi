package MainPackage;

import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.Utils.Implementations.RegisterService;
import MainPackage.Services.Utils.Implementations.CurrentTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@SpringBootApplication
public class PROKingApplication implements CommandLineRunner {

	private final RegisterService registerService;
	private final ObjectMapper mapper;
	private static final Logger logger = LoggerFactory
			.getLogger(PROKingApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(PROKingApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		logger.info("EXECUTING CommandLineRunner instructions...");

		CurrentTime.init();

		try {
			UserDto user = mapper.readValue(new File("./mock-admin-user.json"), UserDto.class);
			registerService.saveUser(user);
			String info = "Mock admin user created with following credentials: \n" +
					"Username: " + user.getUsername() + "\n" +
					"Password: " + user.getPassword();
			logger.info("Mock admin user created");
			System.out.println(info);
		} catch (IOException | CustomInvalidInputException ex) {
			logger.warn("Mock admin user was not created!");
			System.out.println(ex.getMessage());
		}
	}
}
