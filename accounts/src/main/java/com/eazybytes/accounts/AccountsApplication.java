package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.eazybytes.accounts")
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(info = @Info(
		title = "Accounts Microservice Documentation",
		description = "Bank Accounts Microservice Documentation",
		version = "v1",
		contact = @Contact(
				name = "Rudro Choudhury"
		),
		license =@License(
			    name = "Apache 2.0",
				url = "http://www.apache.org/licenses/LICENSE-2.0"
		)
))
@ExternalDocumentation(
		description = "Bank accounts service RestAPI documentation"
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
