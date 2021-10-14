package model;

import java.io.Serializable;
import androidx.annotation.NonNull;

public class ClientOrder implements Serializable {
    private int clNumber;
    private String providerType;
    private int nbYears;

    public ClientOrder(int clNumber, String providerType, int nbYears) {
        this.clNumber = clNumber;
        this.providerType = providerType;
        this.nbYears = nbYears;
    }
    public int getClNumber() {
        return clNumber;
    }

    public void setClNumber(int clNumber) {
        this.clNumber = clNumber;
    }

    public String getproviderType() {
        return providerType;
    }

    public void setproviderType(String providerType) {
        this.providerType = providerType;
    }

    public int getNbYears() {
        return nbYears;
    }

    public void getNbYears(int nbYears) {
        this.nbYears = nbYears;
    }

    @NonNull
    @Override
    public String toString() {
        return "Client Number : "+ clNumber + "\n" + "Provider type: " + providerType + "\n" +
                "Number of Years: " + nbYears + "\n" + "Total: " + getSubtotal() + "\n" ;
    }

    public float getSubtotal() {
        float subtotal = 0;
        float tps = subtotal * 0.06f;
        float tvq = (subtotal + tps) * 0.095f;
        float total = subtotal + tps + tvq;
        if (providerType.equals("Bell")){
            subtotal = nbYears* 60f;
            tps = subtotal * 0.06f;
            tvq = (subtotal + tps) * 0.095f;
            total = subtotal + tps + tvq;
            if (nbYears == 12){
                subtotal = 600;
                tps = subtotal * 0.06f;
                tvq = (subtotal + tps) * 0.095f;
                total = subtotal + tps + tvq;
            }
        }else {
            if(providerType.equals("Videotron"))
            {
                subtotal = nbYears* 70f;
                tps = subtotal * 0.06f;
                tvq = (subtotal + tps) * 0.095f;
                total = subtotal + tps + tvq;
                if (nbYears == 6){
                    subtotal = 350;
                    tps = subtotal * 0.06f;
                    tvq = (subtotal + tps) * 0.095f;
                    total = subtotal + tps + tvq;
                }
                else if(nbYears == 12){
                    subtotal = 70 * 12 * 0.3f;
                    tps = subtotal * 0.06f;
                    tvq = (subtotal + tps) * 0.095f;
                    total = subtotal + tps + tvq;
                }
            }else if( providerType.equals("Acanac")){
                subtotal = nbYears* 45f;
                tps = subtotal * 0.06f;
                tvq = (subtotal + tps) * 0.095f;
                total = subtotal + tps + tvq;
                if (nbYears == 12){
                    subtotal = 45 * 12f;
                    tps = subtotal * 0.06f;
                    tvq = (subtotal + tps) * 0.095f;
                    total = subtotal + tps + tvq;
                }
            }
        }
        return  total;
    }
}
