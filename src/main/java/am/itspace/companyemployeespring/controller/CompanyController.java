package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.entity.Company;
import am.itspace.companyemployeespring.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping("/company")
    public String company(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "company";
    }

    @GetMapping("/company/add")
    public String addCompanyPage() {
        return "addCompany";
    }

    @PostMapping("/company/add")
    public String addCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/company";
    }

    @GetMapping("/company/delete")
    public String deleteCompany(@RequestParam("id") int id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }
}
