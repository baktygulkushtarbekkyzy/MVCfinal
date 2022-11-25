package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAll")
    public String getAllCompanies(Model model){
        model.addAttribute("companies",companyService.getAllCompany());
        return "/company/allCompany";
    }

    @GetMapping("/new")
    public String addCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "/company/newCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company){
        companyService.saveCompany(company);
        return "redirect:/companies/getAll";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable Long id){
        model.addAttribute("company",companyService.getById(id));
        return "/company/updateCompany";
    }

    @PostMapping("{id}/updateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company,
                                    @PathVariable("id") Long id) {
        companyService.updateCompany(id, company);
        return "redirect:/companies/getAll";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        companyService.deleteCompanyById(id);
        return "redirect:/companies/getAll";
    }
}
