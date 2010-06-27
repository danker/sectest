package com.test

class SecurityRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [securityRoleInstanceList: SecurityRole.list(params), securityRoleInstanceTotal: SecurityRole.count()]
    }

    def create = {
        def securityRoleInstance = new SecurityRole()
        securityRoleInstance.properties = params
        return [securityRoleInstance: securityRoleInstance]
    }

    def save = {
        def securityRoleInstance = new SecurityRole(params)
        if (securityRoleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), securityRoleInstance.id])}"
            redirect(action: "show", id: securityRoleInstance.id)
        }
        else {
            render(view: "create", model: [securityRoleInstance: securityRoleInstance])
        }
    }

    def show = {
        def securityRoleInstance = SecurityRole.get(params.id)
        if (!securityRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            [securityRoleInstance: securityRoleInstance]
        }
    }

    def edit = {
        def securityRoleInstance = SecurityRole.get(params.id)
        if (!securityRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [securityRoleInstance: securityRoleInstance]
        }
    }

    def update = {
        def securityRoleInstance = SecurityRole.get(params.id)
        if (securityRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (securityRoleInstance.version > version) {
                    
                    securityRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'securityRole.label', default: 'SecurityRole')] as Object[], "Another user has updated this SecurityRole while you were editing")
                    render(view: "edit", model: [securityRoleInstance: securityRoleInstance])
                    return
                }
            }
            securityRoleInstance.properties = params
            if (!securityRoleInstance.hasErrors() && securityRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), securityRoleInstance.id])}"
                redirect(action: "show", id: securityRoleInstance.id)
            }
            else {
                render(view: "edit", model: [securityRoleInstance: securityRoleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def securityRoleInstance = SecurityRole.get(params.id)
        if (securityRoleInstance) {
            try {
                securityRoleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'securityRole.label', default: 'SecurityRole'), params.id])}"
            redirect(action: "list")
        }
    }
}
