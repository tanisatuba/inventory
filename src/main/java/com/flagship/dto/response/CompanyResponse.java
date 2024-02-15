package com.flagship.dto.response;

import com.flagship.model.db.Sale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {
    private String companyName;
    private String salesCode;
    private String articleCode;
    public static CompanyResponse from(Sale sale){
        return CompanyResponse.builder()
                .companyName(sale.getSupplier().getSupplierName())
                .salesCode(sale.getSaleCode())
                .articleCode(sale.getArticle())
                .build();
    }
}