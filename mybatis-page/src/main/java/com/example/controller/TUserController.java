package com.example.controller;

import com.example.entity.TUser;
import com.example.plugin.PageResponse;
import com.example.service.TUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2020-10-06 14:17:25
 */
@RestController
@RequestMapping("user")
public class TUserController {

    @Resource
    private TUserService tUserService;


    @GetMapping("selectOne")
    public TUser selectOne(Integer id) {
        return this.tUserService.queryById(id);
    }

    @GetMapping("selectBypage")
    public PageResponse<List<TUser>> selectOne(Integer page, Integer size) {
        return this.tUserService.queryByPage(page,size);
    }


    @PostMapping("add")
    public TUser add(@RequestBody TUser tUser){
        return tUserService.insert(tUser);
    }
}