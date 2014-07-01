package com.lifeway.gradle.plugin

import java.io.File;

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class SchemagenTask extends DefaultTask {
	

//	@InputDirectory @Optional
    File schemaClassesDir
//
//    @OutputDirectory
    File schemaTargetDir 
//	
//    @Input
    File xsdFile
       

    @TaskAction
    def schemagen() {
		assert project.jaxb.schemaClassesDir != null, 'schemaClassesDir is not defined'
		xsdFile = project.jaxb.xsdFile
		schemaClassesDir = project.jaxb.schemaClassesDir
		schemaTargetDir = project.jaxb.schemaClassesDir
		
        schemaTargetDir.mkdirs()
		ant.taskdef(name: 'schemagen', classname: 'com.sun.tools.jxc.SchemaGenTask', classpath: project.configurations.jaxb.asPath)
		ant.schemagen(srcdir: schemaClassesDir, destdir: schemaTargetDir)
		new File("$schemaTargetDir/schema1.xsd").renameTo(xsdFile)
    }     
}