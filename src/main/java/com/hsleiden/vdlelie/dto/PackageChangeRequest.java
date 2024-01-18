package com.hsleiden.vdlelie.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageChangeRequest {
    private String id;
    private int amountInStock;
    private int minAmount;
    private String name;
}
