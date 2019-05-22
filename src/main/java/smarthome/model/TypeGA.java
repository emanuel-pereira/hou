package smarthome.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.Contract;
import smarthome.model.validations.Name;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonSerialize
public class TypeGA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    public TypeGA() {
    }

    public TypeGA(String newType) {
        setType(newType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeGA typeGA = (TypeGA) o;
        return type.equals(typeGA.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}





