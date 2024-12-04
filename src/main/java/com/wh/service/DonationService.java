package com.wh.service;

import com.wh.dto.DonationDTO;

public interface DonationService {

    boolean isStaff(String userName);

    boolean isDonor(String userName);

    void acceptDonations(DonationDTO donationDto);

}
