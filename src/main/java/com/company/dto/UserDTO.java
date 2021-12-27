package com.company.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {
    private int id;
    private String name;
}
