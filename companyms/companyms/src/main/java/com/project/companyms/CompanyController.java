package com.project.companyms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
       companyService.addCompany(company);//Add Company
       return new ResponseEntity<>(company,HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> company=companyService.getAllCompanies();
        return new ResponseEntity(company, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Optional<Company> company=companyService.getCompanyById(id);
        return new ResponseEntity(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCompany(@PathVariable Long id){
           companyService.deleteCompanyById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
