package com.blog.BlogAppApis.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    //we use it for transfering data

    private Integer id;
    @NotEmpty
    @Size(min = 4,message = "must me atleast 4")
    private String name;
    @Email(message = "address not valid")
    private String email;
    @NotEmpty
    @Size(min=3,max = 10,message = "atleast 3 at max 10")
    private String password;//@pattern(regexp=),regular expression
    @NotEmpty
    private String about;
}
