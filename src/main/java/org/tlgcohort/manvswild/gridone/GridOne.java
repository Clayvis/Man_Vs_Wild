package org.tlgcohort.manvswild.GridOne;

public class GridOne{
    private String name;
    private String tools;

    public GridOne() {
    }

    public GridOne(String name, String tools) {
        this.name = name;
        this.tools = tools;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "GridOne{" +
                "name='" + name + '\'' +
                ", tools='" + tools + '\'' +
                '}';
    }
}
