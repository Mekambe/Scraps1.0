package com.example.resztki.controllers;

import com.example.resztki.dao.DBFileRepository;
import com.example.resztki.dao.PriceRepository;
import com.example.resztki.dao.UsersRepository;
import com.example.resztki.domain.DBFile;
import com.example.resztki.domain.PriceDomain;
import com.example.resztki.domain.UsersDomain;
import com.example.resztki.dto.MenuDto;
import com.example.resztki.dto.UpdateUserDto;
import com.example.resztki.dto.UserDto;
import com.example.resztki.exception.UserExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class ResztkiController {


    UsersRepository usersRepository;
    PriceRepository priceRepository;
    DBFileRepository dbFileRepository;

    @Autowired
    public ResztkiController(UsersRepository usersRepository, PriceRepository priceRepository, DBFileRepository dbFileRepository) {
        this.usersRepository = usersRepository;
        this.priceRepository = priceRepository;
        this.dbFileRepository = dbFileRepository;
    }







    @PostMapping("newUser")
    public UsersDomain addNewUser (@RequestBody UserDto userDto){



        UsersDomain usersDomain = new UsersDomain();

        usersDomain.setCompanyAddress(userDto.getCompanyAddress());
        usersDomain.setCompanyName(userDto.getCompanyName());
        usersDomain.setOpenTo(userDto.getOpenTo());
        usersDomain.setOpneFrom(userDto.getOpenTo());

        UsersDomain save = usersRepository.save(usersDomain);

        return save;

    }

    @PostMapping("updateUser")
    public UsersDomain updateUser (@RequestParam UpdateUserDto updateUserDto) throws UserExists {
        Optional<UsersDomain> byId = usersRepository.findById(updateUserDto.getIdUser());
        if (byId.isPresent()){
            byId.get().setOpneFrom(updateUserDto.getOpenFrom());
            byId.get().setOpenTo(updateUserDto.getOpenTo());
            byId.get().setCompanyName(updateUserDto.getCompanyName());
            byId.get().setCompanyAddress(updateUserDto.getCompanyAddress());
            byId.get().setUserName(updateUserDto.getUserName());

            UsersDomain save = usersRepository.save(byId.get());
            return save;

        }else{throw new UserExists (); }


    }

    @PostMapping("UpdateOrAddToMenu")
    public ResponseEntity.BodyBuilder addNewProductToMenu (@RequestBody MenuDto menuDto) throws UserExists {

        Optional<UsersDomain> byId = usersRepository.findById(menuDto.getCustomerID());

        if (byId.isPresent()){
            PriceDomain priceDomain = new PriceDomain();

            priceDomain.setPriceHandler(byId.get());
            priceDomain.setNewSaledPriceOfTheProduct(menuDto.getNewSaledPriceOfTheProduct());
            priceDomain.setPoductInsideTheMenu(menuDto.getPoductInsideTheMenu());
            priceDomain.setRegularPriceOfTheProduct(menuDto.getRegularPriceOfTheProduct());
            priceDomain.setNewSaledPriceOfTheProduct(menuDto.getNewSaledPriceOfTheProduct());

            priceRepository.save(priceDomain);




        }else {   return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT);}
        return null;
    }

    @GetMapping("findUserByID")
    public Optional<UsersDomain> findUserById (@RequestParam ("id") Integer id){

        Optional<UsersDomain> byId = usersRepository.findById(id);
        return byId;
    }

    @GetMapping("findUserByUserName")
    public UsersDomain findUserByName (@RequestParam("name") String name){
        return usersRepository.findByUserName(name);
    }

    @GetMapping("findUserByCompanyName")
    public UsersDomain findUserByCompanyName (@RequestParam("companyName")String companyName){

        return usersRepository.findByCompanyName(companyName);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser (@RequestParam ("id") Integer id){
        Optional<UsersDomain> byId = usersRepository.findById(id);
        usersRepository.delete(byId.get());
    }

    @GetMapping("menuProduct")
    public PriceDomain menuProduct (@RequestParam("productName") String productName){
        return priceRepository.findByPoductInsideTheMenu(productName);

    }







}
