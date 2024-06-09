package com.idz4.usersservice.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq_gen")
    @SequenceGenerator(name = "session_seq_gen", sequenceName = "sessions_id_seq", allocationSize = 1)
    private Long id;
    private Long user_id;
    private String token;
    private Date expires;

    public static long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
}
