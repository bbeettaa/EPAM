package ua.epam.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "subscribes_table")
public class Subscriptions {

    @Id
    @Column(name = "subscribes_id")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;
    @Column(name="sub_user_id")
    private int userId;
    @Column(name="sub_event_id")
    private int eventId;

}