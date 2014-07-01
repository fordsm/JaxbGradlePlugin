package com.lifeway.api.domain

import javax.xml.bind.annotation.XmlRootElement

import javax.xml.bind.annotation.XmlElement

@XmlRootElement
class TestDomain {

	@XmlElement
	String id
	
	@XmlElement
	String name
}
