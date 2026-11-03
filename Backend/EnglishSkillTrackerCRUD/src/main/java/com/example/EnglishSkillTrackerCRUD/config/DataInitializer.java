package com.example.EnglishSkillTrackerCRUD.config;

import com.example.EnglishSkillTrackerCRUD.entity.PermissionEntity;
import com.example.EnglishSkillTrackerCRUD.entity.RoleEntity;
import com.example.EnglishSkillTrackerCRUD.model.PermissionEnum;
import com.example.EnglishSkillTrackerCRUD.model.RoleEnum;
import com.example.EnglishSkillTrackerCRUD.repository.PermissionRepository;
import com.example.EnglishSkillTrackerCRUD.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepo;
    private final PermissionRepository perRepo;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (PermissionEnum perEnum : PermissionEnum.values()){
            if (perRepo.findByName(perEnum).isEmpty()){
                PermissionEntity permission = new PermissionEntity();
                permission.setName(perEnum);
                permission.setDescription(perEnum.getDescription());
                perRepo.save(permission);
            }
        }
       for (RoleEnum roleEnum :RoleEnum.values()){

           RoleEntity role = roleRepo.findByName(roleEnum).orElseGet(() -> {
               RoleEntity newRole = new RoleEntity();
               newRole.setName(roleEnum);
               newRole.setDescription(roleEnum.getDescription());
               return newRole;
           });
           Set<PermissionEntity> permissions = roleEnum.getPermission().stream().map(perEnum -> perRepo.findByName(perEnum).orElseThrow()).collect(Collectors.toSet());
            role.setPermissions(permissions);
           roleRepo.save(role);

       }


    }
}
