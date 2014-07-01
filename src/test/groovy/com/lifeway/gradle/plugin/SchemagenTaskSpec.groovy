package com.lifeway.gradle.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class SchemagenTaskSpec extends Specification {
	
	Project project
	SchemagenTask task
	def targetDir
	
	def setup() {
		println 'In Setup....'
		project = ProjectBuilder.builder().build()
		project.apply plugin: com.lifeway.gradle.plugin.JaxbPlugin
		project.dependencies {
			jaxb 'com.sun.xml.bind:jaxb-xjc:2.2.7'
		}
		targetDir = new File("$project.buildDir/generated-xsd")
		project.jaxb {
			schemaClassesDir = new File('src/test/groovy/com/lifeway/api/domain')
			schemaTargetDir = targetDir
			xsdFile = new File("$schemaTargetDir/test.xsd")
		}
		
	}
	
	def "Test setting and getting properties"(){
		given:
			def schemaClassesDir = new File('src/test/groovy/com/lifeway/api/domain')
			def schemaTargetDir = new File("$project.buildDir/generated-xsd")
			def xsdFile = new File("$schemaTargetDir/test.xsd")
			
		when:
			task = project.schemagen
			task.setXsdFile xsdFile
			task.setSchemaClassesDir schemaClassesDir
			task.setSchemaTargetDir schemaTargetDir

		then:
			task
			schemaClassesDir == task.getSchemaClassesDir()
			schemaTargetDir == task.getSchemaTargetDir()
			xsdFile == task.getXsdFile()
	}
	
	def "should create xsd file when schmagen task is run"(){
		def schemaTargetDir = new File("$project.buildDir/generated-xsd")
		def xsdFile = new File("$schemaTargetDir/test.xsd")
		when:
			task = project.schemagen
			task.execute()
			
		then:
			xsdFile.exists()
			xsdFile.length() > 0
	}
}
