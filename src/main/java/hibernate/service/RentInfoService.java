package hibernate.service;

import hibernate.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoService{
    List<RentInfo> getRentInfoBetween(LocalDate localDate1, LocalDate localDate2);

    Integer getCountOfRentInfoByAgencyId(Long agencyId);
}
