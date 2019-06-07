package com.example.resztki.controllers;

import com.example.resztki.dao.PriceRepository;
import com.example.resztki.dao.UsersRepository;
import com.example.resztki.domain.UsersDomain;
import com.example.resztki.dto.UpdateUserDto;
import com.example.resztki.dto.UserDto;
import com.example.resztki.exception.UserExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class ResztkiController {


    UsersRepository usersRepository;
    PriceRepository priceRepository;


    @Autowired
    public ResztkiController(UsersRepository usersRepository, PriceRepository priceRepository) {
        this.usersRepository = usersRepository;
        this.priceRepository = priceRepository;
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

}
