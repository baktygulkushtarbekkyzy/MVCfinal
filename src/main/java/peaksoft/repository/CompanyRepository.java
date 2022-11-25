package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import java.util.List;

@Repository
public interface CompanyRepository {

    void saveCompany(Company company);

    Company updateCompany(Long id, Company company);

    Company getById(Long id);

    List<Company> getAllCompany();

    void deleteCompanyById(Long id);



}
