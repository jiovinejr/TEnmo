package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    boolean validateTransfer();

    List<Transfer> findTransfersByUserId();

    List<Transfer> findTransferByTransferId();

    Transfer createTransfer();

}
