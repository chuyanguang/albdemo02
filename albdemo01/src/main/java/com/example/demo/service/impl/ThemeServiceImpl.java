package com.example.demo.service.impl;

import com.example.demo.dao.ThemeDao;
import com.example.demo.entity.dto.ThemeDo;
import com.example.demo.service.ThemeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeDao themeDao;

    @Override
    public List<ThemeDo> getAllThemes() {
        return themeDao.getAllTheme();
    }

    @Override
    public PageInfo<ThemeDo> getThemeByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ThemeDo> themeList = themeDao.getAllTheme();
        return new PageInfo<>(themeList);
    }

}
