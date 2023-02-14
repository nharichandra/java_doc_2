package com.nisum.java8.practice.model;

/**
 * Experience class to have years of experience and 
 * 
 * @author Rjosula
 *
 */
public class Experience {

	private long numberOfExperience;
	
	private String skillName;
	
	/**
	 * Parameterised constructor.
	 * 
	 * @param skill - skill name
	 * @param experience - total experience
	 */
	public Experience(String skill, long experience) {
		this.skillName = skill;
		this.numberOfExperience = experience;
	}

	public long getNumberOfExperience() {
		return numberOfExperience;
	}

	public void setNumberOfExperience(long numberOfExperience) {
		this.numberOfExperience = numberOfExperience;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	
}
