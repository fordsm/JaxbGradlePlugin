This project represents a template for what a LifeWay API project should look
like. We're striving for a common, consistent Gradle build, instead of each
project using different wrapper versions and tool.  This will still maintain
each project's isolation and keep the build as transparent as possible. While at
the same time we want to be able to push out build updates in a predictable
fashion.

There are three branches for which you should concern yourself with:
* master - Template of a fully working project. It's a good starting place if you're starting from scratch.
* groovy-library - build/project template for a groovy library project.
* groovy-service - build/project template for a groovy service project.

## Features

The template features:
* Consolidated customizations into build.gradle, moving generic code into individual files in the gradle directory
* Jenkins friendly version of gradlew
* Publish to Artifactory
* Find Bugs, PMD and CheckStyle/CodeNarc
* Build source jar and javadoc jar
* JaCoCo for code coverage
* Header checking for headers
* Task to create source directories based on convention

Future features:
* Release plugin to build, tag, and publish

## Merging build files
This will pull in the template:

    git remote add --track $BRANCH build git@github.lifeway.org:Gradle/gradle-template.git
    git fetch build
    git merge build/$BRANCH

### Customize

* Edit settings.gradle, set the name of the project and to list all the submodules you have.
* Edit gradle.properties
    * customize group - make sure it represents the group that you want to publish into Artifactory with.
    * set the version to the *next* version with -SNAPSHOT on the end
    * set the minLineCoverage, minBranchCoverage for JaCoCo coverage limits
* Edit build.gradle
    * customize ext.githubProjectName - Used to construct a few urls in the pom.
    * customize ext.githubUserName - this is the User/Organization in the GitHub URI (e.g. APIServices)

If in a multi-module project, edit the build.gradle and create a project block for each project, there are two by default which you override.

The dependencies will have to be changed to match the pom dependencies. In the single-project branch, simply follow the dependencies block. In the multi-project branch, there are a few dependencies sections. The first one, part of subprojects, is common to all projects. And then there's a dependency block for each project.

## Updating Template

As updates are put into the gradle-template project, individual OSS Projects will need to merge in those changes. Any changes will be announced to the DL Open Source Software.  To perform that merge run this, using the branch you choose when you first setup the project:

    git fetch build
    git merge build/$BRANCH

## Properties

Gradle has a few ways to inject properties into the project, which can then be used in the DSL.
It can done on the command line via the "_\-P_ option,

e.g.

    ./gradlew -Pmyproperty=myvalue_".

It can also be done via Java System Property as long as it's prefixed with ORG\_GRADLE\_PROJECT,

e.g.

    ./gradlew -DORG\_GRADLE\_PROJECT\_myproperty=myvalue

To avoid having to pass in some properties every time on the command line, they can
be stored in <em>$HOME/.gradle/gradle.properties</em>.
