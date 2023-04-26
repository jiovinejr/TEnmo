package com.techelevator.tenmo.model;

public class Transfer {

    int transferId;
    double transferAmount;
    String senderUserId;
    String senderName;
    String receiverUserId;
    String receiverName;
    boolean validTransfer;

    public Transfer() {
    }

    public Transfer(double transferAmount, String senderUserId, String receiverUserId, boolean validTransfer) {
        this.transferAmount = transferAmount;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.validTransfer = validTransfer;
    }

    public Transfer(double transferAmount, String senderUserId, String senderName, String receiverUserId, String receiverName, boolean validTransfer) {
        this.transferAmount = transferAmount;
        this.senderUserId = senderUserId;
        this.senderName = senderName;
        this.receiverUserId = receiverUserId;
        this.receiverName = receiverName;
        this.validTransfer = validTransfer;
    }

    public Transfer(int transferId, double transferAmount, String senderUserId, String senderName, String receiverUserId, String receiverName, boolean validTransfer) {
        this.transferId = transferId;
        this.transferAmount = transferAmount;
        this.senderUserId = senderUserId;
        this.senderName = senderName;
        this.receiverUserId = receiverUserId;
        this.receiverName = receiverName;
        this.validTransfer = validTransfer;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public boolean isValidTransfer() {
        return validTransfer;
    }

    public void setValidTransfer(boolean validTransfer) {
        this.validTransfer = validTransfer;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferAmount=" + transferAmount +
                ", senderUserId='" + senderUserId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverUserId='" + receiverUserId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", validTransfer=" + validTransfer +
                '}';
    }
}
