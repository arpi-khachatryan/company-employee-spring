package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.dto.CreateEmployeesDto;
import am.itspace.companyemployeespring.entity.Company;
import am.itspace.companyemployeespring.entity.Employees;
import am.itspace.companyemployeespring.repository.CompanyRepository;
import am.itspace.companyemployeespring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;


    @Value("${company.employee.images.folder}")
    private String folderPath;


    @GetMapping("/employees")
    public String employees(ModelMap modelMap) {
        List<Employees> employees = employeeRepository.findAll();
        modelMap.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeesPage(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "addEmployees";
    }

    @PostMapping("/employees/add")
    public String addEmployees(@ModelAttribute CreateEmployeesDto dto,
                               @RequestParam("employeesImage") MultipartFile file) throws IOException {
        Employees employees = new Employees();
        Company company = companyRepository.getReferenceById(dto.getCompanyId());

        employees.setName(dto.getName());
        employees.setSurname(dto.getSurname());
        employees.setEmail(dto.getEmail());
        employees.setPhoneNumber(dto.getPhoneNumber());
        employees.setSalary(dto.getSalary());
        employees.setPosition(dto.getPosition());
        employees.setCompany(company);
        company.setSize(company.getSize() + 1);

        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            employees.setProfilePic(fileName);
        }
        employeeRepository.save(employees);
        companyRepository.save(company);
        return "redirect:/employees";
    }

    @GetMapping(value = "/employees/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }
}
