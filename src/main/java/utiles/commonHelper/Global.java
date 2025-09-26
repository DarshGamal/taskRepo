package utiles.commonHelper;

import net.bytebuddy.asm.Advice;

public class Global {
  private String Password;
    private  String MobileNumber;
    private String Email;
    private   String FullName;
    static  Global Instance;

    private  Global(){
    }
    public static  Global  getInstance(){
        if(Instance==null)
        { Instance=new Global();
        }
        return Instance ;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword() {
        this.Password = RandomSource.generatePassword();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail() {
        Email = RandomSource.generateRandomEmail();
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber() {
        MobileNumber = RandomSource.generatePhoneNumber();
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName() {
        FullName = RandomSource.generateFullName();
    }
}
