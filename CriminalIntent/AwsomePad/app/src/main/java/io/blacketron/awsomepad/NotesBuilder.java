package io.blacketron.awsomepad;

/**
 * Created by Blacketron on 6/26/2017.
 */

public class NotesBuilder {

    private String title , content;

    public NotesBuilder(){}

    public NotesBuilder(String title, String content){

        this.title = title;
        this.content = content;
    }

    public String getTitle(){

        return title;
    }

    public String getContent(){

        return content;
    }
}
