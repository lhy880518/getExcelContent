package com.example.excel2.service.impl;

import com.example.excel2.mapper.CampaignApplyMapper;
import com.example.excel2.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private CampaignApplyMapper campaignApplyMapper;

    @Override
    public int getCampaignApply3() throws DataAccessException {
        return campaignApplyMapper.selectCampaignApply3();
    }
}
