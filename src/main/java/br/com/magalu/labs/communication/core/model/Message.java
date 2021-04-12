package br.com.magalu.labs.communication.core.dataprovider.model;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy ="native")
    private Long id;

    @NotNull
    private LocalDateTime dateTimeSchedule;

    @NotNull
    @Setter
    @OneToOne
    @JoinColumn(name = "id_destination", referencedColumnName = "id")
    private Destination destination;

    @NotNull
    @Column(nullable = false)
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageState messageState;

    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
