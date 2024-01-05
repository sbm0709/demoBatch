package com.demobatch.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DistrictCountDTO {
    private String  shopName;

    private String districtName;

    private int viewCount;

}
