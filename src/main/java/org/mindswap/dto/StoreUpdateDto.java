package org.mindswap.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateDto {
    private String city;

    private String address;

}
