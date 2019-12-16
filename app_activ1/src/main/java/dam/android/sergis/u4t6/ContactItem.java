package dam.android.sergis.u4t6;

public class ContactItem {

    private String name;
    private String number;
    private String id2;
    private String contactID;
    private String key2;
    private String rawId;
    private String typeCo;
    private String image;

    //TODO Ex1: Constructor de ContactItem
    public ContactItem() {

    }

    //TODO Ex1: Geters y seters de los diferentes atributos de la classe

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getRawId() {
        return rawId;
    }

    public void setRawId(String rawId) {
        this.rawId = rawId;
    }

    public String getTypeCo() {
        return typeCo;
    }

    public void setTypeCo(String typeCo) {
        this.typeCo = typeCo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", id2='" + id2 + '\'' +
                ", contactID='" + contactID + '\'' +
                ", key2='" + key2 + '\'' +
                ", rawId='" + rawId + '\'' +
                ", typeCo='" + typeCo + '\'' +
                ", image=" + image +
                '}';
    }
}
