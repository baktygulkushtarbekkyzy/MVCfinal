package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Company;

import java.util.List;
@Service
public interface CompanyService {

    void saveCompany(Company company);

    Company updateCompany(Long id, Company company);

    Company getById(Long id);

    List<Company> getAllCompany();

    void deleteCompanyById(Long id);
}
