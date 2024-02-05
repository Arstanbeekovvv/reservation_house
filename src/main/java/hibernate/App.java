package hibernate;

import hibernate.config.DataBaseConnection;
import hibernate.entities.*;
import hibernate.enums.FamilyStatus;
import hibernate.enums.Gender;
import hibernate.enums.HouseType;
import hibernate.service.*;
import hibernate.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        AddressService addressService = new AddressServiceImpl();
        AgencyService agencyService = new AgencyServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();

        //save
//✅        agencyService.save(new Agency("EXM", "+996771900091", new Address("Bishkek", "Vostok5", "Chuy 124")));
//✅        customerService.save(new Customer("Aiturgan", "Turumbekova", "j@gmail.com", LocalDate.of(2003, 1, 15), Gender.FEMALE, "Kyrgyz", FamilyStatus.SINGLE));
//✅        customerService.saveWithRentInfo(new Customer(), new RentInfo());
//✅        ownerService.save(new Owner("Ajybek", "Seyitov", "ajsdfy@gamil.com", LocalDate.of(2007, 5, 16), Gender.MALE));
//✅        ownerService.saveWithHouse(new Owner("Mirl", "arstanbekov", "saasfm@gail.com", LocalDate.of(2002, 4, 7), Gender.MALE), new House());
//✅        houseService.saveHouse(new House(HouseType.EARTH_HOME, BigDecimal.valueOf(30000L),2.1, "LLM", 3, true, new Address("Bremen", "Alsheim", "125")), 1L);

        // get by id
//✅        System.out.println(addressService.findById(1L));
//✅        System.out.println(agencyService.findById(1L));
//✅        System.out.println(customerService.findById(1L));
//✅        System.out.println(ownerService.findById(1L));
//✅        System.out.println(houseService.findById(9L));

        // get all
//✅        System.out.println(addressService.findAll());
//✅        System.out.println(agencyService.getAll());
//✅        System.out.println(customerService.getAll());
//✅        System.out.println(ownerService.getAll());
//✅        System.out.println(houseService.getAll());

        // update
//✅        addressService.update(1L, new Address());
//✅        agencyService.update(1L, new Agency());
//✅        customerService.update(1L, new Customer());
//✅        ownerService.update(1L, new Owner("Default", "Seyitov", "dflt@gamil.com", LocalDate.of(2007, 5, 16), Gender.MALE));
//✅        houseService.update(1L, new House());
        // delete
//✅        agencyService.delete(1L);
        customerService.delete(1L);
//        ownerService.delete(1L);
//        houseService.delete(1L);


//        Scanner scanner = new Scanner(System.in);
//        Scanner scannerStr = new Scanner(System.in);

    }

    private static void menu(){
        System.out.println("""
                         Agency
                1. save with Address
                2. find
                3. find all
                4. update
                5. delete

                         Address
                6. find
                7. find all
                8. update
                9. addresses with agency
                10. how many agency in city
                11. group by region

                         Customer
                12. save
                13. save with rent
                14. find
                15. find all
                16. update
                17. delete
                18. rent

                          House
                19. save
                20. find
                21. find all
                22. update
                23. delete
                24. houses in region
                25. all houses by agency
                26. all houses by owner
                27. between dates

                          Owner
                28. save
                29. save with house
                30. find
                31. find all
                32. update
                33. delete
                34. assign owner to agency
                35. owners by agency
                36. owners only name and age

                          RentInfo
                37. renting between dates
                38. how many house in agency
                """);
        System.out.print("----->>>");










    }
}
