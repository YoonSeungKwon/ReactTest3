package yoon.test.reactTest3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="token_owner")
    private Members member;

    private String token;

    @Builder
    public RefreshToken(Members member, String token){
        this.member = member;
        this.token = token;
    }
}
