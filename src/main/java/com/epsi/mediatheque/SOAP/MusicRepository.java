package com.epsi.mediatheque.SOAP;


import music.Music;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
@Component
public class MusicRepository {
    private static final Map<String, Music> musics = new HashMap();

    @PostConstruct
    public void initData(){
        Music astonishing = new Music();
        astonishing.setAuthor("Dream Theater");
        astonishing.setId("1");
        astonishing.setIsbn("123456789");
        astonishing.setTitle("The Astonishing");

        musics.put(astonishing.getTitle(),astonishing);

        Music similitude = new Music();
        astonishing.setAuthor("The Neal Morse Band");
        astonishing.setId("2");
        astonishing.setIsbn("987654321");
        astonishing.setTitle("The Similitude Of A Dream");

        musics.put(similitude.getTitle(),similitude);
    }

    public Music findMusic(String id){
        Assert.notNull(id,"The music's id must not be null");
        return musics.get(id);
    }
}
