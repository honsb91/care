package com.care.account;

import com.care.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByEmail(@Email @NotBlank String email);

    boolean existsByNickname(@NotBlank @Length(min = 3, max = 20) @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$") String nickname);
}
