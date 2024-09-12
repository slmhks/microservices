package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Schema(
        name = "AccountsContactInfo",
        description = "Schema to hold Accounts Contact information (contact details and on call support numbers)",
        externalDocs = @ExternalDocumentation(
                description = "Use this link to get further information about this schema.",
                url = "http://www.test.com/api/schemas"
        )

)
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
