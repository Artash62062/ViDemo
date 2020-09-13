package am.tech42.videmodemo.model;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    Video video;
    @Enumerated(value = EnumType.STRING)
    ReactionsValue reactionsValue;

}
