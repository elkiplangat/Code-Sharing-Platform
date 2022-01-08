package platform;

import lombok.Data;


import java.time.LocalDate;



public class Code {
    private String code;
    private LocalDate date;

    public Code(String code, LocalDate date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
