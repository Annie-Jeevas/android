package android.anna.firstapp.model;

import lombok.Data;

/**
 * Created by anna_000 on 15.11.2017.
 */
@Data
public class Client implements IModel {

    private int id;
    private String email;
    private String name;


    @Override
    public int getFieldNumber() {
        return this.getClass().getDeclaredFields().length;
    }

    @Override
    public String[] getDataForTable() {
        String[] result = new String[getFieldNumber()];
        result[0] = String.valueOf(id);
        result[1] = email;
        result[2] = name;
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
