package domain;

public class Ir_FuncInfo {
    private String header_code;
    private String remote_code;
    private String remote_function;

    public String getHeader_code() {
        return header_code;
    }

    public void setHeader_code(String header_code) {
        this.header_code = header_code;
    }

    public String getRemote_code() {
        return remote_code;
    }

    public void setRemote_code(String remote_code) {
        this.remote_code = remote_code;
    }

    public String getRemote_function() {
        return remote_function;
    }

    public void setRemote_function(String remote_function) {
        this.remote_function = remote_function;
    }

    @Override
    public String toString() {
        return "Ir_FuncInfo{" +
                "header_code='" + header_code + '\'' +
                ", remote_code='" + remote_code + '\'' +
                ", remote_function='" + remote_function + '\'' +
                '}';
    }
}

