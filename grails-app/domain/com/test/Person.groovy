package com.test

class Person {

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<SecurityRole> getAuthorities() {
		PersonSecurityRole.findAllByPerson(this).collect { it.securityRole } as Set
	}
	
	String toString() {
		"$username"
	}
}
