package com.lifeway.gradle.plugin;

import static org.junit.Assert.*

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
//import com.lifeway.gradle.plugin.SchemagenTask

class JaxbPluginTest {

	@Test
	public void jaxbPluginAddsGreetingTaskToProject() {
		Project project = ProjectBuilder.builder().build()
		project.apply plugin: com.lifeway.gradle.plugin.JaxbPlugin
		project.jaxb {
			schemaClassesDir = new File("src/main/java/com/lifeway/soa/dash/domain")  
			schemaTargetDir = new File("$project.buildDir/generated-xsd") 
			xsdFile = new File("$schemaTargetDir/digitalassets.xsd")
		}
		def extension = project.extensions.findByName('jaxb')

		assertTrue(project.schemagen instanceof DefaultTask)
		assertNotNull(project.tasks.withType(SchemagenTask) )
		assertNotNull(extension)
	}
}
