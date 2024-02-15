package com.flagship.dto.response;

import com.flagship.model.db.ImportMaster;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class ImportResponse {
  private Integer code;
  private String message;
  private String shipmentNo;
  private String country;
  private ZonedDateTime date;
  List<ImportDetailsResponse> importDetailsResponseList;

  public static ImportResponse from(String message, ImportMaster importMaster,
                                    List<ImportDetailsResponse> importDetailsResponseList) {
    return ImportResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .shipmentNo(importMaster.getShipmentNo())
            .country(importMaster.getCountry().getCountryName())
            .date(importMaster.getDate())
            .importDetailsResponseList(importDetailsResponseList)
            .build();

  }
}
