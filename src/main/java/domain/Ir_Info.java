package domain;

public class Ir_Info {
    private int id;
    private String irname;
    private String irheader_code;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIrname() {
        return irname;
    }

    public void setIrname(String irname) {
        this.irname = irname;
    }

    public String getIrheader_code() {
        return irheader_code;
    }

    public void setIrheader_code(String irheader_code) {
        this.irheader_code = irheader_code;
    }

    @Override
    public String toString() {
        return "Ir_Info{" +
                "id=" + id +
                ", ir_name='" + irname + '\'' +
                ", irheader_code='" + irheader_code + '\'' +
                '}';
    }
}

