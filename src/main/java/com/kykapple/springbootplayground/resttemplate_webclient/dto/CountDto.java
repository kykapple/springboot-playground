package com.kykapple.springbootplayground.resttemplate_webclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CountDto {

    @JsonProperty("total_count")
    private int count;

}
