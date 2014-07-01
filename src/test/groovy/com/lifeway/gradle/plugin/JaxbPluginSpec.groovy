package com.lifeway.gradle.plugin

import static org.junit.Assert.*

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import spock.lang.Specification

class JaxbPluginSpec extends Specification {
	
	def "should apply plugin to project"(){
		given:
			Project project = ProjectBuilder.builder().build()
			project.apply plugin: com.lifeway.gradle.plugin.JaxbPlugin
			project.jaxb {
				schemaClassesDir = new File("src/main/java/com/lifeway/soa/dash/domain")  
				schemaTargetDir = new File("$project.buildDir/generated-xsd") 
				xsdFile = new File("$schemaTargetDir/digitalassets.xsd")
			}
					
		when:
			def extension = project.extensions.findByName('jaxb')

		then:
			project.schemagen instanceof DefaultTask
			project.tasks.withType(SchemagenTask)
			extension
	}
}
