package com.lifeway.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class JaxbPlugin implements Plugin<Project> {

	static final String EXTENSION_NAME = 'jaxb'
	
	@Override
	void apply(Project project) {
		project.extensions.create(EXTENSION_NAME, JaxbPluginExtension)
		project.configurations.create('jaxb')
//		project.dependencies.add('jaxb', 'com.sun.xml.bind:jaxb-xjc:2.2.7')  
		project.task('schemagen', type: SchemagenTask) 
	}  
}

class JaxbPluginExtension {
	File schemaClassesDir
	File schemaTargetDir
	File xsdFile
}