package browserCustomer;

public class customerBean {
    String mobile="";
    String cname="";
    String address="";
    String city;
    String email="";
    String ctype="";
    byte[] pic;
    byte[] acard;
    String doe;
    public customerBean(String mobile,String cname , String address, String city, String email, String ctype, byte[] pic,byte[] acard,String doe) {
        this.mobile = mobile;
        this.doe = doe;
        this.acard = acard;
        this.email = email;
        this.city = city;
        this.cname = cname;
        this.address = address;
        this.ctype = ctype;
        this.pic = pic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    public byte[] getAcard() {
        return acard;
    }

    public void setAcard(byte[] acard) {
        this.acard = acard;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
