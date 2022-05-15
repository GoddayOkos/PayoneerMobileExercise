package dev.decagon.godday.payoneerapp.model;


public class ListResult {
    private String resultInfo;
    private Networks networks;

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public Networks getNetworks() {
        return networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }
}
