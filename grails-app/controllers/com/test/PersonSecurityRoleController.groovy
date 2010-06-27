package com.test

class PersonSecurityRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personSecurityRoleInstanceList: PersonSecurityRole.list(params), personSecurityRoleInstanceTotal: PersonSecurityRole.count()]
    }

    def create = {
        def personSecurityRoleInstance = new PersonSecurityRole()
        personSecurityRoleInstance.properties = params
        return [personSecurityRoleInstance: personSecurityRoleInstance]
    }

    def save = {
        def personSecurityRoleInstance = new PersonSecurityRole(params)
        if (personSecurityRoleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), personSecurityRoleInstance.id])}"
            redirect(action: "show", id: personSecurityRoleInstance.id)
        }
        else {
            render(view: "create", model: [personSecurityRoleInstance: personSecurityRoleInstance])
        }
    }

    def show = {
        def personSecurityRoleInstance = PersonSecurityRole.get(params.id)
        if (!personSecurityRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            [personSecurityRoleInstance: personSecurityRoleInstance]
        }
    }

    def edit = {
        def personSecurityRoleInstance = PersonSecurityRole.get(params.id)
        if (!personSecurityRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [personSecurityRoleInstance: personSecurityRoleInstance]
        }
    }

    def update = {
        def personSecurityRoleInstance = PersonSecurityRole.get(params.id)
        if (personSecurityRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personSecurityRoleInstance.version > version) {
                    
                    personSecurityRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole')] as Object[], "Another user has updated this PersonSecurityRole while you were editing")
                    render(view: "edit", model: [personSecurityRoleInstance: personSecurityRoleInstance])
                    return
                }
            }
            personSecurityRoleInstance.properties = params
            if (!personSecurityRoleInstance.hasErrors() && personSecurityRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), personSecurityRoleInstance.id])}"
                redirect(action: "show", id: personSecurityRoleInstance.id)
            }
            else {
                render(view: "edit", model: [personSecurityRoleInstance: personSecurityRoleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def personSecurityRoleInstance = PersonSecurityRole.get(params.id)
        if (personSecurityRoleInstance) {
            try {
                personSecurityRoleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personSecurityRole.label', default: 'PersonSecurityRole'), params.id])}"
            redirect(action: "list")
        }
    }
}
