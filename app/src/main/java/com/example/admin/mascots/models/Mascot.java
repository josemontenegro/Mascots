package com.example.admin.mascots.models;

public class Mascot {

    private String name, description, uid, owner;
   // private boolean visited;


    public Mascot() {
    }

    public Mascot(String name, String description, String uid, String owner) {
        this.name = name;
        this.description = description;
        this.uid = uid;
        this.owner = owner;
      //  this.visited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

   /* public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }*/
}
