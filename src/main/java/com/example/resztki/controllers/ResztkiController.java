package com.example.resztki.controllers;

import com.example.resztki.dao.PriceRepository;
import com.example.resztki.dao.UsersRepository;
import com.example.resztki.domain.UsersDomain;
import com.example.resztki.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
