package com.epam.socode.repository;

import com.epam.socode.domain.VerificationToken;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
public interface TokenRepository {
	void save(VerificationToken myToken);

	VerificationToken getByVerificationKey(String verifykey);
}
