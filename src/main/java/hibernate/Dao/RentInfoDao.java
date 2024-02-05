package hibernate.Dao;


import hibernate.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoDao{
    List<RentInfo> getRentInfoBetween(LocalDate localDate1, LocalDate localDate2);

    Integer getCountOfRentInfoByAgencyId(Long agencyId);
}
