package com.test

import grails.plugins.springsecurity.Secured

class SecureController {

	@Secured(['ROLE_ADMIN'])
    def index = {
		render "Secure access only!!!!"
	}
	
	def wideopen = {
		render "Anyone can see this."
	}
	
	@Secured(['ROLE_AWESOME'])
	def wayawesome = {
		render "You must be awesome because you can see this."
	}
}
