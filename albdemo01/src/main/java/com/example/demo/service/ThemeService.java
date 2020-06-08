package com.example.demo.service;

import com.example.demo.entity.dto.ThemeDo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ThemeService {

    List<ThemeDo> getAllThemes();

    PageInfo<ThemeDo> getThemeByPage(Integer page, Integer size);

}
