package ua.epam.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "report_table")
public class Report {
    @Id
    @Column(name = "report_id")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private int id;
    @NotEmpty()//message = "Name should not be empty")
    @Size(min = 3, max = 30)//, message = "Name should be between 3 and 30 characters")
    @Pattern(regexp = "^\\S[^/%]{0,30}")
    @Column(name = "report_name")
    private String name;
    @Column(name = "report_event_id")
    private int eventId;
    @Column(name = "report_speaker_id")
    private int speakerId;
    @NotEmpty()//message = "Speaker login should not be empty")
    @Transient
    private String speakerName;
    @Transient
    private User speaker;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventId=" + eventId +
                ", speakerId=" + speakerId +
                ", speakerName='" + speakerName + '\'' +
                ", speaker=" + speaker +
                '}';
    }
}
