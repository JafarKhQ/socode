package com.epam.socode.repository;

import com.epam.socode.domain.VerificationToken;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
public interface VerificationTokenRepository {
    VerificationToken addVerificationToken(VerificationToken verificationToken);

    VerificationToken findVerificationTokenByKey(String verificationKey);
}
