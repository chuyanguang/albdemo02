package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.entity.dto.ThemeDo;
import com.example.demo.service.ThemeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping(value = "getAll")
    public Result<List<ThemeDo>> getAllThemes(){
        List<ThemeDo> themeDoList = themeService.getAllThemes();
        return new Result(HttpStatus.OK, "查询成功", themeDoList);
    }

    @GetMapping(value = "getByPage")
    public Result<PageInfo<ThemeDo>> getThemesByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(name = "size", defaultValue = "10") Integer size){
        PageInfo<ThemeDo> pageInfo = themeService.getThemeByPage(page, size);
        log.info(pageInfo.getList().toString());
        return new Result(HttpStatus.OK, "查询成功", pageInfo);
    }

}
