import com.test.*

class BootStrap {
	
	def springSecurityService

    def init = { servletContext ->
	
		def adminRole = new SecurityRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new SecurityRole(authority: 'ROLE_USER').save(flush: true)

		String password = springSecurityService.encodePassword('password', 'me') // have to salt with username!
		def testUser = new Person(username: 'me', enabled: true, password: password)
		testUser.save(flush: true)
		
		PersonSecurityRole.create testUser, adminRole, true

		assert Person.count() == 1
		assert SecurityRole.count() == 2
		assert PersonSecurityRole.count() == 1
	
    }

    def destroy = {
    }
}
