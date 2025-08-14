package com.example.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserDto {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
}
