package com.zhang.prototype_mode.pck_01;

/**
 * description
 *
 * @author zb 2019/05/19 17:31
 */
public class SuperPerson implements Cloneable {

    private String skill;
    private Person person;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
