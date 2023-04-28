package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface TransferDao {

    List<Transfer> findTransfersByUserId(int userId);

    Transfer findTransferByTransferId(int transferId);

    Transfer createTransfer(Transfer transfer);


}
