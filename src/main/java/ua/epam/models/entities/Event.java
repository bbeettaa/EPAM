package ua.epam.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "event_table")
public class Event {
    @Id
    @Column(name = "event_id")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;
    @NotEmpty()
    @Size(min = 3, max = 30)
    @Column(name = "event_name")
    private String name;
    @Column(name = "event_date_start")
    private Date dateBegin;
    @Column(name = "event_time_start")
    private Time timeBegin;
    @Column(name = "event_reports_count")
    private int reports;

    @Transient
    private int subscribers;
    @Transient
    private boolean isSubscribe;
    @Transient
    private boolean isActive;
}