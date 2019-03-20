package com.example.excel2.controller;

import com.example.excel2.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/")
    public String exMain(HttpServletRequest request){
        Set pathSet = request.getSession().getServletContext().getResourcePaths("/");

        System.out.println(excelService.getCampaignApply3());

        return "index";
    }

    @PostMapping("/excel")
    @ResponseBody
    public List<Map<String, String>> literExcel(Model model, @RequestParam(name = "excel", required = false) MultipartFile excel) throws Exception {

        List<Map<String, String>> arr = new ArrayList<Map<String, String>>();

        if(excel != null){
            try{
                Workbook wb = new XSSFWorkbook(excel.getInputStream());
                Sheet sheet = wb.getSheetAt(0);

                int last = sheet.getLastRowNum();

                for(int i = 1 ; i <= last ; i++){
                    Row row = sheet.getRow(i);
                    Map<String, String> map = new HashMap<>();

                    Cell cellmemberId = row.getCell(0);
                    Cell cellmemberName = row.getCell(1);
                    Cell cellLiter = row.getCell(2);

                    map.put("memberId", String.valueOf((int)cellmemberId.getNumericCellValue()));
                    map.put("memberName", String.valueOf(cellmemberName));
                    map.put("liter", String.valueOf((int)cellLiter.getNumericCellValue()));
                    log.info("map = {}",map);
                    arr.add(map);
                    log.info("arr = {}",arr);
                }

            }catch (Exception e){
            }
        }
        log.info("last arr = {}",arr);

        return arr;
    }
}
