package com.employee.management.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Getter
//@Setter
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @NoArgsConstructor
public class EmployeeDto {

    private Long id;
    //@NotNull(message = "First name is not null")
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "email is mandatory")
    private String email;
    @Min(value = 1, message = "departmentId can not be less than 1")
    private Long departmentId;
}
