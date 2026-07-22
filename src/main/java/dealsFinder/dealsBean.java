package dealsFinder;

import java.time.LocalDate;

public class dealsBean {

    private int pid;
    private String seller;
    private String sellercontact;
    private String buyer;
    private String buyercontact;
    private String finalamount;
    private String dowmpmt;
    private LocalDate dtofdeal;
    private LocalDate dtofreg;
    private String totalcom;
    private String adv;
    private String bal;
    private String status;


    // Constructor
    public dealsBean() {
    }


    public dealsBean(int pid, String seller, String sellercontact, String buyer,
                    String buyercontact, String finalamount, String dowmpmt,
                    LocalDate dtofdeal, LocalDate dtofreg,
                    String totalcom, String adv, String bal, String status) {

        this.pid = pid;
        this.seller = seller;
        this.sellercontact = sellercontact;
        this.buyer = buyer;
        this.buyercontact = buyercontact;
        this.finalamount = finalamount;
        this.dowmpmt = dowmpmt;
        this.dtofdeal = dtofdeal;
        this.dtofreg = dtofreg;
        this.totalcom = totalcom;
        this.adv = adv;
        this.bal = bal;
        this.status = status;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


    public String getSellercontact() {
        return sellercontact;
    }

    public void setSellercontact(String sellercontact) {
        this.sellercontact = sellercontact;
    }


    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }


    public String getBuyercontact() {
        return buyercontact;
    }

    public void setBuyercontact(String buyercontact) {
        this.buyercontact = buyercontact;
    }


    public String getFinalamount() {
        return finalamount;
    }

    public void setFinalamount(String finalamount) {
        this.finalamount = finalamount;
    }


    public String getDowmpmt() {
        return dowmpmt;
    }

    public void setDowmpmt(String dowmpmt) {
        this.dowmpmt = dowmpmt;
    }


    public LocalDate getDtofdeal() {
        return dtofdeal;
    }

    public void setDtofdeal(LocalDate dtofdeal) {
        this.dtofdeal = dtofdeal;
    }


    public LocalDate getDtofreg() {
        return dtofreg;
    }

    public void setDtofreg(LocalDate dtofreg) {
        this.dtofreg = dtofreg;
    }


    public String getTotalcom() {
        return totalcom;
    }

    public void setTotalcom(String totalcom) {
        this.totalcom = totalcom;
    }


    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }


    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}