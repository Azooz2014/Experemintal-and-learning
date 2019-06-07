package io.blacketron.empublite;

import java.util.List;

public class BookContents {

    List<BookContents.Chapter> chapters;

    int getChaptersCount(){

        return (chapters.size());
    }

    String getChapterFile(int position){

        return (chapters.get(position).file);
    }

    String getChapterTitle(int position){

        return (chapters.get(position).title);
    }


    private static class Chapter {

        String file;
        String title;
    }
}
