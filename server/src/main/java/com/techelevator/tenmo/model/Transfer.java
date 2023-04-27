package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    int transferId;
    BigDecimal transferAmount;
    int senderUserId;
    int senderAccountId;
    int receiverAccountId;

    public Transfer() {
    }

    public Transfer(int transferId, BigDecimal transferAmount, int senderUserId, int senderAccountId, int receiverAccountId) {
        this.transferId = transferId;
        this.transferAmount = transferAmount;
        this.senderUserId = senderUserId;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
    }

    public Transfer(BigDecimal transferAmount, int senderUserId, int senderAccountId, int receiverAccountId) {
        this.transferAmount = transferAmount;
        this.senderUserId = senderUserId;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferAmount=" + transferAmount +
                ", senderUserId=" + senderUserId +
                ", senderAccountId=" + senderAccountId +
                ", receiverAccountId=" + receiverAccountId +
                '}';
    }
}
