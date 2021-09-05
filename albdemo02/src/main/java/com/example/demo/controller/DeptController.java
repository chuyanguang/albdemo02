package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.entity.ResultData;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.entity.dto.EmplyeeDo;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping(value = "getByCode")
    public ResultData<List<DeptDo>> getByCodes(String code) {
        List<DeptDo> deptList = deptService.getDeptByCode(code);
        return ResultData.ok("查询成功", deptList);
    }

    @PostMapping("import")
    public ResultData importData(@RequestParam MultipartFile file) {
        try {
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(1);
            importParams.setTitleRows(0);
            List<EmplyeeDo> list = ExcelImportUtil.importExcel(file.getInputStream(), EmplyeeDo.class, importParams);
            return ResultData.ok("操作成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传文件失败,{}", e.getMessage());
            return ResultData.fail("操作失败：" + e.getMessage(), null);
        }
    }
}
