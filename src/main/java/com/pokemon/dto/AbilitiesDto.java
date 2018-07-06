package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilitiesDto {
    private String abilityName;
    public AbilitiesDto() {
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @JsonProperty("is_hidden")
    private boolean isHidden;

    @JsonProperty("slot")
    private int slot;

//    @SuppressWarnings("unchecked")
    @JsonProperty("ability")
    public String unpackNested(Map<String, String> ab) {
        return this.abilityName=ab.get("name");
    }

}