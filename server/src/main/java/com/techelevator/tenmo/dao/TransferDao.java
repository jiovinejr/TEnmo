package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface TransferDao {

    boolean validateTransfer(BigDecimal transferAmount);

    List<Transfer> findTransfersByUserId();

    Transfer findTransferByTransferId(int transferId);

    Transfer createTransfer(Transfer transfer);


}
