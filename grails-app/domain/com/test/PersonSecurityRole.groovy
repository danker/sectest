package com.test

import org.apache.commons.lang.builder.HashCodeBuilder

class PersonSecurityRole implements Serializable {

	Person person
	SecurityRole securityRole

	boolean equals(other) {
		if (!(other instanceof PersonSecurityRole)) {
			return false
		}

		other.person?.id == person?.id &&
			other.securityRole?.id == securityRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (person) builder.append(person.id)
		if (securityRole) builder.append(securityRole.id)
		builder.toHashCode()
	}

	static PersonSecurityRole get(long personId, long securityRoleId) {
		find 'from PersonSecurityRole where person.id=:personId and securityRole.id=:securityRoleId',
			[personId: personId, securityRoleId: securityRoleId]
	}

	static PersonSecurityRole create(Person person, SecurityRole securityRole, boolean flush = false) {
		new PersonSecurityRole(person: person, securityRole: securityRole).save(flush: flush, insert: true)
	}

	static boolean remove(Person person, SecurityRole securityRole, boolean flush = false) {
		PersonSecurityRole instance = PersonSecurityRole.findByPersonAndSecurityRole(person, securityRole)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Person person) {
		executeUpdate 'DELETE FROM PersonSecurityRole WHERE person=:person', [person: person]
	}

	static void removeAll(SecurityRole securityRole) {
		executeUpdate 'DELETE FROM PersonSecurityRole WHERE securityRole=:securityRole', [securityRole: securityRole]
	}

	static mapping = {
		id composite: ['securityRole', 'person']
		version false
	}
}
