package ua.epam.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(generator = "kaugen")
    private int id;
    @NotEmpty()
    @Size(min = 3, max = 30)
    @Column(name = "user_login")
    private String login;
    @NotEmpty()
    @Size(min = 3, max = 60)
    @Column(name = "user_pass")
    private String password;
    @NotEmpty()
    @Email()
    @Column(name = "user_email")
    private String email;
    @Size(min = 3, max = 30)
    @Column(name = "user_name")
    private String name;
    @Size(min = 3, max = 30)
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_role")
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}