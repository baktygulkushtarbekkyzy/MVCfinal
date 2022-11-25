package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {
    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Company> getAllCompany() {
        return   manager.createQuery("select c from Company c", Company.class).getResultList();

    }

    @Override
    public void deleteCompanyById(Long id) {
        manager.remove(getById(id));
    }





    @Override
    public void saveCompany(Company company) {
        manager.merge(company);
    }

    @Override
    public Company updateCompany(Long id,Company company) {
        Company newCompany = getById(id);
        newCompany.setCompanyName(company.getCompanyName());
        newCompany.setLocatedCountry(company.getLocatedCountry());
        manager.merge(newCompany);
        return newCompany;
    }

    @Override
    public Company getById(Long id) {
        return manager.find(Company.class,id);
    }
}
