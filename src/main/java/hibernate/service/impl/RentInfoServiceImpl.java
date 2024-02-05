package hibernate.service.impl;

import hibernate.Dao.RentInfoDao;
import hibernate.Dao.impl.RentInfoImpl;
import hibernate.entities.RentInfo;
import hibernate.service.RentInfoService;

import java.time.LocalDate;
import java.util.List;

public class RentInfoServiceImpl implements RentInfoService {
    RentInfoDao rentInfoDao = new RentInfoImpl();
    @Override
    public List<RentInfo> getRentInfoBetween(LocalDate localDate1, LocalDate localDate2) {
        return rentInfoDao.getRentInfoBetween(localDate1, localDate2);
    }

    @Override
    public Integer getCountOfRentInfoByAgencyId(Long agencyId) {
        return rentInfoDao.getCountOfRentInfoByAgencyId(agencyId);
    }
}
