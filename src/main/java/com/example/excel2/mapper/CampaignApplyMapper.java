package com.example.excel2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CampaignApplyMapper {
    int selectCampaignApply3() throws DataAccessException;
}
