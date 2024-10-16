package ai.ready.ready;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ReadyApplication {

	private final BookRepository bookRepository;

	public ReadyApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadyApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(prefix = "ready", name = "db.init.enabled", havingValue = "true")
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Running.....");

			Book b1 = new Book("Book A",
					"Author A",
					540);

			Book b2 = new Book("Book B",
					"Author B",
					300);

			Book b3 = new Book("Book C",
					"Author C",
					129);

			bookRepository.saveAll(List.of(b1, b2, b3));
		};
	}

}
