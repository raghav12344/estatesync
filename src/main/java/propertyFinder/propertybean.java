package propertyFinder;

public class propertybean {

    private int rid;
    private String mobile;
    private String location;
    private String area;
    private String city;
    private float size;
    private float front;
    private float rear;
    private float leftside;
    private float rightside;
    private String facing;
    private String proptype;
    private String consttype;
    private String approvedby;
    private String price;
    private String otherinfo;
    private byte[] pic1;
    private byte[] pic2;

    public propertybean() {
    }

    public propertybean(int rid, String mobile, String location, String area,
                        String city, float size, float front, float rear,
                        float leftside, float rightside, String facing,
                        String proptype, String consttype, String approvedby,
                        String price, String otherinfo,
                        byte[] pic1, byte[] pic2) {

        this.rid = rid;
        this.mobile = mobile;
        this.location = location;
        this.area = area;
        this.city = city;
        this.size = size;
        this.front = front;
        this.rear = rear;
        this.leftside = leftside;
        this.rightside = rightside;
        this.facing = facing;
        this.proptype = proptype;
        this.consttype = consttype;
        this.approvedby = approvedby;
        this.price = price;
        this.otherinfo = otherinfo;
        this.pic1 = pic1;
        this.pic2 = pic2;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getFront() {
        return front;
    }

    public void setFront(float front) {
        this.front = front;
    }

    public float getRear() {
        return rear;
    }

    public void setRear(float rear) {
        this.rear = rear;
    }

    public float getLeftside() {
        return leftside;
    }

    public void setLeftside(float leftside) {
        this.leftside = leftside;
    }

    public float getRightside() {
        return rightside;
    }

    public void setRightside(float rightside) {
        this.rightside = rightside;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public String getConsttype() {
        return consttype;
    }

    public void setConsttype(String consttype) {
        this.consttype = consttype;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public byte[] getPic1() {
        return pic1;
    }

    public void setPic1(byte[] pic1) {
        this.pic1 = pic1;
    }

    public byte[] getPic2() {
        return pic2;
    }

    public void setPic2(byte[] pic2) {
        this.pic2 = pic2;
    }
}
