/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package org.teiid.security;

import java.util.concurrent.atomic.AtomicLong;

import org.ietf.jgss.GSSCredential;


public class GSSResult {
    private static String NULL_TOKEN = "Auth validated with no further peer token ";
    private static AtomicLong COUNT = new AtomicLong(0);
    
    private byte[] serviceToken;
	private boolean authenticated;
	private Object securityContext;
	private String userName;
	private GSSCredential delegationCredential;
	
	public GSSResult(byte[] token, boolean authenticated, GSSCredential cred) {
		this.serviceToken = token;
		this.authenticated = authenticated;
		this.delegationCredential = cred;
	}
	
	public GSSResult(boolean authenticated, GSSCredential cred) {
        this.serviceToken = (NULL_TOKEN + COUNT.getAndIncrement()).getBytes();
        this.authenticated = authenticated;
        this.delegationCredential = cred;
	}
	
	public boolean isNullContinuationToken() {
	    String token = new String(this.serviceToken);
	    return token.startsWith(NULL_TOKEN);
	}
	
	public boolean isAuthenticated() {
		return this.authenticated;
	}
	
	public byte[] getServiceToken() {
		return this.serviceToken;
	}
	
	public void setSecurityContext(Object sc) {
		this.securityContext = sc;
	}
	
	public Object getSecurityContext() {
		return this.securityContext;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}

    public GSSCredential getDelegationCredential() {
        return delegationCredential;
    }

    public void setDelegationCredential(GSSCredential delegationCredentail) {
        this.delegationCredential = delegationCredentail;
    }	
}
