package am.itspace.companyemployeespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeesDto {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private double salary;
    private String position;
    private Integer companyId;
    private String profilePic;
}
