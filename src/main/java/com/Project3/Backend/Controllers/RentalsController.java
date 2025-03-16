package com.Project3.Backend.Controllers;

import com.Project3.Backend.Models.Rental;
import com.Project3.Backend.Services.RentalService;
import com.Project3.Backend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/rentals")
public class RentalsController {



    @Autowired
    private RentalService rentalService;

    @Autowired
    UserService userService;


    @PostMapping("/store")
    public ResponseEntity<Rental> saveRental(@ModelAttribute Rental rental,@RequestParam("image") MultipartFile imageFile){


        return new ResponseEntity<Rental>(rentalService.addRental(rental, imageFile), HttpStatus.CREATED);

    }



    @PostMapping("/product")


    public ResponseEntity<Rental> addProduct(@ModelAttribute Rental rental, @RequestParam("file") MultipartFile imageFile) {


/*
        String fileName = fileUploadService.uploadFile(imageFile);
        //ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
        String filepath= ServletUriComponentsBuilder.fromCurrentContextPath().path("images/"+fileName).toUriString();
        //return "Upload Successfully=" + filepath;
     */

        //rental.setPicture(filepath);

        //Rental rental1 = rentalService.addRental(rental, imageFile);

        //return new ResponseEntity<Rental>(rentalService.saveRental(rental), HttpStatus.CREATED);
        return new ResponseEntity<Rental>(rentalService.addRental(rental, imageFile), HttpStatus.CREATED);

        /*try {
            Rental rental1 = rentalService.addRental(rental, imageFile);
            return new ResponseEntity<>(rental1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);


        }

         */
    }

    //GetAll Rest Api
    @GetMapping
    public List<Rental> getAllRentals(){
        return rentalService.getAllRentals();
    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/employees/1
    public ResponseEntity<Rental> getRentalById(@PathVariable("id") int rentalID){
        return new ResponseEntity<Rental>(rentalService.getRentalById(rentalID),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("/update/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable("id") int id,
                                               @RequestBody Rental rental){
        return new ResponseEntity<Rental>(rentalService.updateRental(rental,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        //delete employee from db
        rentalService.deleteRental(id);
        return new ResponseEntity<String>("Rental deleted Successfully.",HttpStatus.OK);
    }
}