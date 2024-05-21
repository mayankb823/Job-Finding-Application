package com.project.companyms;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id);
     Company addCompany(Company company);

     boolean deleteCompanyById(Long id);
}
