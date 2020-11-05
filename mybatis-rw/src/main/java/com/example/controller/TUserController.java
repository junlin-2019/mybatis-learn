package com.example.controller;

import com.example.entity.TUser;
import com.example.service.TUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2020-09-28 18:22:57
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    @GetMapping("selectOne")
    public TUser selectOne(Integer id) {
        return this.tUserService.queryById(id);
    }

    @PostMapping("insert")
    public TUser insert() {
        TUser tUser = new TUser();
        tUser.setId(34);
        tUser.setName("似懂非懂");
        return this.tUserService.insert(tUser);
    }

}