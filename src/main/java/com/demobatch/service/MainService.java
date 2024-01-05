package com.demobatch.service;


import com.demobatch.dto.DistrictCountDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public ExitStatus execute(){
        jdbcTemplate.execute("TRUNCATE TABLE `shop`");
        return ExitStatus.COMPLETED;
    }

    public List<DistrictCountDTO> selectResult(){
        RowMapper<DistrictCountDTO> rowMapper = (rs, rowNum) -> {
            DistrictCountDTO districtCountDTO = new DistrictCountDTO();
            districtCountDTO.setDistrictName(rs.getString("districtName"));
            districtCountDTO.setViewCount(rs.getInt("viewCount"));
            return districtCountDTO;
        };
        String selectSQL = "SELECT districtName, Sum(viewCount) as 'viewCount' from shop Group By districtName";
        return jdbcTemplate.query(selectSQL, rowMapper);
    }
}
