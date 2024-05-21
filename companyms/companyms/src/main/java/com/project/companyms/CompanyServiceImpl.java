package com.project.companyms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    @Override
    public Company addCompany(Company company){
        return companyRepository.save(company);
    }
    @Override
    public Optional<Company> getCompanyById(Long id){
        return companyRepository.findById(id);
    }

    @Override
    public boolean deleteCompanyById(Long Id){
         companyRepository.deleteById(Id);
         return true;
    }
}
