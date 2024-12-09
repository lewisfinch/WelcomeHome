package com.wh.controller;

import com.wh.dto.DonationDTO;
import com.wh.pojo.Person;
import com.wh.pojo.Result;
import com.wh.service.DonationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/isStaff")
    public Result isStaff(@RequestParam String userName) {

        boolean result = donationService.isStaff(userName);
        if (result) {
            return Result.success("Permission Granted");
        } else {
            return Result.error("No Permission, because user is not a staff member");
        }
    }

    @GetMapping("/isDonor")
    public Result isDonor(@RequestBody Person person) {
        String userName = person.getUserName();
        boolean result = donationService.isDonor(userName);
        if (result) {
            return Result.success("Access Granted");
        } else {
            return Result.error("No Permission, because user is not a registered donor");
        }
    }

    @PostMapping("/acceptDonations")
    public Result acceptDonations(@RequestBody DonationDTO donationDto) {
        log.info("Accepting donation: {}", donationDto);
        try {
            donationService.acceptDonations(donationDto);
            return Result.success("Donation accepted successfully");
        } catch (Exception e) {
            log.error("Error accepting donation", e);
            return Result.error("Failed to accept donation");
        }
    }

}
