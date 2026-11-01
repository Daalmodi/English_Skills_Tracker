package com.example.EnglishSkillTrackerCRUD.config;

import com.example.EnglishSkillTrackerCRUD.entity.RoleEntity;
import com.example.EnglishSkillTrackerCRUD.model.RoleEnum;
import com.example.EnglishSkillTrackerCRUD.repository.RolesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RolesRepository roleRepo;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
       for (RoleEnum roleEnum :RoleEnum.values()){
           if(roleRepo.findByName(roleEnum).isEmpty()){
               RoleEntity role = new RoleEntity();
               role.setName(roleEnum);
               role.setDescription(roleEnum.getDescription());
               roleRepo.save(role);
           }
       }

    }
}
