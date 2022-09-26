package am.itspace.companyemployeespring.dto;


import am.itspace.companyemployeespring.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

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
    @ManyToOne
    private Integer companyId;
    private String profilePic;

}
