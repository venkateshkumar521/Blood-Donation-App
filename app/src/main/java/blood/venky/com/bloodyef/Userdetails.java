package blood.venky.com.bloodyef;

public class Userdetails {

    private String Desc;
    private  String Hosp;
    private  String Address;
    private  String Blood;
    private  String Mobile;
    private String Remove;
    public Userdetails() {

    }

    public String getDesc() {
        return Desc;
    }

    public String getHosp() {
        return Hosp;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setHosp(String hosp) {
        Hosp = hosp;
    }

    public String getBlood() {
        return Blood;
    }

    public String getMobile() {
        return Mobile;
    }



    public void setBlood(String blood) {

        this.Blood = blood;
    }

    public void setMobile(String mobile)
    {
        this.Mobile = mobile;
    }



    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        this.Address = address;
    }

    public String getRemove() {
        return Remove;
    }

    public void setRemove(String remove) {
        Remove = remove;
    }
}

