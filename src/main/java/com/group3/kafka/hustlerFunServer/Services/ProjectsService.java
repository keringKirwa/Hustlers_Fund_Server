package com.group3.kafka.hustlerFunServer.Services;

import com.group3.kafka.hustlerFunServer.Entities.Farmer;
import com.group3.kafka.hustlerFunServer.Entities.NewProject;
import com.group3.kafka.hustlerFunServer.Repositories.ProjectsRepository;
import com.group3.kafka.hustlerFunServer.Repositories.RegisterFarmerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private RegisterFarmerRepository farmerRepository;

    public NewProject createNewProject(NewProject myProject) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(myProject.getFarmerId());
        if (farmerOptional.isPresent()) {

            Farmer farmer = farmerOptional.get();
            NewProject savedProject = projectsRepository.save(myProject);
            farmer.setNumberOfProjects(farmer.getNumberOfProjects() + 1);
            farmerRepository.save(farmer);
            return savedProject;

        } else
            throw new IllegalStateException("Farer with  that id was not found");

    }

    public NewProject findOneByFarmerID(String farmerID) {
        return projectsRepository.findOneByFarmerId(farmerID);

    }

    public List<NewProject> findAllByFarmerID(String farmerID) {
        List<NewProject> projects = projectsRepository.findAllByFarmerId(farmerID);
        return projects;

    }

    public List<NewProject> retrieveAllProjects() {
        return projectsRepository.findAll();

    }
}
