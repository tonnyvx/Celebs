package antonioneto.unibratec.com.br.celebinfo.model;

import java.io.Serializable;

/**
 * Created by AntonioNeto on 03/12/2016.
 */
public class Celeb implements Serializable {
    private String age;
    private String birthday;
    private String birth_place;
    private String birth_sign;
    private String birth_year;
    private String name;
    private String occupation;
    private String photo_url;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getBirth_sign() {
        return birth_sign;
    }

    public void setBirth_sign(String birth_sign) {
        this.birth_sign = birth_sign;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
