package com.Project3.Backend.Services.Impl;

import com.Project3.Backend.Models.Rental;
import com.Project3.Backend.Models.User;
import com.Project3.Backend.Repositories.RentalRepository;
import com.Project3.Backend.Repositories.UserRepository;
import com.Project3.Backend.Services.FileUploadService;

import com.Project3.Backend.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private FileUploadService fileUploadService;




    @Override
    public Rental saveRental(Rental rental) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        rental.setOwner(user);

        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(int id) {

        Optional<Rental> rental = rentalRepository.findById(id);
        if(rental.isPresent()){
            return rental.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public Rental updateRental(Rental rental, int id) {

        Rental existingRental = rentalRepository.findById(id).orElseThrow(()-> new RuntimeException());
        existingRental.setName(rental.getName());
        existingRental.setSurface(rental.getSurface());
        existingRental.setPrice(rental.getPrice());
        existingRental.setDescription(rental.getDescription());
        //existingRental.setOwner(rental.getOwner());
        // save
        rentalRepository.save(existingRental);
        return existingRental;




             /*
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED)
                */
    }

    @Override
    public void deleteRental(int id) {

        //check
        rentalRepository.findById(id).orElseThrow(()-> new RuntimeException());
        //delete
        rentalRepository.deleteById(id);
    }

    @Override
    public Rental addRental(Rental rental, MultipartFile imageFile) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());


        String fileName = fileUploadService.uploadFile(imageFile);
        //ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
        String filepath= ServletUriComponentsBuilder.fromCurrentContextPath().path("images/"+fileName).toUriString();
        //return "Upload Successfully=" + filepath;

        rental.setOwner(user);
        rental.setPicture(filepath);

        return rentalRepository.save(rental);
    }




}

