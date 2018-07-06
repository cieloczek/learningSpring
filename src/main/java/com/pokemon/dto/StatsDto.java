package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties
public class StatsDto {
    @JsonProperty("base_stat")
    private int base_stat;
    @JsonProperty("effort")
    private int effort;

    private String statName;
    public StatsDto() {
    }
    public void setBase_stat(int base_stat){
        this.base_stat =base_stat;
    }
    public int getBase_stat(){
        return base_stat;
    } public void setEffort(int effort){
        this.effort =effort;
    }
    public int getEffort(){
        return effort;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    @JsonProperty("stat")
    public String unpackNested(Map<String, String> stats) {
        return this.statName=stats.get("name");
    }

}
