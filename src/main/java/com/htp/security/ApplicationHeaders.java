//security

package com.htp.security;

/*
 * в этоткласс сложим разные хедеры, с которыми будем работать.
 * Авторизационный токен, и всякое другое вроде локации и тд.
 */


public interface ApplicationHeaders {
	//Просто принято так называть.
	String AUTH_TOKEN = "X-Auth-Token";
}
